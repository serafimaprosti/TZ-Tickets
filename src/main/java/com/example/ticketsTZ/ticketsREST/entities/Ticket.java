package com.example.ticketsTZ.ticketsREST.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tickets")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "ticket.patient.doctor",
                attributeNodes = {
                @NamedAttributeNode(value = "ticketPatient"), @NamedAttributeNode(value = "ticketDoctor")
        })
})
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ticketId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor ticketDoctor;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient ticketPatient;

    @Column(name = "ticket_date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ticketStartDateTime;

    @Column(name = "ticket_date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ticketEndDateTime;

    public Duration calculateTicketDuration(){
        return Duration.between(this.ticketStartDateTime, this.ticketEndDateTime);
    }
}
