package com.outwit.edoctor.infrastructure.mapper;

import com.outwit.edoctor.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int hasUser(String telephone);

    void createUser(User user);

    User fetchUser(String telephone);
}
