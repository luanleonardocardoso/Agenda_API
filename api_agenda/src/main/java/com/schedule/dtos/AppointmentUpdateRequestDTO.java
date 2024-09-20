package com.schedule.dtos;

import jakarta.validation.constraints.NotNull;

public class AppointmentUpdateRequestDTO extends AppointmentRequestDTO {

    @NotNull(message = "O campo 'idSchedule' é obrigatório")
    private final Integer idSchedule;

    // Construtor que chama o super do AppointmentRequestDTO
    public AppointmentUpdateRequestDTO(Integer idSchedule, String daySchedule, String initialScheduledTime, String finalScheduledTime, String description) {
        super(daySchedule, initialScheduledTime, finalScheduledTime, description);
        this.idSchedule = idSchedule;
    }

    // Getter
    public Integer getIdSchedule() {
        return idSchedule;
    }
}
