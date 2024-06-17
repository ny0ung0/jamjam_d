package com.jamjam.rest.service.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamjam.rest.dao.CompanyDao;
import com.jamjam.rest.dto.Company;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyMapper;
	
	public Company getCompany(Integer user_id) {
		
		return companyMapper.getCompany(user_id);
	}
	public int updateCompany(Company company) {
		return companyMapper.updateCompany(company);
	}
	
	public int deleteCompany(Integer user_id) {
		return companyMapper.deleteCompany(user_id);
	}
}
