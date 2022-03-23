package br.senai.sp.restaguide.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class AppConfig {
	@Bean
public	DataSource dataSource() {
		// configurado a conexão da plicação do banco de dados
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3307/restaurantebd");
	dataSource.setUsername("root");
	dataSource.setPassword("root");
	return dataSource;
}
	// configura o Hibernate (ORM - Mapeamento Objeto Relacional)
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter =  new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		adapter.setShowSql(true);
		adapter.setPrepareConnection(true);
		adapter.setGenerateDdl(true);
		return adapter;
	}

}
