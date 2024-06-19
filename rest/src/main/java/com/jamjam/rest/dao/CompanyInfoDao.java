package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.CompanyInfo;

@Mapper
public interface CompanyInfoDao {

	@Insert("insert into companyinfo(company_id,description,number_of_employees,working_environment,benefits,ideal_candidate,main_contact,main_email,origin_name,new_name) "
			+ "values(#{company_id},#{description},#{number_of_employees},#{working_environment},#{benefits},#{ideal_candidate},#{main_contact},#{main_email},#{origin_name},#{new_name})")
	public int insertCompanyInfo(CompanyInfo companyInfo);
	
	@Select("select * from companyinfo where company_id = #{company_id}")
	public CompanyInfo getCompanyInfoByCompanyId(Integer company_id);
	
	
	@Select("select * from companyinfo where info_id = #{info_id}")
	public CompanyInfo getCompanyInfoByInfoId(Integer info_id);
	
	@Update("update companyinfo set description=#{description},number_of_employees=#{number_of_employees},working_environment=#{working_environment},benefits=#{benefits},ideal_candidate=#{ideal_candidate},main_contact=#{main_contact},main_email=#{main_email},origin_name=#{origin_name},new_name=#{new_name}")
	public int updateCompanyInfoImage(CompanyInfo companyInfo);
	
	@Update("update companyinfo set description=#{description},number_of_employees=#{number_of_employees},working_environment=#{working_environment},benefits=#{benefits},ideal_candidate=#{ideal_candidate},main_contact=#{main_contact},main_email=#{main_email}")
	public int updateCompanyInfo(CompanyInfo companyInfo);
	
	@Delete("delete from Companyinfo where info_id=#{info_id} ")
	public int deleteCompanyInfo(Integer info_id);
}
