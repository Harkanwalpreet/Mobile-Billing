package com.cg.mobilebilling.daoservices;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cg.mobilebilling.beans.Bill;
public interface BillingDAOServices extends JpaRepository<Bill, Integer>{

	@Query("SELECT b FROM Bill b WHERE b.postpaidAccount.mobileNo=:mobileNo")
	List<Bill> findby(@Param("mobileNo")long mobileNo);
	
	@Query("SELECT b FROM Bill b WHERE b.billMonth =:billMonth AND b.postpaidAccount.mobileNo=:mobileNo")
	Bill findByMonth(@Param("mobileNo") long mobileNo, @Param("billMonth") String billMonth);
}