package com.example.ticketsTZ.ticketsREST.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


import javax.sql.DataSource;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@EnableWs
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class Configuration extends WsConfigurerAdapter {
    @Bean
    public DataSource dataSource(){

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://localhost:5432/admin2");
        config.setUsername("postgres");
        config.setPassword("admin1");

        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);

        config.setConnectionTimeout(20000);
        config.setMaxLifetime(900000);

        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

        factory.setDataSource(this.dataSource());
        factory.setPackagesToScan("com.example.myServices.tzKTELabRESTService.entities");

        Properties factoryHibernateProp = new Properties();
        factoryHibernateProp.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
//        factoryHibernateProp.setProperty("hibernate.hbm2ddl.auto", "create");
//        factoryHibernateProp.setProperty("hibernate.show_sql","true");
//        factoryHibernateProp.setProperty("hibernate.format_sql", "true");
//        factoryHibernateProp.setProperty("hibernate.generate_statistics", "true");
//        factoryHibernateProp.setProperty("hibernate.connection.isolation", "TRANSACTION_READ_UNCOMMITTED");

        factory.setHibernateProperties(factoryHibernateProp);


        return factory;
    }
    @Bean
    public HibernateTransactionManager transactionManager(){

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(this.sessionFactory().getObject());

        return transactionManager;
    }

    // soap part

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "schedule")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema scheduleSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SchedulePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/tz_kte_lab_soap");
        wsdl11Definition.setSchema(scheduleSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema scheduleSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schedule.xsd"));
    }

}
