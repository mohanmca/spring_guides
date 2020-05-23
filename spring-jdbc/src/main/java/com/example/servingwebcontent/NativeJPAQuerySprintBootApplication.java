package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {NativeJPAQueryApplication.class})
})
public class NativeJPAQuerySprintBootApplication {

    private static final Logger LOG = LoggerFactory.getLogger(NativeJPAQuerySprintBootApplication.class);

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private DataSource ds;

//    private EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(NativeJPAQuerySprintBootApplication.class, args);
    }

    private static void nativeQuery(EntityManager em, String s) {
        LOG.info("---------------------------%n'{}'%n", s);
        Query query = em.createNativeQuery(s);
        List list;
        list = query.getResultList();
        for (Object o : list) {
            if (o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            } else {
                System.out.println(o);
            }
        }
    }

//    @Bean
//    @Profile("springBoot")
//    public EntityManagerFactory entityManagerFactory() {
//        return entityManagerFactoryBean().getObject();
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource ds) {
//        LocalEntityManagerFactoryBean em= new LocalEntityManagerFactoryBean();
//        em.setDataSource(ds);
//        em.setPackagesToScan("com.example.core");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        //em.setJpaProperties(additionalProperties());
//
//        return em;
//    }


    @Bean
    public CommandLineRunner executeThis() {
        return args -> {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            System.getProperties().list(pw);
            LOG.info("System properties - {}", sw.toString());
            //EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
            EntityManager em = emf.createEntityManager();
            try {
                nativeQuery(em, "SHOW TABLES");
                nativeQuery(em, "SHOW COLUMNS from Person");

            } finally {
                em.close();
                emf.close();
            }
        };
    }
}