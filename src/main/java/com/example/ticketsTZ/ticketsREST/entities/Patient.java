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
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long patientId;

    @Column(name = "patient_uuid")
    private UUID patientUUID;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private List<Ticket> patientTicketList;

    @Embedded
    private FullName patientFullName;
}
