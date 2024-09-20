package com.schedule.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AppointmentRequestDTO {

    @NotBlank(message = "O campo 'daySchedule' é obrigatório")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato yyyy-MM-dd")
    private final String daySchedule;

    @NotBlank(message = "O campo 'initialScheduledTime' é obrigatório")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "O horário inicial deve estar no formato HH:mm")
    private final String initialScheduledTime;

    @NotBlank(message = "O campo 'finalScheduledTime' é obrigatório")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "O horário final deve estar no formato HH:mm")
    private final String finalScheduledTime;

    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres")
    private final String description;

    // Construtor com todos os campos
    public AppointmentRequestDTO(String daySchedule, String initialScheduledTime, String finalScheduledTime, String description) {
        this.daySchedule = daySchedule;
        this.initialScheduledTime = initialScheduledTime;
        this.finalScheduledTime = finalScheduledTime;
        this.description = description;
    }

    // Getters
    public String getDaySchedule() {
        return daySchedule;
    }

    public String getInitialScheduledTime() {
        return initialScheduledTime;
    }

    public String getFinalScheduledTime() {
        return finalScheduledTime;
    }

    public String getDescription() {
        return description;
    }
}
