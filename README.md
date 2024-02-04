java 17 <br>
spring boot <br>

hibernate <br>
hikariCP<br>
spring orm <br>
postgresql<br>

spring web <br>
spring web services <br>
wsdl4j<br>

lombok<br>
<br>
SQL 
```sql
CREATE TABLE IF NOT EXISTS doctors (
   doctor_id BIGSERIAL NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   surname VARCHAR(255),
   doctor_uuid UUID,
   PRIMARY KEY (doctor_id)
);

CREATE TABLE IF NOT EXISTS patients (
   patient_id BIGSERIAL NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   surname VARCHAR(255),
   patient_uuid UUID,
   PRIMARY KEY (patient_id)
);

CREATE TABLE IF NOT EXISTS tickets (
   ticket_id BIGSERIAL NOT NULL,
   ticket_date_end TIMESTAMP(6),
   ticket_date_start TIMESTAMP(6),
   doctor_id BIGINT,
   patient_id BIGINT,
   PRIMARY KEY (ticket_id),
   CONSTRAINT FKrtv97uj8j6mqn42q7ebdo4rc5 FOREIGN KEY (doctor_id) REFERENCES doctors,
   CONSTRAINT FKclh8sgbev455tku5nfid52es9 FOREIGN KEY (patient_id) REFERENCES patients
);
```
также он есть в файле tz-KTE-Lab/src/main/resources/schema.sql


<br><br>


http://localhost:8080/ws/schedule.wsdl -> wsdl<br>
GET http://localhost:8080/api/tickets/{id или uuid} -> получить пациента по id или uuid<br>
PUT http://localhost:8080/api/tickets/{id тикета}/{id пациента} -> занять тикет по его id<br>
GET http://localhost:8080/api/tickets + в теле запроса JSON -> получение свободных тикетов к врачу с id 1 + на указанную дату<br>
JSON
```txt
{
  "doctor_id" : 1,
  "date" : "yyyy-MM-ddTHH:mm:ss"
}
```


soap метод getSchedule<br>
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tz="http://example.com/tz_kte_lab_soap"><br>
   <soapenv:Header/><br>
   <soapenv:Body><br>
      <tz:getScheduleRequest><br>
         <tz:durationOfMinutes>?</tz:durationOfMinutes>   -> вместо <<?>> длительность тикета в минутах<br>
         <tz:startDateTime>?</tz:startDateTime>           -> вместо <<?>> начало расписания формат -> yyyy-MM-ddTHH:mm:ss<br>
         <tz:endDateTime>?</tz:endDateTime>               -> вместо <<?>> конец расписания формат  -> yyyy-MM-ddTHH:mm:ss<br>
      </tz:getScheduleRequest><br>
   </soapenv:Body><br>
</soapenv:Envelope><br>

<!--ограничение на <<не рабочее>> время не сделал, не успел но думаю не сложно реализуется-->
```
<br>


оставил таргет              -> нужна для soap там классы<br>
плохая обработка исключений -> было мало свободного времени<br>


 



