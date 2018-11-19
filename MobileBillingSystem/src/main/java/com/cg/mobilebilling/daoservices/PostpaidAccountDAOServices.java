package com.cg.mobilebilling.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mobilebilling.beans.PostpaidAccount;

public interface PostpaidAccountDAOServices extends JpaRepository<PostpaidAccount, Long>{

}
