package com.cshop.cs_helper.db;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {
//
//	public static final Logger LOG = LogManager.getFormatterLogger();
	public static EntityManager ENTITY_MANAGER;
	public static EntityManagerFactory EM_FACTORY;
//
//	
//
//	@Bean
//	@Primary
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(String dbPropertiesFile) {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		DBProperties dbProperties = null;
//		Properties properties = new Properties();
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//
//		try {
//			dbProperties = GetDBProperties(dbPropertiesFile);
//
//			dataSource.setDriverClassName(dbProperties.getDriverClassName());
//			dataSource.setUrl(dbProperties.getUrl());
//			dataSource.setUsername(dbProperties.getUsername());
//			dataSource.setPassword(dbProperties.getPassword());
//
//			em.setDataSource(dataSource);
//			em.setPackagesToScan("com.cshop");
//			em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//			properties.put("hibernate.dialect", dbProperties.getHibernateDialect());
//			properties.put("hibernate.show_sql", dbProperties.getShowSql());
//			properties.put("hibernate.hbm2ddl.auto", dbProperties.getHbmtoddl());
//			properties.put("hibernate.connection.characterEncoding", "utf8");
//
//			em.setJpaProperties(properties);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return em;
//	}

	public static DBProperties GetDBProperties(String urlPath) {
		Properties properties = new Properties();
		DBProperties dbProperties = new DBProperties();
		try (FileInputStream input = new FileInputStream(urlPath)) {
			properties.load(input);
			dbProperties.setDriverClassName(properties.getProperty("driver-class-name"));
			dbProperties.setUrl(properties.getProperty("url"));
			dbProperties.setUsername(properties.getProperty("username"));
			dbProperties.setPassword(properties.getProperty("password"));
			dbProperties.setHibernateDialect(properties.getProperty("hibernate.dialect"));
			dbProperties.setHbmtoddl(properties.getProperty("hibernate.hbm2ddl.auto"));
			dbProperties.setShowSql(properties.getProperty("hibernate.show_sql"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbProperties;
	}

//	@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(emf);
//		return transactionManager;
//	}
//
//	@Bean
//	public EntityManager entityManager(EntityManagerFactory emf) {
//		DBConfiguration.ENTITY_MANAGER = emf.createEntityManager();
//		return emf.createEntityManager();
//	}
//
//	@Bean
//	public String string() {
//		return new String();
//	}
}