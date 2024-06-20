package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.PositionProposal;

@Mapper
public interface PositionProposalDao {

	@Insert("insert into positionproposal(company_id,user_id,proposal_title,proposal_content) "
			+ "values(#{company_id},#{user_id},#{proposal_title},#{proposal_content})")
	public void insertProposal(PositionProposal positionProposal);
	
	@Select("select * from positionproposal where company_id =#{company_id}")
	public List<PositionProposal> findProposalsByCompanyId(Integer company_id);
	
	@Select("SELECT proposal_id, company_id, p.user_id as user_id, STATUS, proposal_title, proposal_content, p.created_at as created_at, p.updated_at as updated_at, company_name from positionproposal p RIGHT JOIN user ON p.company_id = user.user_id WHERE p.user_id = #{user_id}")
	public List<PositionProposal> findProposalsByUserId(Integer user_id);
	
	@Update("UPDATE positionproposal SET status = #{status} WHERE proposal_id = #{proposal_id}")
	public void updatePositionproposalStatue(@Param("status") String status, @Param("proposal_id") Integer proposal_id);
	
	@Select("select p.*, u.name as user_name from positionproposal p left join user u on p.user_id = u.user_id where p.company_id=#{company_id} ")
	public List<PositionProposal> findProposalsAndNameByCompanyId(Integer company_id);
}
