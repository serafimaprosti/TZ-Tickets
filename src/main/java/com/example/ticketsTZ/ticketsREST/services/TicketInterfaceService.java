package com.example.ticketsTZ.ticketsREST.services;

import com.example.ticketsTZ.ticketsREST.entities.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketInterfaceService {
    List<Ticket> getAllTicketsByPatientIdOrUUID(String patientIdOrUUID);

    int setTicketPatientId(Long patientId, Long ticketId);

    List<Ticket> showAllEmptyTicketsByDoctorId(Long doctorId, LocalDateTime ticketDateTime);
}
