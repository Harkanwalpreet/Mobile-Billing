package com.cg.mobilebilling.beans;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity
public class PostpaidAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="mobileNo")
	@SequenceGenerator(name="mobileNo", sequenceName="sequence1",initialValue=987650001,allocationSize=0)
	private long mobileNo;
	
	@ManyToOne
	@JoinColumn(name="planID")
	private Plan plan;
	
	@OneToMany(mappedBy="postpaidAccount", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@MapKey
	private  Map<Integer, Bill> bills;
	
	@ManyToOne
	@JoinColumn(name="customerID")
	private Customer customer;
	
	
	public PostpaidAccount(Customer customer) {
		super();
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public PostpaidAccount() {}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Map<Integer, Bill> getBills() {
		return bills;
	}
	public void setBills(Map<Integer, Bill> bills) {
		this.bills = bills;
	}
	public PostpaidAccount(long mobileNo, Plan plan, Map<Integer, Bill> bills) {
		super();
		this.mobileNo = mobileNo;
		this.plan = plan;
		this.bills = bills;
	}
	public PostpaidAccount(Customer customer,Plan plan) {
		super();
		this.plan = plan;
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "PostpaidAccount [mobileNo=" + mobileNo + "]";
	}
}