package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
  Optional<AppUser> findByUserEmail(String username);
}
