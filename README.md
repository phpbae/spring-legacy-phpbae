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
- applicationContext.xml 및 dispatcher-servlet.xml 환경설정
```
1. component-scan
2. annotation-driven
3. InternalResourceViewResolver  //Spring MVC에서 View는 사용자에게 결과를 랜더링하여 보여주는 Bean
4. resource handler mapping
5. dataSource //org.springframework.jdbc.datasource.DriverManagerDataSource
```

```
dispatcher-servlet.xml / applicationContext.xml 관계에 대한 내용
- 스프링 MVC는 1개 이상의 DispatcherServlet 설정이 가능.
- DispatcherServlet 설정은 web.xml 파일에 설정 내용을 작성한다.
<servlet>
<servlet-name>dispatcer</servlet-name>
<servlet-class>
org.springframework.web.servlet.DispatcerServlet
</servlet-class>
</servlet>
- 위와 같이 설정을 하였다면,  <servlet-name>-servlet.xml 파일을 로드하게 된다.

- 다른 설정파일을 사용하고 싶은경우에는, contextConfigLocation 초기화 파라미터에 설정 파일 목록을 지정 
<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
</context-param>

- 만약, 2개 이상의 dispatcher servlet을 설정하면, 각각 별도의 WebApplicationContext를 생성하는데 서로 설정된 Bean을 참조하고 싶다면 ContextLoaderListener를 사용하여 공통으로 사용될 빈을 설정할 수 있게 된다. 다음과 같이 ContextLoaderListener를 ServletListener로 등록.
- 웹 어플리케이션이 시작되는 시점에 ApplicationContext을 로딩하며, 로딩된 빈정보는 모든 WebApplicationContext들이 참조할 수 있다.
<listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

//------------- 예제
<!-- ApplicationContext 빈 설정 파일-->
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>/WEB-INF/config/easycompany-service.xml,/WEB-INF/config/easycompany-dao.xml</param-value>
</context-param>
	
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<servlet>
	<servlet-name>employee</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/config/easycompany-service.xml</param-value>
	</init-param>
</servlet>

<servlet>
	<servlet-name>webservice</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/config/easycompany-webservice.xml</param-value>
	</init-param>
</servlet>

- employee-servlet 과 webservice-servlet.xml 에 정의된 Bean들은 서로 참조할 수 없지만, easycompany-service.xml / easycompany-dao.xml 에 정의된 Bean들은 참조가 가능하다. 

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

Bean scope(총 5가지)
- singleton : default / 싱글턴으로 인스턴스를 생성
- prototype : 이용할 때마다 인스턴스 생성 getBean()
- request : request 스코프인 동안만 인스턴스 생존 (각각의 HTTP request 는 자신만의 인스턴스를 가짐)
- session : session 스코프인 동안만 인스턴스 생존
- application : application 스코프인 동안만 인스턴스 생존

@Scope(value = "")
<bean id="beanLifeCycleTest" class="com.phpbae.web.business.service.BeanLifeCycleTest" scope="singleton"></bean>


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