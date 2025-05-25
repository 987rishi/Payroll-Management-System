package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.dto.GeneratePayslipsDto;
import com.pms.Payroll.Management.System.models.PayrollRecord;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRecordRepo extends JpaRepository<PayrollRecord,Long> {

  @NativeQuery("select pr.* from payroll_record pr, employee e where e" +
          ".email=:empEmail and e.employee_id=pr.employee_id")
  List<PayrollRecord> findAllByEmpEmail(@Email String empEmail);


  //
//  { empId: 'EMP001', empName: 'Alice Smith', dept: 'HR', attendancePercent: 95, currentPaySalary: 6000, payPeriod: '2024-07', conduct: 'Good', remarks: '', salaryPaid: false },
//  { empId: 'EMP002', empName: 'Bob Johnson', dept: 'HR', attendancePercent: 98, currentPaySalary: 6200, payPeriod: '2024-07', conduct: 'Excellent', remarks: 'Top performer', salaryPaid: true },
//          ];
  @NativeQuery(
          value = "SELECT "
                  + "e.employee_id         AS empId, "
                  + "e.employee_fname || ' ' || e.employee_lname AS empName, "
                  + "d.dept_name           AS dept, "
                  + "pr.net_salary         AS currentPaySalary, "
                  + "pr.pay_date_start     AS payDateStart, "
                  + "pr.pay_date_end       AS payDateEnd, "
                  + "a.conduct             AS conduct, "
                  + "a.remarks             AS remarks, "
                  + "pr.salary_paid        AS salaryPaid, "
                  + "pr.payroll_id AS payrollId "
                  + "FROM employee e "
                  + "JOIN department d     ON e.department_id = d.dept_id "
                  + "JOIN payroll_record pr ON e.employee_id   = pr.employee_id "
                  + "JOIN attendance a     ON e.employee_id   = a.employee_id "
                  + "JOIN role r on r.role_id=e.role_id "
                  + "WHERE e.department_id =:deptId and r.rank<:hrRank"
  )
  List<GeneratePayslipsDto> findAllGeneratePayslipsView(@Param("deptId") Integer deptId, @Param("hrRank") Integer hrRank);


  @NativeQuery(
          value = "SELECT "
                  + "e.employee_id         AS empId, "
                  + "e.employee_fname || ' ' || e.employee_lname AS empName, "
                  + "d.dept_name           AS dept, "
                  + "pr.net_salary         AS currentPaySalary, "
                  + "pr.pay_date_start     AS payDateStart, "
                  + "pr.pay_date_end       AS payDateEnd, "
                  + "a.conduct             AS conduct, "
                  + "a.remarks             AS remarks, "
                  + "pr.salary_paid        AS salaryPaid, "
                  + "pr.payroll_id AS payrollId "
                  + "FROM employee e "
                  + "JOIN department d     ON e.department_id = d.dept_id "
                  + "JOIN payroll_record pr ON e.employee_id   = pr.employee_id "
                  + "JOIN attendance a     ON e.employee_id   = a.employee_id "
                  + "JOIN role r on r.role_id=e.role_id "
                  + "WHERE  r.rank<:rank and r.rank>1"
  )
  List<GeneratePayslipsDto> findAllGeneratePayslipsForFM(Integer rank);
}
