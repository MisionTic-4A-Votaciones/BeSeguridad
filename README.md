# BeSeguridad
Backend for the security and access module of MisionTic web application project cicle IVa. To make this part of the project work properly you should add the file application properties as follows


spring.jpa.hibernate.naming.physical_strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

spring.jpa.generate-ddl=True

spring.jpa.hibernate.ddl-auto=create

server.port= the port you are using

spring.datasource.driver-class=com.mysql.cj.jdbc.Driver

spring.datasource.url= your database url

spring.datasource.username= your database user

spring.datasource.password= your database password
