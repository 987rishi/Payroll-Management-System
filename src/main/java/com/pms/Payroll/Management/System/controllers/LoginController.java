package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.LoginDto;
import com.pms.Payroll.Management.System.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtService jwtService;

  @PostMapping("/login")
  ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto){

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword())
            );

    if(authentication.isAuthenticated())
      return new ResponseEntity<>(jwtService
              .generateToken(loginDto.getEmail())
      , HttpStatus.OK);

    return new ResponseEntity<>("CANNOT LOGIN", HttpStatus.UNAUTHORIZED);
  }

}
