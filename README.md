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
4. resource handler mapping
5. dataSource //org.springframework.jdbc.datasource.DriverManagerDataSource
```

- SampleController
```
해당 컨트롤러에는 기초적인 컨트롤러 생성 실습 코드가 작성되어 있습니다.
@Controller : 클래스
@RequestMapping : 클레스, 메서드
@ResponseBody : 메서드, 리턴타입
RedirectAttributes 를 이용한, 리다이렉트 시점에 원하는 데이터를 임시로 넘기는 작업

JDBC 코드와 JDBC 코드에 문제점이 정리 되어있습니다. exampleJDBC() 참조
JPA 예제가 작성되어 있습니다. (설정은 JavaConfig 방식으로 구현 / JpaEntityManagerFactory Class 참조)
JPA는 EntityManager를 생성해서 사용하며, 취득하는 방법은 EntityManagerFactoryBean을 설정하여 생성합니다. (org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean)

```

- SampleController2
```
해당 컨트롤러에는 spring bean 생명주기 예제 코드가 작성되어 있습니다.
bean 생명주기는 
1. InitializingBean / DisposableBean 인터페이스 구현
2. @PostConstruct / @PreDestroy
3. XML 에서 init-methoc / destroy-method 또는 @Bean(initMethod = "", destroyMethod = "")
언제 사용하냐면, 초기화부분은 주로 Bean이 제대로 생성이 되었는지에 대한 확인(유효성 검사)이며 소멸부분은 자원의 반납을 위해 주로 사용한다.

Bean Life Cycle
1. XML / JavaConfig Class / Annotation Bean 정의를 스캔
2. Bean 인스턴스 생성
3. Bean 프로퍼티 초기화
4. BeanNameAware setBeanName() : Bean 객체 이름을 전달.
5. BeanClassLoaderAware setBeanClassLoader()
6. BeanFactoryAware setBeanFactory() 
7. BeanPostProcessor postProcessorBeforeInitialization()
6. @PostConstruct 적용 메서드 호출. (어노테이션)
7. InitializingBean afterPropertiesSet() (인터페이스 구현)
8. init-method 호출 (Bean 정의 메서드 지정)
9. BeanPostProcessor postProcessorAfterInitialization()

spring container shotdown
1. @PreDestroy (어노테이션)
2. DisposableBean destroy() (인테페이스 구현)
3. destroy-method (Bean 정의 메서드 지정)
```