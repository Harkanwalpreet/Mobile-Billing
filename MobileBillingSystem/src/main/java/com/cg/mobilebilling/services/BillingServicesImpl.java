package com.cg.mobilebilling.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.daoservices.BillingDAOServices;
import com.cg.mobilebilling.daoservices.CustomerDAOServices;
import com.cg.mobilebilling.daoservices.PlanDAOServices;
import com.cg.mobilebilling.daoservices.PostpaidAccountDAOServices;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
@Transactional
public class BillingServicesImpl implements BillingServices {

	@Autowired
	private CustomerDAOServices customerDAOServices;
	@Autowired
	private PostpaidAccountDAOServices postpaidAccountDAOServices;
	@Autowired
	private PlanDAOServices planDAOServices;
	@Autowired
	private BillingDAOServices billingDAOServices;
	private PostpaidAccount postpaidAccount;
	private Plan plan;
	private Customer customer;
	private Bill bill;
	private List<Customer> customers;
	private Map<Long, PostpaidAccount> postpaidAccounts;

	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		return planDAOServices.findAll();
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		Plan plan=new Plan(101, 200, 100, 50, 100, 20, 1, 1, 1, 1, 1, 100, "Punjab", "Silver");
		Plan plan1=new Plan(102, 300, 200, 150, 200, 30, 2, 2, 2, 2, 2, 200, "Maharashtra", "Gold");
		Plan plan2=new Plan(103, 400, 300, 250, 300, 40, 3, 3, 3, 3, 3, 300, "Delhi", "Platinum");
		planDAOServices.save(plan);
		planDAOServices.save(plan1);
		planDAOServices.save(plan2);
		return customerDAOServices.save(customer);		
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer =customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("CustomerDetailsNotFoundException"));
		Plan plan= planDAOServices.findById(planID).orElseThrow(()-> new PlanDetailsNotFoundException("PlanDetailsNotFoundException"));
		PostpaidAccount account = new PostpaidAccount(customer,plan);
		account=postpaidAccountDAOServices.save(account);
		return account.getMobileNo();
	}

	@Override
	public Bill generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
					throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
					BillingServicesDownException, PlanDetailsNotFoundException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->
		new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		plan = postpaidAccount.getPlan();
		float localSMSAmount,stdSMSAmount, localCallAmount, stdCallAmount,internetDataUsageAmount, totalBillAmount, servicesTax, vat;
		
		if((noOfLocalSMS-plan.getFreeLocalSMS())<0)
			localSMSAmount = 0;
		else
		localSMSAmount=plan.getLocalSMSRate()*(noOfLocalSMS-plan.getFreeLocalSMS());
		
		if((noOfStdSMS-plan.getFreeStdSMS())<0)
			stdSMSAmount=0;
		else
		stdSMSAmount=plan.getStdSMSRate()*(noOfStdSMS-plan.getFreeStdSMS());
		
		if((noOfLocalCalls-plan.getFreeLocalCalls())<0)
			localCallAmount=0;
		else
		localCallAmount=plan.getLocalCallRate()*(noOfLocalCalls-plan.getFreeLocalCalls());
		
		if((noOfStdCalls-plan.getFreeStdCalls())<0)
			stdCallAmount=0;
		else
		stdCallAmount=plan.getStdCallRate()*(noOfStdCalls-plan.getFreeStdCalls());
		
		if((internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())<0)
			internetDataUsageAmount=0;
		else
		internetDataUsageAmount=plan.getInternetDataUsageRate()*(internetDataUsageUnits-plan.getFreeInternetDataUsageUnits());
		
		totalBillAmount=localCallAmount+stdCallAmount+localSMSAmount+stdSMSAmount+internetDataUsageAmount+plan.getMonthlyRental();
		servicesTax = (float) (totalBillAmount*0.025);					
		vat =  (float) (totalBillAmount*0.025);
		totalBillAmount = totalBillAmount+servicesTax+vat;
		bill=new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth, totalBillAmount, localSMSAmount, stdSMSAmount, localCallAmount, stdCallAmount, internetDataUsageAmount, servicesTax, vat, postpaidAccount);
		billingDAOServices.save(bill);
		return bill;
	}

	@Override
	public Customer getCustomerDetails(Customer customer)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer1=customerDAOServices.findById(customer.getCustomerID()).orElseThrow(()->new CustomerDetailsNotFoundException("Customer Details Not Found"));
		if (!customer.getFirstName().equals(customer1.getFirstName())) 
			throw new CustomerDetailsNotFoundException("Customer Details Not Found");
		return customer1;
	}

	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		Customer customer = customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer Details Not Found"));
		return customer;
	}
	
	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {
		List<Customer> customers=customerDAOServices.findAll();
		return customers;
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->
		new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		return postpaidAccount;
	}

	/*@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		List<PostpaidAccount> postpaidAccounts = customer.getPostpaidAccounts();
		return postpaidAccounts;
	}*/
	
	@Override
	public Map<Long, PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		Map<Long, PostpaidAccount> postpaidAccounts = customer.getPostpaidAccounts();
		return postpaidAccounts;
	}
	
	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException, PlanDetailsNotFoundException {
		Customer customer = customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->
		new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		Bill bill = billingDAOServices.findByMonth(mobileNo, billMonth);
		System.out.println(bill);
		return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			BillDetailsNotFoundException {
		Customer customer = customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->
		new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		List<Bill>bills = billingDAOServices.findby(mobileNo);
		return bills;
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
	PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		Customer customer = customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		Plan plan = planDAOServices.findById(planID).orElseThrow(()->
		new PlanDetailsNotFoundException("Plan details for planId"+planID+"not found"));
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->
		new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		postpaidAccount.setPlan(plan);
		postpaidAccountDAOServices.save(postpaidAccount);
		return false;
	}
	
	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer = customerDAOServices.findById(customerID).orElseThrow(()-> new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->	new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		postpaidAccountDAOServices.deleteById(mobileNo);
		return true;
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		Customer customer = customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer details for customerId"+customerID+"not found"));
		customerDAOServices.deleteById(customerID);
		return true;

	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount=postpaidAccountDAOServices.findById(mobileNo).orElseThrow(()->
		new PostpaidAccountNotFoundException("Postpaid account for mobileNo"+mobileNo+"not found"));
		return postpaidAccount.getPlan();
	}
}