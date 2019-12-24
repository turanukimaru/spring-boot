package com.github.turanukimaru.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class PersistenceJPAConfig {
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val bean = LocalContainerEntityManagerFactoryBean()
        bean.dataSource = dataSource()
        bean.setPackagesToScan("com.github.turanukimaru.demo.entity")
        bean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        bean.setJpaProperties(addtionalProperties())

        return bean
    }

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        //InvalidConnectionAttributeException: The server time zone value 'unknown' is...
        dataSource.url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC"
        dataSource.username = "scott"
        dataSource.password = "tiger"
        return dataSource
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        val manager = JpaTransactionManager()
        manager.entityManagerFactory = emf
        return manager
    }

    fun addtionalProperties(): Properties {
        val p = Properties()
        p.setProperty("hibernate.hbm2ddl.auto", "create-drop")
        p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
        return p
    }
}