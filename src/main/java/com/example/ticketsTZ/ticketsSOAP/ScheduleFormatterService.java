package com.example.ticketsTZ.ticketsSOAP;

import com.example.ticketsTZ.ticketsREST.entities.Ticket;
import com.example.ticketsTZ.ticketsREST.repositories.TzKTELabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleFormatterService {
    private final TzKTELabRepository repository;
    private List<Ticket> schedule;
    @Autowired
    public ScheduleFormatterService(TzKTELabRepository repository){
        this.repository = repository;
        this.schedule = new ArrayList<>();
    }
    public String sortByTimeTicketSchedule(int ticketDurationOfMinutes,
                               XMLGregorianCalendar XMLDateTimeScheduleStart,
                               XMLGregorianCalendar XMLDateTimeScheduleEnd){

            Duration ticketDuration = Duration.ofMinutes(ticketDurationOfMinutes);

            LocalDateTime dateTimeScheduleStart = LocalDateTime.ofInstant
                    (XMLDateTimeScheduleStart.toGregorianCalendar().toInstant(), ZoneId.systemDefault());

            LocalDateTime dateTimeScheduleEnd = LocalDateTime.ofInstant
                    (XMLDateTimeScheduleEnd.toGregorianCalendar().toInstant(), ZoneId.systemDefault());


        while (dateTimeScheduleStart.isBefore(dateTimeScheduleEnd)){
            Ticket ticket = Ticket.builder()
                    .ticketStartDateTime(dateTimeScheduleStart)
                    .ticketEndDateTime(dateTimeScheduleStart.plus(ticketDuration))
                    .build();
            this.schedule.add(ticket);


            dateTimeScheduleStart = dateTimeScheduleStart.plus(ticketDuration);
        }

        return this.repository.saveTicketSchedule(this.schedule);
    }
}
