package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.User;

@Mapper
public interface UserDao {
    @Insert("INSERT INTO User (email, name, role) VALUES (#{email}, #{name}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "user_id")
    void insertUser(User user);

    @Select("SELECT * FROM User WHERE email = #{email}")
    User findByEmail(String email);
    
    @Select("SELECT * FROM User WHERE email = #{email}")
    Integer findByEmailInteger(String email);
    
    @Update("UPDATE User SET name = #{name}, contact = #{contact}, address = #{address}, birth_date = #{birth_date}, gender = #{gender} WHERE email = #{email}")
    void updateUser(User user);
    
    @Select("select * from user where user_id= #{user_id}")
    public User findByUserId(Integer user_id);
    
    @Select("select * from user where role='ROLE_USER'")
    public List<User> userFindAll();
    
    @Update("UPDATE User SET contact = #{contact}, address = #{address} WHERE email = #{email}")
    void updateUserAfterInfo(User user);
}
