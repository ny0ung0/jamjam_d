package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.jamjam.rest.dto.Company;

@Mapper
public interface CompanyDao {

	 @Insert("INSERT INTO Company(email,registration_number,company_type,representative_name,address,tel)  "
	 		+ "VALUES (#{email}, #{registration_number}, #{company_type}, #{representative_name}, #{address}, #{tel})")
	 public void insertCompany(Company company);
}
