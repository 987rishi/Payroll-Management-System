package com.pms.Payroll.Management.System.config;

import com.pms.Payroll.Management.System.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final UserDetailsService detailsService;
  private final JwtService jwtService;

  public JwtAuthFilter(
          UserDetailsService service,
          JwtService jwtService) {
    this.detailsService = service;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    if(header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String jwtToken = header.substring(7);
      String username = jwtService.extractUsername(jwtToken);

      if(username != null && SecurityContextHolder
              .getContext()
              .getAuthentication() == null) {
        UserDetails userDetails = detailsService.loadUserByUsername(username);

        if(jwtService.validateToken(jwtToken, userDetails)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
          );
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      filterChain.doFilter(request, response);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
