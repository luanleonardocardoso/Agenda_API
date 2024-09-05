package com.schedule.dtos;

import jakarta.validation.constraints.NotNull;

public class AppointmentUpdateRequestDTO extends AppointmentRequestDTO {

    @NotNull(message = "idSchedule is required")
    private Integer idSchedule;

    // Getters and Setters
    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }
}
