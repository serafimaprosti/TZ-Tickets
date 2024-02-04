package com.example.ticketsTZ.ticketsSOAP;

import com.example.tz_kte_lab_soap.GetScheduleResponse;
import com.example.tz_kte_lab_soap.GetScheduleRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

<<<<<<< HEAD:src/main/java/com/example/ticketsTZ/ticketsSOAP/ScheduleEndpoint.java

=======
>>>>>>> f784acdaa5131125e4600220de28ad1b51ae69a5:src/main/java/com/example/myServices/tzKTELabSOAPService/ScheduleEndpoint.java
@Endpoint
public class ScheduleEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/tz_kte_lab_soap";
    private final ScheduleFormatterService service;
    @Autowired
    public ScheduleEndpoint(ScheduleFormatterService service){
        this.service = service;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getScheduleRequest")
    @ResponsePayload
    public GetScheduleResponse getScheduleResponse(@RequestPayload GetScheduleRequest request) {

        GetScheduleResponse response = new GetScheduleResponse();

        String status = service.sortByTimeTicketSchedule(request.getDurationOfMinutes()
                , request.getStartDateTime(), request.getEndDateTime());

        response.setStatus(status);


        return response;
    }
}
