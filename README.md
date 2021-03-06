# spring-legacy-phpbae


### API version & spec in project

> `- Java 1.8`  
`- spring 4.3.18.RELEASE`  
`- spring-data-jpa 1.11.13.RELEASE`  
`- spring-security-xxx 4.2.7.RELEASE`  
`- hibernate 5.3.1.Final`  
`- lombok 1.18.0`  
`- mysql-connector-java 8.0.11`  
`- servlet-api 4.0.0`  
`- junit 4.12`  
`- slf4j-api / slf4j-simple 1.7.25`   
`- aspectjrt / aspectjweaver 1.8.13`


### spring library & API List

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
spring-aspects
spring-data-jpa
spring-security-config
spring-security-core
spring-security-web
```

### 구동환경

> `Tomcat 9.0.8`  
`DB : MySQL 5.7`    
 ```
 // TEST LOCAL DB INFO
 db name : test
 id : root
 pw : 1234
 
 JPA set(JpaEntityManagerFactory.java 참조)
 ```
 
 ---

## Note

- spring legacy 환경으로 프로젝트 구성. (maven 기반)
- applicationContext.xml 및 dispatcher-servlet.xml 환경설정
```
1. component-scan
2. annotation-driven
3. InternalResourceViewResolver // Spring MVC에서 View는 사용자에게 결과를 랜더링하여 보여주는 Bean
4. resource handler mapping
5. dataSource // org.springframework.jdbc.datasource.DriverManagerDataSource
```


/src/main/webapp/WEB-INF/web.xml
/src/main/webapp/WEB-INF/dispatcher-servlet.xml
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

- 위와 같이 설정을 하였다면,  <servlet-name>-servlet.xml 파일을 로드하게 된다. (dispatcher-servlet.xml)
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

- employee-servlet 과 webservice-servlet.xml 에 정의된 Bean들은 서로 참조할 수 없지만, 
easycompany-service.xml / easycompany-dao.xml 에 정의된 Bean들은 참조가 가능하다. 

```

---

- SampleController.java
```
해당 컨트롤러에는 기초적인 컨트롤러 생성 실습 코드가 작성되어 있습니다.

@Controller : 클래스
@RequestMapping : 클레스, 메서드
@ResponseBody : 메서드, 리턴타입

RedirectAttributes 를 이용한, 리다이렉트 시점에 원하는 데이터를 임시로 넘기는 작업
redirectAttributes.addFlashAttribute("key", value); 
addFlash 는 URI 뒤에 쿼리스트링형태로 붙지는 않는다. 세션에 저장 후, 리다이렉트 후 소멸한다.

JDBC 코드와 JDBC 코드에 문제점이 정리 되어있습니다. exampleJDBC() 참조
JPA 예제가 작성되어 있습니다. (설정은 JavaConfig 방식으로 구현 / JpaEntityManagerFactory Class 참조)
JPA는 EntityManager(엔티티매니저)를 생성해서 사용하며, 취득하는 방법은 EntityManagerFactoryBean(엔티티매니저팩토리)을 설정하여 생성합니다. (org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean)

```

---

- SampleController2.java
```
해당 컨트롤러에는 spring bean 생명주기 예제 코드가 작성되어 있습니다.

Bean scope(총 5가지)
- singleton : default / 싱글턴으로 인스턴스를 생성 / single beans cache 에 저장
- prototype : 이용할 때마다 인스턴스 생성 getBean()
- request : request 스코프인 동안만 인스턴스 생존 (각각의 HTTP request 는 자신만의 인스턴스를 가짐)
- session : session 스코프인 동안만 인스턴스 생존
- application : application 스코프인 동안만 인스턴스 생존

* annotation 설정
@Scope(value = "")

* xml 설정
<bean id="beanLifeCycleTest" class="com.phpbae.web.business.service.BeanLifeCycleTest" scope="singleton"></bean>


bean 생명주기는 
1. InitializingBean / DisposableBean 인터페이스 구현
2. @PostConstruct / @PreDestroy
3. XML 에서 init-method / destroy-method 또는 
@Bean(initMethod = "", destroyMethod = "") -> annotaion 방식
초기화부분은 주로 Bean이 제대로 생성이 되었는지에 대한 확인(유효성 검사)
소멸부분은 자원의 반납을 위해 주로 사용

* 호출순서 : 어노테이션 -> 인터페이스 -> initMethod 순으로 호출이 된다.

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

- sampleController3
```
sampleController3 에는 AOP와 로깅처리 예제가 작성되어있습니다.

AOP용어(Aspect Orient Programming(관점 지향 프로그래밍)의 약자)
1. Aspect
 관심사 입니다. 하나 또는 그 이상의 어드바이스와 포인트컷을 조합한 것.
2. Join Point
 어드바이스가 실행하는 동작을 끼워 넣을 수 있는 때를 의미.
3. Advice
 조인 포인트에서 실행되는 코드를 의미(로그, 트랜잭션 관리 등)
4. Pointcut
 조인 포인트를 조건으로 거르는 필터(조인 포인트와 어드바이스 중간에 있으면서, 어떤 어드바이스를 호출할지 선별)
 
Spring 에서 제공하는 어드바이스
Before
After
AfterRunning
Around
AfterThrowing

@Aspect 오토프록시를 사용할 때 CGLIB 프록시를 강제하려면 <aop:aspectj-autoproxy> 요소의 'proxy-target-class' 속성을 true로 설정

- AOP 관련해서 JoinPoint를 파라미터로 전달받을 경우 반드시 첫번째 파라미터로 지정해야 함(그 외는 예외 발생)
- JoinPoint 인터페이스는 호출되는 대상 객체, 메서드 그리고 전달되는 파라미터 목록에 접근할 수 있는 메서드를 제공
  - Signature getSignature( ) - 호출되는 메서드에 대한 정보를 구함
  - Object getTarget( ) - 대상 객체를 구함
  - Object[ ] getArgs( ) - 파라미터 목록을 구함

- org.aspectj.lang.Signature 인터페이스는 호출되는 메서드와 관련된 정보를 제공하기 위해 다음과 같은 메서드를 정의
  - String getName( ) - 메서드의 이름을 구함
  - String toLongName( ) - 메서드를 완전하게 표현한 문장을 구함(메서드의 리턴 타입, 파라미터 타입 모두 표시)
  - String toShortName( ) - 메서드를 축약해서 표현한 문장을 구함(메서드의 이름만 구함)

- Around Advice의 경우 org.aspectj.lang.ProceedingJoinPoint를 첫 번째 파라미터로 전달받는데 해당 인터페이스는 프록시 대상 객체를 호출할 수있는 proceed() 메서드를 제공
- ProceedingJoinPoint는 JoinPoint 인터페이스를 상속받았기 때문에 Signature를 이용하여 대상 객체, 메서드 및 전달되는 파라미터에 대한 정보를 구할 수 있음

```


- sampleController4
```
sampleController4 에는 트랜잭션에 대한 예제를 맛보실수 있습니다.
트랜잭션 설정은 명시적 / 선언적 트랜잭션을 이용할 수 있습니다.
여기서는 명시적 트랜잭션으로 설정했습니다.(aop를 이용하는 방식)
```


- sampleController5
```
security 설정하고 밑에와 같은 에러가 발생.
org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesJdbc The web application [ROOT] registered the JDBC driver [com.mysql.cj.jdbc.Driver] but failed to unregister it when the web application was stopped. To prevent a memory leak, the JDBC Driver has been forcibly unregistered.
check whether you have multiple ContextLoader* definitions in your web.xml
밑에 링크 참조 : 
https://javadeveloperzone.com/common-error/spring-security-check-whether-multiple-contextloader-definitions-web-xml/

- AbstractSecurityWebApplicationInitializer class 상속 
- XML 설정 (web.xml)
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

```
