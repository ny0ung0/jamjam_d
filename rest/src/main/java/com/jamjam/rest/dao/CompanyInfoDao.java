package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.CompanyInfo;

@Mapper
public interface CompanyInfoDao {

	@Insert("insert into companyinfo (company_id,description,number_of_employees,working_environment,benefits,ideal_candidate,main_contact,main_email,origin_name,new_name) "
			+ "values(#{company_id},#{description},#{number_of_employees},#{working_environment},#{benefits},#{ideal_candidate},#{main_contact},#{main_email},#{originName},#{newName})")
	public int insertCompanyInfo(CompanyInfo companyInfo);
	
	@Select("select * from Companyinfo where company_id = #{company_id}")
	public CompanyInfo getCompanyInfo(Integer company_id);
}
