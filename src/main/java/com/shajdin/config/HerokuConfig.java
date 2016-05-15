package com.shajdin.config;

import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("heroku")
public class HerokuConfig {
	
	@Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException{
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(false);
		adapter.setGenerateDdl(true);
		adapter.setDatabase(Database.POSTGRESQL);

//		Properties props = new Properties();
//		props.setProperty("hibernate.format_sql", "true");
		
		LocalContainerEntityManagerFactoryBean emfb = 
			new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource());
		emfb.setPackagesToScan("com.shajdin.model");
//		emfb.setJpaProperties(props);
		emfb.setJpaVendorAdapter(adapter);
		
		return emfb;
	}

}
