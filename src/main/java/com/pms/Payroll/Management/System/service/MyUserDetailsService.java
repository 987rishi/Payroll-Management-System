package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.models.AppUser;
import com.pms.Payroll.Management.System.repo.AppUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private AppUserRepo appUserRepo;

  private static final Logger logger =
          LoggerFactory.getLogger(MyUserDetailsService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("USER NAME = {}", username);
    AppUser user = appUserRepo
            .findByUserEmail(username)
            .orElseThrow( () -> new NoSuchElementException(
                    "USER DOES NOT EXIST WITH EMAIL: " + username
            ));

//    logger.info("APP USER :{}", user.toString());
    logger.info("APP USER EMAIL:{} ,-------{}", user.getUsername(),
            user.getPassword());
    return user;


  }
}
