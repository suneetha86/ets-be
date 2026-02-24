package com.ets.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalDateTime loginTime;

    private LocalDateTime logoutTime;

    private String workingHours;

    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
