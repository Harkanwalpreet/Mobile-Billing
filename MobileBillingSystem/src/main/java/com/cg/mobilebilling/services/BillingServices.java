package com.cg.mobilebilling.services;
import java.util.List;
import java.util.Map;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
public interface BillingServices {
	
	List<Plan> getPlanAllDetails() throws BillingServicesDownException;	
	
	Customer acceptCustomerDetails(Customer customer) throws BillingServicesDownException,
		CustomerDetailsNotFoundException;
	
	long openPostpaidMobileAccount(int customerID, int planID) 
			throws PlanDetailsNotFoundException,CustomerDetailsNotFoundException,
			BillingServicesDownException;
	
	Bill  generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls,int internetDataUsageUnits) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			InvalidBillMonthException, BillingServicesDownException, 
			PlanDetailsNotFoundException;
	
	Customer getCustomerDetails(Customer customer)
			throws CustomerDetailsNotFoundException, BillingServicesDownException;
	
	List<Customer> getAllCustomerDetails() throws BillingServicesDownException;
	
	PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, 
			PostpaidAccountNotFoundException, 
			BillingServicesDownException;
	
	Map<Long, PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException;
	
	Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException, PlanDetailsNotFoundException;
	
	List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			BillingServicesDownException, BillDetailsNotFoundException;
	
	boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			PlanDetailsNotFoundException, BillingServicesDownException;
	
	boolean closeCustomerPostPaidAccount(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			BillingServicesDownException;
	
	boolean deleteCustomer(int customerID) 
			throws BillingServicesDownException, CustomerDetailsNotFoundException;
	
	Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			BillingServicesDownException, PlanDetailsNotFoundException ;

	Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException;
}