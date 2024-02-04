package com.example.ticketsTZ.ticketsREST.repositories;

import com.example.ticketsTZ.ticketsREST.entities.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TicketInterfaceRepository {
    List<Ticket> findAllTicketsByPatientId(Long patientId);
    List<Ticket> findAllTicketsByPatientUUID(UUID patientUUID);
    int setTicketPatientId(Long patientId, Long ticketId);
    List<Ticket> getEmptyTicketsByDoctorIdAndTicketDate(Long doctorId, LocalDateTime dayStart);
    String saveTicketSchedule(List<Ticket> schedule);
}
