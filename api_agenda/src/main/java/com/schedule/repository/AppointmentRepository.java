package com.schedule.repository;

import com.schedule.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Método para encontrar compromissos em uma data específica
    List<Appointment> findByDaySchedule(Date daySchedule);

    // Método para verificar conflitos de horário
    int countByDayScheduleAndInitialScheduledTimeLessThanAndFinalScheduledTimeGreaterThan(
            Date daySchedule, String finalScheduledTime, String initialScheduledTime);

    // Método para verificar conflitos de horário excluindo um ID específico
    int countByDayScheduleAndIdScheduleNotAndInitialScheduledTimeLessThanAndFinalScheduledTimeGreaterThan(
            Date daySchedule, Integer idSchedule, String finalScheduledTime, String initialScheduledTime);

    // Método para buscar compromissos em um intervalo de datas
    List<Appointment> findByDayScheduleBetween(Date startOfMonth, Date endOfMonth);
}