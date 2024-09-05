package com.schedule.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AppointmentRequestDTO {

    @NotBlank(message = "daySchedule is required")
    private String daySchedule;

    @NotBlank(message = "initialScheduledTime is required")
    private String initialScheduledTime;

    @NotBlank(message = "finalScheduledTime is required")
    private String finalScheduledTime;

    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    // Getters and Setters
    public String getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(String daySchedule) {
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
