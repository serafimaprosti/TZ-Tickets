package com.example.ticketsTZ.ticketsREST.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class DTODoctorDate {
    private long doctorId;
    private LocalDateTime date;

    @JsonProperty(value = "doctor_id")
    public long getDoctorId() {
        return doctorId;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty(value = "date")
    public LocalDateTime getDate(){
        return this.date;
    }
}
