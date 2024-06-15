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

	 @Insert("INSERT INTO Company(email,company_name,registration_number,company_type,representative_name,address,tel)  "
	 		+ "VALUES (#{email},#{company_name}, #{registration_number}, #{company_type}, #{representative_name}, #{address}, #{tel})")
	 public int insertCompany(Company company);
	 
	 @Select("select * from company where company_id = #{company_id}")
	 public  Company getCompany(Integer company_id);
	 
	 @Update("UPDATE company SET email=#{email}, company_name=#{company_name},registration_number=#{registration_number},company_type=#{company_type},representative_name=#{representative_name},address=#{address},tel=#{tel}  "
	 		+ "WHERE company_id =#{company_id} ")
	 public int updateCompany(Company company);
	 @Delete("delete from company where company_id = #{company_id}")
	 public int deleteCompany(Integer Company_id);
}
