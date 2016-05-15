package com.shajdin.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("heroku")
public class DataSourceHerokuConfig {
	
	

//	@Bean
//	public URI dbUrl(@Value("${DATABASE_URL}") String dbUrl) throws URISyntaxException {
//		return new URI(dbUrl);
//	}

//	@Bean
//	public DataSource herokuDataSource() {
//		String databaseUrl = System.getenv("DATABASE_URL");
//
//		URI dbUri;
//		try {
//			dbUri = new URI(databaseUrl);
//		} catch (URISyntaxException e) {
//			return null;
//		}
//
//		String username = dbUri.getUserInfo().split(":")[0];
//		String password = dbUri.getUserInfo().split(":")[1];
//		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//
//		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setUrl(dbUrl);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setTestOnBorrow(true);
//		dataSource.setTestWhileIdle(true);
//		dataSource.setTestOnReturn(true);
//		dataSource.setMaxActive(10);
//		dataSource.setMaxIdle(5);
//		dataSource.setMinIdle(2);
//		dataSource.setInitialSize(5);
//		dataSource.setRemoveAbandoned(true);
//		dataSource.setValidationQuery("SELECT 1");
//		return dataSource;
//	}

}
