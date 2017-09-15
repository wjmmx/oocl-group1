package com.group1.booking.configurations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateContext{
	private SessionFactory factory;
	
	@SuppressWarnings("deprecation")
	public HibernateContext() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public SessionFactory GetSessionFactory() {		
		System.out.println("test");
		return factory;
	}
}



//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan({"com.oocl.models"})
//@PropertySource(value = {"classpath:application.properties"})
//public class HibernateConfiguration {
//
//	@Autowired
//    private Environment environment;
//	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory(){
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(new String[] { "com.oocl.models" });
//        sessionFactory.setHibernateProperties(hibernateProperties());
//		
//		return sessionFactory;
//	}
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername("jdbc.username");
//        dataSource.setPassword("jdbc.password");
//        return dataSource;
//    }
//    
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//
//        return properties;        
//    }
//	
//}
