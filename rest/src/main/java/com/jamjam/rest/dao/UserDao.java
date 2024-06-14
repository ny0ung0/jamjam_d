package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.User;

@Mapper
public interface UserDao {
    @Insert("INSERT INTO User (email, name) VALUES (#{email}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);

    @Select("SELECT * FROM User WHERE email = #{email}")
    User findByEmail(String email);
    
    @Update("UPDATE User SET name = #{name}, contact = #{contact}, address = #{address}, birth_date = #{birth_date}, gender = #{gender} WHERE email = #{email}")
    void updateUser(User user);
}
