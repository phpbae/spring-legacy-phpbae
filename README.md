# spring-legacy-phpbae


## API version & spec in project
- Java 1.8
- spring 4.3.18.RELEASE
- spring-data-jpa 1.11.13.RELEASE
- hibernate 5.3.1.Final
- lombok 1.18.0
- mysql-connector-java 8.0.11
- servlet-api 4.0.0
- junit 4.12


## spring library & API List

```
spring-context
spring-core
spring-aop
spring-beans
spring-web
spring-webmvc
spring-tx
spring-orm
spring-jdbc
spring-test
spring-data-jpa
```

## 구동환경
- Tomcat 9.0.8
- DB : MySQL 5.7

## Note

- spring legacy 환경으로 프로젝트 구성. (maven 기반)
- applicationContext.xml 에 환경설정
```
1. component-scan
2. annotation-driven
3. InternalResourceViewResolver  //Spring MVC에서 View는 사용자에게 결과를 랜더링하여 보여주는 Bean
4. resource
5. dataSource //org.springframework.jdbc.datasource.DriverManagerDataSource
```