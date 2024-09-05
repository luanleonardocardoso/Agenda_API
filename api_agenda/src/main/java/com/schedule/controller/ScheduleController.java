package com.schedule.controller;

import com.schedule.dtos.AppointmentRequestDTO;
import com.schedule.dtos.AppointmentUpdateRequestDTO;
import com.schedule.entities.Appointment;
import com.schedule.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleController {

    @Autowired
    private AppointmentService appointmentService;

    // Inserir novo compromisso
    @PostMapping("/appointments/add")
    public ResponseEntity<?> insertAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequest) {
        boolean isInserted = appointmentService.addAppointment(appointmentRequest);
        if (isInserted) {
            return ResponseEntity.ok("Appointment added successfully");
        } else {
            return ResponseEntity.status(409).body("Você já possui atividades neste período de tempo.");
        }
    }

    // Atualizar compromisso
    @PostMapping("/appointments/update")
    public ResponseEntity<?> updateAppointment(@Valid @RequestBody AppointmentUpdateRequestDTO appointmentRequest) {
        boolean isUpdated = appointmentService.updateAppointment(appointmentRequest);
        if (isUpdated) {
            return ResponseEntity.ok("Appointment updated successfully");
        } else {
            return ResponseEntity.status(409).body("Você já possui atividades neste período de tempo.");
        }
    }

    // Excluir compromisso
    @DeleteMapping("/appointments/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") int idSchedule) {
        boolean isDeleted = appointmentService.deleteAppointment(idSchedule);
        if (isDeleted) {
            return ResponseEntity.ok("Appointment deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Appointment not found");
        }
    }

    // Verificar compromissos em uma data específica
    @GetMapping("/appointments")
    public ResponseEntity<List<Map<String, Object>>> checkAppointments(@RequestParam("date") String date) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(date);
        List<Map<String, Object>> results = appointments.stream().map(appointment -> {
            Map<String, Object> appointmentMap = new HashMap<>();
            appointmentMap.put("idSchedule", appointment.getIdSchedule());
            appointmentMap.put("daySchedule", appointment.getDaySchedule());
            appointmentMap.put("initialScheduledTime", appointment.getInitialScheduledTime());
            appointmentMap.put("finalScheduledTime", appointment.getFinalScheduledTime());
            appointmentMap.put("description", appointment.getDescription());
            return appointmentMap;
        }).toList();

        return ResponseEntity.ok(results);
    }

    // Verificar se existe algum compromisso em uma data específica
    @GetMapping("/appointments/check")
    public ResponseEntity<Boolean> hasAppointment(@RequestParam("date") String date) {
        boolean hasAppointment = appointmentService.hasAppointmentOnDate(date);
        return ResponseEntity.ok(hasAppointment);
    }

    // Verificar compromissos de um mês
    @GetMapping("/appointments/month")
    public ResponseEntity<List<Map<String, Object>>> getAppointmentsByMonth(@RequestParam("yearMonth") String yearMonth) {
        List<Appointment> appointments = appointmentService.getAppointmentsByMonth(yearMonth);
        List<Map<String, Object>> results = appointments.stream().map(appointment -> {
            Map<String, Object> appointmentMap = new HashMap<>();
            appointmentMap.put("daySchedule", appointment.getDaySchedule());
            return appointmentMap;
        }).toList();

        return ResponseEntity.ok(results);
    }
}
