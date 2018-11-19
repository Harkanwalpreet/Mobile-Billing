package com.cg.mobilebilling.controllers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.daoservices.CustomerDAOServices;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
import com.cg.mobilebilling.services.BillingServices;
import com.cg.mobilebilling.services.GeneratePdfReport;
import com.itextpdf.text.DocumentException;

@Controller
public class CustomerController {
	@Autowired
	private CustomerDAOServices customerDAO;
	private PostpaidAccount postpaid;
	private Map<Long, PostpaidAccount> postpaidAccounts;
	@Autowired
	private BillingServices billingServices;

	@RequestMapping("registerCustomer")
	public ModelAndView registerCustomerAction(@Valid @ModelAttribute Customer customer,BindingResult result,Model model){
		if(result.hasErrors())
			return new ModelAndView("registrationPage");
		try {
			customer=billingServices.acceptCustomerDetails(customer);
			model.addAttribute("successMessage","Registration Successful. Customer ID:"+customer.getCustomerID());
			return new ModelAndView("controllerAdviserPage","customer",customer);
		} catch (BillingServicesDownException| CustomerDetailsNotFoundException e) {
			model.addAttribute("errorMessage",e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}

	@RequestMapping("displayPlanDetails")
	public ModelAndView displayPlanDetailsAction(@Valid @ModelAttribute Customer customer,BindingResult result,Model model) {
		try {
			List<Plan> plans = billingServices.getPlanAllDetails();
			model.addAttribute("plans",plans);		
			return new ModelAndView("displayPlansPage");
		} catch (BillingServicesDownException e) {
			model.addAttribute("errorMessage",e.getMessage());		
			return new ModelAndView("controllerAdviserPage");
		}				
	}

	@RequestMapping("addPlanToAccount")
	public ModelAndView addPlanToAccountAction(@RequestParam("planID") int planID, @ModelAttribute Customer customer,Model model) {
		try {
			long mobileNo= billingServices.openPostpaidMobileAccount(customer.getCustomerID(), planID);
			model.addAttribute("successMessage", "Postpaid account successfully created. Your new mobile number is "+mobileNo);
			return new ModelAndView("controllerAdviserPage");
		} catch (PlanDetailsNotFoundException | CustomerDetailsNotFoundException | BillingServicesDownException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}

	@RequestMapping("enterBillDetails")
	public ModelAndView enterBillDetailsAction(@Valid @ModelAttribute Bill bill, BindingResult result,Model model, 
			@RequestParam("mobileNo") long mobileNo,@RequestParam("customerID") int customerID) {
		try {
			bill= billingServices.generateMonthlyMobileBill(customerID, mobileNo, bill.getBillMonth(), bill.getNoOfLocalSMS(), bill.getNoOfStdSMS(), bill.getNoOfLocalCalls(), bill.getNoOfStdCalls(), bill.getInternetDataUsageUnits());
			Plan plan=billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
			model.addAttribute("bill", bill);
			model.addAttribute("plan",plan);
			return new ModelAndView("billSuccessPage");
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
				| BillingServicesDownException | PlanDetailsNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}

	@RequestMapping("deleteCustomerController")
	public ModelAndView deleteCustomerAction(@Valid @ModelAttribute Customer customer, BindingResult result,Model model) {
		try {
			boolean deleteCustomer=billingServices.deleteCustomer(customer.getCustomerID());
			model.addAttribute("successMessage","Your account has been successfully deleted");
			return  new ModelAndView("controllerAdviserPage");
		} catch (BillingServicesDownException |CustomerDetailsNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		} 		
	}

	@RequestMapping("editPlan")
	public ModelAndView editPlanAction(@Valid @RequestParam("customerID") int customerID, @RequestParam("mobileNo") int mobileNo, @RequestParam("planID") int planID, Model model) {
		try {
			billingServices.changePlan(customerID, mobileNo, planID);
			model.addAttribute("successMessage", "Plan changed successfully for mobileNo "+mobileNo);
			return new ModelAndView("controllerAdviserPage");
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | PlanDetailsNotFoundException
				| BillingServicesDownException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}

	}
	
	@RequestMapping("close")
	public ModelAndView closeAction(@Valid @RequestParam("customerID") int customerID, @RequestParam("mobileNo") int mobileNo, Model model) {
		try {
			billingServices.closeCustomerPostPaidAccount(customerID, mobileNo);
			model.addAttribute("successMessage", "Postpaid account of mobileNo: "+mobileNo+" successfully closed");
			return new ModelAndView("controllerAdviserPage");
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping("postpaidAccountDetailsController")
	public ModelAndView PostpaidAccountDetailsAction(@RequestParam("customerID") int customerID, Model model) {
		try {
			postpaidAccounts = billingServices.getCustomerAllPostpaidAccountsDetails(customerID);
			return new ModelAndView("postpaidAccountDisplayPage", "postpaidAccounts", postpaidAccounts);
		} catch (CustomerDetailsNotFoundException | BillingServicesDownException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping("planDetailsController")
	public ModelAndView planDetailsControllerAction(@RequestParam("customerID") int customerID, @RequestParam("mobileNo") int mobileNo, Model model) {
		Plan plan;
		try {
			plan = billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
			return new ModelAndView("planDisplayPage", "plan", plan);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException | PlanDetailsNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping("customerDetailsController")
	public ModelAndView customerDetailsControllerAction(@RequestParam ("customerID") int customerID, Model model) {
		try {
			Customer customer = billingServices.getCustomerDetails(customerID);
			return new ModelAndView("customerDisplayPage","customer", customer);
		} catch (CustomerDetailsNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping("customerAllBillDetailsController")
	public ModelAndView customerAllBillDetailsControllerAction(@RequestParam ("customerID") int customerID, @RequestParam("mobileNo") long mobileNo, Model model) {
		try {
			List<Bill>bills = billingServices.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo);
			return new ModelAndView("customerAllBillDisplayPage","bills",bills);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException
				| BillDetailsNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping("monthlyBillDisplayController")
	public ModelAndView monthlyBillDisplayControllerAction(@RequestParam ("customerID") int customerID, @RequestParam("mobileNo") long mobileNo, @RequestParam("billMonth") String billMonth, Model model) {
		try {
			Bill bill = billingServices.getMobileBillDetails(customerID, mobileNo, billMonth);	
			return new ModelAndView("monthlyBillDisplayPage", "bill", bill);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException | BillDetailsNotFoundException | BillingServicesDownException | PlanDetailsNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping("allCustomerDetailsDisplayController")
	public ModelAndView allCustomerDetailsDisplayControllerAction(Model model) {
		try {
			List<Customer> customers = billingServices.getAllCustomerDetails();
			return new ModelAndView("allCustomerDetailsDisplayPage", "customers", customers);
		} catch (BillingServicesDownException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return new ModelAndView("controllerAdviserPage");
		}
	}
	
	@RequestMapping(value = "/allBillsPdfReport", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_PDF_VALUE )
public ResponseEntity<InputStreamResource> getAllBillsPdfReport(@RequestParam("customerID")int customerID,@RequestParam("mobileNo")long mobileNo) throws IOException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException, BillDetailsNotFoundException, PlanDetailsNotFoundException, DocumentException {

List<Bill> bills =  billingServices.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo);

ByteArrayInputStream bis = GeneratePdfReport.billsReport(bills);

HttpHeaders headers = new HttpHeaders();
headers.add("Content-Disposition", "inline; filename=billsreport.pdf");

return ResponseEntity
        .ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
}
}