package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.jamjam.rest.dto.Role;

@Mapper
public interface RoleDao {

	@Insert("INSERT INTO role (user_id, company_id, role) VALUES (#{user_id}, #{company_id}, #{role})")
	void insertRole(Role role);
}
