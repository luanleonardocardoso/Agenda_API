package com.schedule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idSchedule")
	    private Integer idSchedule;

	    @Column(name = "daySchedule", nullable = false)
	    private java.sql.Date daySchedule;

	    @Column(name = "initialScheduledTime", nullable = false)
	    private String initialScheduledTime;

	    @Column(name = "finalScheduledTime", nullable = false)
	    private String finalScheduledTime;

	    @Column(name = "description")
	    private String description;

    // Getters and Setters
    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public java.sql.Date getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(java.sql.Date daySchedule) {
        this.daySchedule = daySchedule;
    }

    public String getInitialScheduledTime() {
        return initialScheduledTime;
    }

    public void setInitialScheduledTime(String initialScheduledTime) {
        this.initialScheduledTime = initialScheduledTime;
    }

    public String getFinalScheduledTime() {
        return finalScheduledTime;
    }

    public void setFinalScheduledTime(String finalScheduledTime) {
        this.finalScheduledTime = finalScheduledTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

