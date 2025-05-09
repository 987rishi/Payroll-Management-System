package com.pms.Payroll.Management.System.DbInitializer;

import com.pms.Payroll.Management.System.models.Role;
import com.pms.Payroll.Management.System.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private RoleRepo roleRepo;

  @Override
  public void run(String... args) throws Exception {

    if(roleRepo.count() != 0) return;
    roleRepo.saveAll(List.of(
            new Role(null, "DEPT_HEAD",2),
            new Role(null, "HR",2),
            new Role(null, "FINANCE_MANAGER",3),
            new Role(null, "ADMINISTRATIVE_STAFF",1),
            new Role(null, "TEACHING_STAFF",1),
            new Role(null, "SUPPORT_STAFF",1)
    ));
  }
}
