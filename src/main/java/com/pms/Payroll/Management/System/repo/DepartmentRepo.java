package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Department;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
    @NativeQuery("select * from department where dept_name=:departmentName")
    Optional<Department> findByDeptName(@NotNull String departmentName);
}
