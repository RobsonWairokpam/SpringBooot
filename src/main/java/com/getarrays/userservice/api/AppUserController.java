package com.getarrays.userservice.api;

import com.getarrays.userservice.domain.AppUser;
import com.getarrays.userservice.domain.Role;
import com.getarrays.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getAppUsers(){
        return ResponseEntity.ok().body(userService.getAppUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<AppUser>saveAppUser(@RequestBody AppUser appUser){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveAppUser(appUser));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role ){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>saveRole(@RequestBody RoleToAppUserForm roleToAppUserForm ){
        userService.addRoletoAppUser(roleToAppUserForm.getUsername(),roleToAppUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/role/addtouser")
    public  void refreshToken(HttpServletRequest request, HttpServletResponse response ) {
        String authorizationHeader=request.getHeader(AUTHORIZATION);
    }
}
@Data
class RoleToAppUserForm{
    private String username;
    private String roleName;
}
