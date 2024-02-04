package com.example.ticketsTZ.ticketsREST.controllers;

import com.example.ticketsTZ.ticketsREST.dto.DTODoctorDate;
import com.example.ticketsTZ.ticketsREST.entities.Ticket;
import com.example.ticketsTZ.ticketsREST.services.TicketInterfaceService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class TicketController {

    private final TicketInterfaceService service; // interface

    @GetMapping(path = "/tickets/{patientIdOrUUID}")
    public List<Ticket> showAllTicketsByPatientId(@PathVariable(name = "patientIdOrUUID") String patientIdOrUUID){
        return this.service.getAllTicketsByPatientIdOrUUID(patientIdOrUUID);
    }

    @PutMapping(path = "/tickets/{ticketId}/{patientId}")
    public int takeTicket(@PathVariable(name = "ticketId") Long patientId,
                          @PathVariable(name = "patientId") Long ticketId) {

        return this.service.setTicketPatientId(patientId, ticketId);
    }

    @GetMapping(path = "/tickets")
    public List<Ticket> showAllEmptyTicketsByDoctorId(@RequestBody DTODoctorDate dto){

        return this.service.showAllEmptyTicketsByDoctorId(dto.getDoctorId(), dto.getDate());
    }

}
