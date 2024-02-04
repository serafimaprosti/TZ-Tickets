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