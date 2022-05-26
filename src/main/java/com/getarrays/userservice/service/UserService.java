package com.getarrays.userservice.service;

import com.getarrays.userservice.domain.AppUser;
import com.getarrays.userservice.domain.Role;

import java.util.List;

public interface UserService {

    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoletoAppUser(String username,String rolename);
    AppUser getUser(String  username);
    List<AppUser> getAppUsers();//to return specific username
}
