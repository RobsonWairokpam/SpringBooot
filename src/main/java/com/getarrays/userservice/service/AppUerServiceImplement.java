package com.getarrays.userservice.service;

import com.getarrays.userservice.domain.AppUser;
import com.getarrays.userservice.domain.Role;
import com.getarrays.userservice.repository.AppUserRepo;
import com.getarrays.userservice.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class AppUerServiceImplement  implements UserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserRepo.findByUsername(username);
        if(appUser==null){
            log.error("User is not found in database");
            throw new UsernameNotFoundException("User is not found in database");
        }else {
            log.info("User found in database:{}",username);
        }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        );
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(),appUser.getPassword(),authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new user {} to thr database",appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to thr database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoletoAppUser(String username, String rolename) {
        log.info("Adding role {} to user {}",rolename,username);
        AppUser appUser =appUserRepo.findByUsername(username);
        Role role =roleRepo.findByName(rolename);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user{}",username);
        return appUserRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Fetching all users");
        return appUserRepo.findAll();
    }


}
