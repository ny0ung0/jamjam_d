package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.PositionProposal;

@Mapper
public interface PositionProposalDao {

	@Insert("insert into positionproposal(company_id,user_id,proposal_title,proposal_content) "
			+ "values(#{company_id},#{user_id},#{proposal_title},#{proposal_content})")
	public void insertProposal(PositionProposal positionProposal);
	
	@Select("select * from positionproposal where company_id =#{company_id}")
	public List<PositionProposal> findProposalsByCompanyId(Integer company_id);
}
