package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.Company;

@Mapper
public interface CompanyDao {
	Company company = new Company();

	 @Update("UPDATE user SET company_name=#{company_name},registration_number=#{registration_number},company_type=#{company_type},representative_name=#{representative_name},address=#{address},tel=#{tel},role=#{role}  "
		 		+ "WHERE email =#{email} ")
	 public int insertCompany(Company company);
	 
	 @Select("select * from user where user_id = #{user_id}")
	 public  Company getCompany(Integer user_id);
	 
	 @Update("UPDATE user SET email=#{email}, company_name=#{company_name},registration_number=#{registration_number},company_type=#{company_type},representative_name=#{representative_name},address=#{address},tel=#{tel}  "
	 		+ "WHERE user_id =#{user_id} ")
	 public int updateCompany(Company company);
	 
	 @Delete("delete from user where user_id = #{user_id}")
	 public int deleteCompany(Integer user_id);
	 
	 @Select("select * from user where email = #{email}")
	 public Company getCompanyByEmail(String email);
}
