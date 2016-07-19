package com.outwit.edoctor.infrastructure.mapper;

import com.outwit.edoctor.domain.User;
import com.outwit.edoctor.domain.UserType;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface UserMapper {

    int hasUser(String telephone);

    void createUser(User user);

    User fetchUser(String telephone);

    Set<UserType> fetchRoles(String telephone);

    void updateUserPassword(User user);

}
