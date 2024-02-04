package com.example.ticketsTZ.ticketsREST.entities;

import com.example.ticketsTZ.ticketsREST.entities.embedded.FullName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long doctorId;

    @Column(name = "doctor_uuid")
    private UUID doctorUUID;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private List<Ticket> doctorTicketList;

    @Embedded
    private FullName doctorFullName;
}
