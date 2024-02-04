package com.example.ticketsTZ.ticketsREST.repositories;

import com.example.ticketsTZ.ticketsREST.entities.Ticket;
import com.example.ticketsTZ.ticketsREST.exceptions.NoSuchPatientException;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor // lombok constructor with SessionFactory final
@Component
public class TzKTELabRepository implements TicketInterfaceRepository {



    private final SessionFactory factory;
    private final static int BATCH_SIZE = 5;


    @Override
    @Transactional
    public List<Ticket> findAllTicketsByPatientId(Long patientId){

        Session session = this.factory.getCurrentSession();

        TypedQuery<Ticket> query
                = session.createQuery("SELECT t FROM Ticket t WHERE t.ticketPatient.patientId = :patientId"
                , Ticket.class);
        query.setParameter("patientId", patientId);

        EntityGraph<?> entityGraph = session.createEntityGraph("ticket.patient.doctor");
        query.setHint("jakarta.persistence.fetchgraph", entityGraph);



        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Ticket> findAllTicketsByPatientUUID(UUID patientUUID) {

        Session session = this.factory.getCurrentSession();

        TypedQuery<Ticket> query
                = session.createQuery("SELECT t FROM Ticket t WHERE t.ticketPatient.patientUUID = :patientUUID"
                , Ticket.class);
        query.setParameter("patientUUID", patientUUID);

        EntityGraph<?> entityGraph = session.createEntityGraph("ticket.patient.doctor");
        query.setHint("jakarta.persistence.fetchgraph", entityGraph);



        return query.getResultList();
    }

    @Override
    @Transactional
    public int setTicketPatientId(Long patientId, Long ticketId) {

        Session session = this.factory.getCurrentSession();

        NativeQuery<Boolean> haveThisPatient
                = session.createNativeQuery
                ("SELECT EXISTS(SELECT 1 FROM patients WHERE patient_id = :patientId);", Boolean.class);
        haveThisPatient.setParameter("patientId", patientId);

        Boolean flag =  haveThisPatient.getSingleResult();


        if (flag.equals(false))
            throw new NoSuchPatientException("have no patients with id = " + patientId);


        NativeQuery<Ticket> nativeQuery
                = session.createNativeQuery
                ("UPDATE tickets SET patient_id = :patientId WHERE ticket_id = " + ticketId, Ticket.class);
        nativeQuery.setParameter("patientId", patientId);

        return nativeQuery.executeUpdate();
    }

    @Override
    @Transactional
    public List<Ticket> getEmptyTicketsByDoctorIdAndTicketDate(Long doctorId, LocalDateTime dayStart) {


        Session session = this.factory.getCurrentSession();

        LocalDateTime dayEnd = dayStart.plusDays(1);
        TypedQuery<Ticket> query =
                session.createQuery("SELECT t FROM Ticket t WHERE t.ticketStartDateTime > :dayStart AND t.ticketStartDateTime < :dayEnd AND t.ticketDoctor.doctorId = :doctorId AND t.ticketPatient.patientId IS NULL" , Ticket.class);

        EntityGraph<?> entityGraph = session.createEntityGraph("ticket.patient.doctor");
        query.setHint("jakarta.persistence.fetchgraph", entityGraph);

        query.setParameter("doctorId", doctorId);
        query.setParameter("dayStart", dayStart);
        query.setParameter("dayEnd", dayEnd);



        return query.getResultList();
    }
    @Override
    @Transactional
    public String saveTicketSchedule(List<Ticket> schedule){

        try {
            Session session = this.factory.getCurrentSession();
            session.setJdbcBatchSize(BATCH_SIZE);

            for (int i = 1; i <= schedule.size() - 1; i++) {
                session.persist(schedule.get(i));
                if (i % BATCH_SIZE == 0) {
                    // Flush and clear the cache every batch
                    session.flush();
                    session.clear();
                }
            }
<<<<<<< HEAD:src/main/java/com/example/ticketsTZ/ticketsREST/repositories/TzKTELabRepository.java
            return "success " + schedule.size() + " tickets saved";
=======
            return "success " + schedule.size() + " tickets was saved";
>>>>>>> f784acdaa5131125e4600220de28ad1b51ae69a5:src/main/java/com/example/myServices/tzKTELabRESTService/repository/TzKTELabRepository.java
        }catch (HibernateException e){
            return "filed";
        }
    }


}
