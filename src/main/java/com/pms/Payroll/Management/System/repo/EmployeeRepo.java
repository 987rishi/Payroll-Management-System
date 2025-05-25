package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Employee;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
  Optional<Employee> findByEmail(@Email String email);

  @NativeQuery("select employee.* from employee ,role where employee" +
          ".department_id=:deptId " +
          "and employee.role_id=role.role_id and role.rank<2")
  List<Employee> findAllByDeptAndRole(Integer deptId);
}
