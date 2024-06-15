package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.Company;

@Mapper
public interface CompanyDao {
	Company company = new Company();

	 @Insert("INSERT INTO Company(email,registration_number,company_type,representative_name,address,tel)  "
	 		+ "VALUES (#{email}, #{registration_number}, #{company_type}, #{representative_name}, #{address}, #{tel})")
	 public int insertCompany(Company company);
	 
	 @Select("select * from company where company_id = #{company_id}")
	 public  Company getCompany(Integer company_id);
}
