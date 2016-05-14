package com.shajdin.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
public class DataSourceConfig {
	
	@Bean
	@Profile("!heroku")
	public DataSource devDataSource(){
		return new EmbeddedDatabaseBuilder().build();
	}
	
//	@Bean
//	@Profile("heroku")
//	public DataSource dataSource(){
//		//TODO
//		return new EmbeddedDatabaseBuilder().build();
//	}

}
