package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@Configuration
public class NativeJPAQueryApplication {

    private static Logger LOG = LoggerFactory.getLogger(NativeJPAQueryApplication.class);

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
        factory.setPersistenceUnitName("example-unit");
        return factory;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(NativeJPAQueryApplication.class);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        System.getProperties().list(pw);
        LOG.debug("System properties - {}", sw.toString());
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
        try {
            nativeQuery(em, "SHOW TABLES");
            nativeQuery(em, "SHOW COLUMNS from Person");

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void nativeQuery(EntityManager em, String s) {
        LOG.debug("---------------------------%n'{}'%n", s);
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
}