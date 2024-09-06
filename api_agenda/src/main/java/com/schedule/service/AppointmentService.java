package com.schedule.service;

import com.schedule.dtos.AppointmentRequestDTO;
import com.schedule.dtos.AppointmentUpdateRequestDTO;
import com.schedule.entities.Appointment;
import com.schedule.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Método para inserir um compromisso
    public boolean addAppointment(AppointmentRequestDTO appointmentRequest) {
        Date daySchedule = Date.valueOf(appointmentRequest.getDaySchedule()); // Converter para java.sql.Date
        String initialScheduledTime = appointmentRequest.getInitialScheduledTime();
        String finalScheduledTime = appointmentRequest.getFinalScheduledTime();

        // Validação de conflito de horários
        int count = appointmentRepository.countByDayScheduleAndInitialScheduledTimeLessThanAndFinalScheduledTimeGreaterThan(
                daySchedule, finalScheduledTime, initialScheduledTime);

        if (count > 0) {
            return false; // Conflito de horário
        }

        // Inserir compromisso
        Appointment appointment = new Appointment();
        appointment.setDaySchedule(daySchedule);
        appointment.setInitialScheduledTime(initialScheduledTime);
        appointment.setFinalScheduledTime(finalScheduledTime);
        appointment.setDescription(appointmentRequest.getDescription());

        appointmentRepository.save(appointment);
        return true; // Inserido com sucesso
    }

    // Método para atualizar um compromisso
    public boolean updateAppointment(AppointmentUpdateRequestDTO appointmentRequest) {
        Date daySchedule = Date.valueOf(appointmentRequest.getDaySchedule()); // Converter para java.sql.Date
        String initialScheduledTime = appointmentRequest.getInitialScheduledTime();
        String finalScheduledTime = appointmentRequest.getFinalScheduledTime();
        int idSchedule = appointmentRequest.getIdSchedule();

        // Validação de conflito de horários (exceto o compromisso atual)
        int count = appointmentRepository.countByDayScheduleAndIdScheduleNotAndInitialScheduledTimeLessThanAndFinalScheduledTimeGreaterThan(
                daySchedule, idSchedule, finalScheduledTime, initialScheduledTime);

        if (count > 0) {
            return false; // Conflito de horário
        }

        // Atualizar compromisso
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(idSchedule);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setDaySchedule(daySchedule);
            appointment.setInitialScheduledTime(initialScheduledTime);
            appointment.setFinalScheduledTime(finalScheduledTime);
            appointment.setDescription(appointmentRequest.getDescription());

            appointmentRepository.save(appointment);
            return true; // Atualizado com sucesso
        }

        return false; // Não encontrado
    }

    // Método para deletar um compromisso
    public boolean deleteAppointment(int idSchedule) {
        if (appointmentRepository.existsById(idSchedule)) {
            appointmentRepository.deleteById(idSchedule);
            return true; // Deletado com sucesso
        }
        return false; // Não encontrado
    }

    // Método para verificar compromissos em uma data específica
    public List<Appointment> getAppointmentsByDate(String date) {
        Date daySchedule = Date.valueOf(date);
        return appointmentRepository.findByDaySchedule(daySchedule);
    }

    // Método para verificar se existe algum compromisso em uma data específica
    public boolean hasAppointmentOnDate(String date) {
        Date daySchedule = Date.valueOf(date);
        return !appointmentRepository.findByDaySchedule(daySchedule).isEmpty();
    }

    // Método para buscar compromissos de um mês
    public List<Appointment> getAppointmentsByMonth(String yearMonth) {
        LocalDate startDate = LocalDate.parse(yearMonth + "-01");
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return appointmentRepository.findByDayScheduleBetween(Date.valueOf(startDate), Date.valueOf(endDate));
    }
}