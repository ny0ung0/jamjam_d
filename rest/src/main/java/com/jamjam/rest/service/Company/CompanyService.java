package com.jamjam.rest.service.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamjam.rest.dao.CompanyDao;
import com.jamjam.rest.dto.Company;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyMapper;
	
	public Company getCompany(Integer company_id) {
		
		return companyMapper.getCompany(company_id);
	}
}
