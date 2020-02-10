package com.deloitte.ecommerce.dkartproducts.hibernate.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * This class will perform the Datasource configurations for hibernate
 * 
 */

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@Value("${db.driver}")
	private String DB_DRIVER;

	@Value("${db.password}")
	private String DB_PASSWORD;

	@Value("${db.url}")
	private String DB_URL;

	@Value("${db.username}")
	private String DB_USERNAME;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Value("${hikari.connectionpool.size}")
	private int HIKARI_CONNECTIONPOOL_SIZE;
	
	/**
	 * This method will return datasource based on the database connection
	 * configurations provided in the config properties file.
	 * 
	 * @return DataSource
	 */

	@Bean
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(HIKARI_CONNECTIONPOOL_SIZE);
		ds.setDataSourceClassName("org.postgresql.jdbc2.optional.PoolingDataSource");
		ds.addDataSourceProperty("url", DB_URL);
		ds.addDataSourceProperty("user", DB_USERNAME);
		ds.addDataSourceProperty("password", DB_PASSWORD);
		return ds;
	}

	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		jpaVendorAdapter.setGenerateDdl(Boolean.getBoolean(HIBERNATE_HBM2DDL_AUTO));
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(this.dataSource());
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		emf.setPersistenceUnitName("default");
		emf.setJpaProperties(additionalProperties());
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	

	/**
	 * This method will create the Local Session Factory Bean which will then be
	 * used to access session factory objects
	 * 
	 * @return LocalSessionFactoryBean
	 */
	
	@Bean
	public SessionFactory sessionFactory() {
		if (this.entityManagerFactory().unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		return this.entityManagerFactory().unwrap(SessionFactory.class);
	}
	
	
	/**
	 * This method is to set the transaction manager for the hibernate
	 * transaction manager object
	 * 
	 * @return HibernateTransactionManager
	 */
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory());
		return transactionManager;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
		properties.setProperty("hibernate.current_session_context_class", "thread");
		return properties;
	}

}