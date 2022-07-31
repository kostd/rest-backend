package org.kostd.restaurant.config

import org.hibernate.jpa.HibernatePersistenceProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.TransactionManagementConfigurer
import java.util.*
import javax.sql.DataSource


@EnableTransactionManagement
@Configuration
class ApplicationConfig : TransactionManagementConfigurer {

    @Bean
    fun h2DataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build()
    }

    /**
     * Настраиваемся на испоьзование JPA для доступа к данным, с реализацией в виде привычного
     * hibernate (но hibernate-coupling не допускаем, юзаем только аннотации JPA)
     */
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        // #TODO: в release не надо H2, а надо PG
        factory.dataSource = h2DataSource()
        factory.setPersistenceProviderClass(HibernatePersistenceProvider::class.java)
        factory.setPersistenceXmlLocation("jpa/persistence.xml")
        val properties = Properties()
        // #TODO: в release не надо генерить ddl
        properties.setProperty("javax.persistence.schema-generation.database.action", "create")
        factory.setJpaProperties(properties)
        return factory
    }

    /**
     * локальные JPA-driven транзакции, как рекомендует Спринг в 5.4.3. Spring-driven JPA transactions
     */
    @Bean
    fun transactionManager(): PlatformTransactionManager {
        val transactionManager: JpaTransactionManager = JpaTransactionManager();
        transactionManager.entityManagerFactory = entityManagerFactory().getObject();
        return transactionManager;
    }

    /**
     * теперь можно пользоваться аннотацией @Transactional
     */

    override fun annotationDrivenTransactionManager(): TransactionManager {
        return transactionManager()
    }
}