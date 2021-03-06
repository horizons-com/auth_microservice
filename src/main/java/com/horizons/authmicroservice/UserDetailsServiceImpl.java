package com.horizons.authmicroservice;

import com.horizons.authmicroservice.models.AppUser;
import com.horizons.authmicroservice.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*
        appUserRepository.save(new AppUser("student", encoder.encode( "student"), "STUDENT"));
        appUserRepository.save(new AppUser("parent", encoder.encode( "parent"), "PARENT"));
        appUserRepository.save(new AppUser("admin", encoder.encode( "admin"), "ADMIN"));
        appUserRepository.save(new AppUser("counselor", encoder.encode( "counselor"), "COUNSELOR"));
        appUserRepository.save(new AppUser("employer", encoder.encode( "employer"), "EMPLOYER"));
*/

        final List<AppUser> users = appUserRepository.findAll();

        // Iterate through all results to find the username
        for(AppUser appUser: users) {
            if(appUser.getUsername() != null && appUser.getUsername().equals(username)) {

                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
            }
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}