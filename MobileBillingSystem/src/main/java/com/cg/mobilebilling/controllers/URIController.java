package com.cg.mobilebilling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
@Controller
public class URIController {
	
	public URIController() {
	}	
	
	@RequestMapping("/")
	public String getIndexPage(){
		return "indexPage";
	}			
	@RequestMapping("/registration")
	public String getRegistrationPage(){
		return "registrationPage";
	}	
		
	@RequestMapping("/getMonthlyBill")
	public String getMonthlyBillPage(){
		return "getMonthlyBillPage";
	}	
	
	@RequestMapping("/deleteCustomer")
	public String getDeleteCustomerPage(){
		return "deleteCustomerPage";
	}	
	@RequestMapping("/closeAccount")
	public String getCloseAccountPage(){
		return "closeAccountPage";
	}
	
	@RequestMapping("/customerDetails")
	public String getCustomerDetailsPage(){
		return "customerDetailsPage";
	}
	
	@RequestMapping("/postpaidAccountDetails")
	public String getPostpaidAccountDetailsPage(){
		return "postpaidAccountDetailsPage";
	}
	
	@RequestMapping("/planDetails")
	public String getPlanDetailsPage(){
		return "planDetailsPage";
	}
	
	@RequestMapping("/index")
	public String returnToIndexPage() {
		return "indexPage";
	}
	
	@RequestMapping("/controllerAdviser")
	public String getControllerAdviserPage() {
		return "controllerAdviserPage";
	}
	
	@RequestMapping("/customerAllBillDetails")
	public String getCustomerAllBillDetailsPage() {
		return "customerAllBillDetailsPage";
	}
	
	@RequestMapping("/monthlyBill")
	public String getMonthlyBillPage1() {
		return "monthlyBillDetailsPage";
	}
	
	@ModelAttribute
	public Customer getCustomer() {
		return new Customer();
	}
	@ModelAttribute
	public Bill getBill() {
		return new Bill();
	}
}
