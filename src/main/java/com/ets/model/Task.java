//package com.ets.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String date;
//    private String time;
//    private String taskdescription;
//    private String challangesfaced;
//    private String uploadscreenshots;
//    private String solutionimplemented;
//    private String uploadsolutiondocuments;
//    private String status;
//}







package com.ets.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ets.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private LocalDate date;

    private LocalTime time;

    private String taskDescription;

    private String challengesFaced;

    private String uploadScreenshots;

    private String solutionImplemented;

    private String uploadSolutionDocuments;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
  
}

