package com.shajdin.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shajdin.service.SequenceService;
import com.shajdin.service.SequenceServiceImpl;
import com.shajdin.service.UserService;
import com.shajdin.service.UserServiceImpl;

@Configuration
@EnableJpaRepositories(basePackages={"com.shajdin.repository"})
@EnableTransactionManagement
@ComponentScan(basePackages={"com.shajdin.conntroller"})
public class SystemConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}
	
	@Bean
	public SequenceService sequenceService(){
		return new SequenceServiceImpl();
	}
	
	@Bean
	public JdbcOperations jdbcOperations(){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	@Profile("!heroku")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabase(Database.H2);

		Properties props = new Properties();
		props.setProperty("hibernate.format_sql", "true");
		
		LocalContainerEntityManagerFactoryBean emfb = 
			new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setPackagesToScan("com.shajdin.model");
		emfb.setJpaProperties(props);
		emfb.setJpaVendorAdapter(adapter);
		
		return emfb;
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
