package com.empmgt.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.empmgt.dao.Employee;

 
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer {
 
    @Value("${spring.datasource.url}")
	private String mysqldbUrl;
    
    @Value("${spring.datasource.username}")
    private String userName;
    
    @Value("${spring.datasource.password}")
    private String passWord;
    
    AppConfig(){
    	
    }
    
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
    
    @Bean
    public Employee employee(){
    	return new Employee();
    }
      


    @Bean(name = "dataSource")
	public BasicDataSource dataSource() {

		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl(mysqldbUrl);
		datasource.setUsername(userName);
		datasource.setPassword(passWord);
		return datasource;
	}
    
    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect",
            "org.hibernate.dialect.MySQL5Dialect");
        return prop;
}
    
    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
    	LocalSessionFactoryBuilder builder =
    			new LocalSessionFactoryBuilder(dataSource());
    	builder.addProperties(getHibernateProperties());
    	builder.addAnnotatedClass(Employee.class);
    	    	   	
    	return builder.buildSessionFactory();
    }
    
   
}
