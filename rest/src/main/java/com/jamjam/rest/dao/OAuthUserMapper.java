package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.OAuthUser;

@Mapper
public interface OAuthUserMapper {
    @Insert("INSERT INTO oauth_user (user_id, provider, provider_user_id) VALUES (#{userId}, #{provider}, #{providerUserId})")
    void insertOAuthUser(OAuthUser oauthUser);

    @Select("SELECT * FROM oauth_user WHERE provider = #{provider} AND provider_user_id = #{providerUserId}")
    OAuthUser findByProviderAndProviderUserId(@Param("provider") String provider, @Param("providerUserId") String providerUserId);
}
