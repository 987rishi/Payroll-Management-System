package com.pms.Payroll.Management.System.helpers;

import com.pms.Payroll.Management.System.models.Attendance;
import com.pms.Payroll.Management.System.repo.AttendanceRepo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public final class Utility {


    public static <T,K> T findOrThrow(String role, JpaRepository<T,K> repo, @NotNull K searchKey) {
        return repo.findById(searchKey).orElseThrow(()-> new NoSuchElementException(String.format("%s DOES NOT EXIST WITH ID :%s",role,searchKey)));
    }
}
