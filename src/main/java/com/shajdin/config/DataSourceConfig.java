package com.shajdin.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
public class DataSourceConfig {
	
	@Bean
	public DataSource devDataSource(){
		return new EmbeddedDatabaseBuilder().build();
	}
	
//	@Bean
//	@Profile("")
//	public DataSource dataSource(){
//		//TODO
//		return new EmbeddedDatabaseBuilder().build();
//	}

}
