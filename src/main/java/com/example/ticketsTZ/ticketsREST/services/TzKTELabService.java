package com.example.ticketsTZ.ticketsREST.services;

import com.example.ticketsTZ.ticketsREST.entities.Ticket;
import com.example.ticketsTZ.ticketsREST.repositories.TicketInterfaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor // lombok constructor with myRepository final
@Service
public class TzKTELabService implements TicketInterfaceService {


    private final TicketInterfaceRepository repository; // interface

    @Override
    public List<Ticket> getAllTicketsByPatientIdOrUUID(String patientIdOrUUID) {

        try {

            return this.repository.findAllTicketsByPatientId(Long.parseLong(patientIdOrUUID));

        }catch (NumberFormatException e){

            return this.repository.findAllTicketsByPatientUUID(UUID.fromString(patientIdOrUUID));

        }

    }

    @Override
    public int setTicketPatientId(Long patientId, Long ticketId) {
        return this.repository.setTicketPatientId(ticketId, patientId);
    }

    @Override
    public List<Ticket> showAllEmptyTicketsByDoctorId(Long doctorId, LocalDateTime ticketDateTime) {
        return this.repository.getEmptyTicketsByDoctorIdAndTicketDate(doctorId, ticketDateTime);
    }

}
