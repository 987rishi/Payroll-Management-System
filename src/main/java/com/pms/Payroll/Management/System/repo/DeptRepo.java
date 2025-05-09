package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DeptRepo extends JpaRepository<Department, Integer> {

  Optional<Department> findByDeptName(String deptName);
}
