package com.ets.service;

import org.springframework.stereotype.Service;
import com.ets.dto.*;
import com.ets.model.*;
import com.ets.repository.*;
import com.ets.statusenum.TaskStatus;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
public class DashboardService {

    private final Employeedashboard taskRepo;
    private final EmpDashboardAttendanceRepo attendanceRepo;
    private final EmpDashboardCoding performanceRepo;

    public DashboardService(Employeedashboard taskRepo,
                            EmpDashboardAttendanceRepo attendanceRepo,
                            EmpDashboardCoding performanceRepo) {
        this.taskRepo = taskRepo;
        this.attendanceRepo = attendanceRepo;
        this.performanceRepo = performanceRepo;
    }

    public EmployeeDashboardDTO getDashboard(Long empId) {

        DashboardSummaryDTO summary = new DashboardSummaryDTO(
                taskRepo.countByEmployeeIdAndStatus(empId, TaskStatus.NEW),
                taskRepo.countByEmployeeIdAndStatus(empId, TaskStatus.ACCEPTED),
                taskRepo.countByEmployeeIdAndStatus(empId, TaskStatus.COMPLETED),
                taskRepo.countByEmployeeIdAndStatus(empId, TaskStatus.FAILED)
        );

        LocalDate start = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate end = start.plusDays(6);

        Map<DayOfWeek, Double> attendanceMap =
                attendanceRepo.findByEmployeeIdAndDateBetween(empId, start, end)
                        .stream()
                        .collect(Collectors.groupingBy(
                                a -> a.getDate().getDayOfWeek(),
                                Collectors.summingDouble(EmpDashboardAttendance::getHoursWorked)
                        ));

        List<DayValueDTO> attendance =
                Arrays.stream(DayOfWeek.values())
                        .map(d -> new DayValueDTO(
                                d.name().substring(0, 3),
                                attendanceMap.getOrDefault(d, 0.0)
                        ))
                        .toList();

        Map<DayOfWeek, Double> performanceMap =
                performanceRepo.findByEmployeeIdAndDateBetween(empId, start, end)
                        .stream()
                        .collect(Collectors.groupingBy(
                                p -> p.getDate().getDayOfWeek(),
                                Collectors.summingDouble(EmpDashboardCodingperform::getScore)
                        ));

        List<DayValueDTO> performance =
                Arrays.stream(DayOfWeek.values())
                        .map(d -> new DayValueDTO(
                                d.name().substring(0, 3),
                                performanceMap.getOrDefault(d, 0.0)
                        ))
                        .toList();

        return new EmployeeDashboardDTO(summary, attendance, performance);
    }
}