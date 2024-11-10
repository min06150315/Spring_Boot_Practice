# Spring Hello World Project

## 개요
이 프로젝트는 Spring Boot를 사용하여 간단한 Hello World 애플리케이션을 구현한 예제입니다. Spring Boot의 자동 구성과 IoC (Inversion of Control)를 이해하고, RESTful 엔드포인트 설정을 익히는 목적입니다.

## 주요 기능 설명

### 1. @SpringBootApplication 애너테이션
`SpringHelloWorldApplication` 클래스에 사용된 `@SpringBootApplication` 애너테이션은 Spring Boot 애플리케이션의 진입점 역할을 합니다. 이는 세 가지 애너테이션을 결합한 것입니다.

- **@Configuration**: Spring 설정 클래스를 정의하며, 애플리케이션의 설정을 담당합니다.
- **@EnableAutoConfiguration**: Spring Boot가 필요한 설정을 자동으로 적용하도록 하며, 예를 들어, Spring Web 의존성이 포함된 경우 DispatcherServlet을 자동 설정합니다.
- **@ComponentScan**: 애플리케이션의 모든 `@Component` (예: `@Controller`, `@Service`, `@Repository`)를 검색하여 빈(Bean)으로 등록합니다.

따라서 `@SpringBootApplication` 덕분에 Spring은 `HelloWorldController` 클래스를 자동으로 빈으로 등록하고 관리하게 됩니다.

### 2. @RestController와 @GetMapping
`HelloWorldController` 클래스의 `@RestController`와 `@GetMapping` 애너테이션 덕분에 Spring은 해당 클래스를 REST API 엔드포인트로 인식합니다.

- **@RestController**: 이 애너테이션은 `@Controller`와 `@ResponseBody`를 결합한 것으로, 클래스의 모든 메서드가 HTTP 응답 본문에 데이터를 반환하도록 합니다.
- **@GetMapping("/hello")**: HTTP GET 요청을 `/hello` 경로로 처리하라는 의미입니다. 즉, `/hello`로 들어오는 GET 요청이 `hello()` 메서드에 의해 처리되고 `"Hello, World!"`가 반환됩니다.

### 3. 자동 호출 흐름
`@SpringBootApplication`을 사용하면, Spring Boot는 애플리케이션 시작 시 컴포넌트 스캔을 통해 `@RestController`가 붙은 `HelloWorldController`를 자동으로 빈으로 등록합니다. 이후 Spring MVC의 디스패처 서블릿(DispatcherServlet)이 HTTP 요청을 받아 적절한 컨트롤러 메서드로 라우팅합니다.  
따라서 `SpringHelloWorldApplication`에서 `HelloWorldController`를 직접 호출할 필요 없이, Spring의 디스패처 서블릿이 HTTP 요청을 받아서 자동으로 컨트롤러로 위임합니다.

### 4. 애플리케이션 흐름 요약
1. `SpringHelloWorldApplication.main()` 메서드를 통해 애플리케이션이 시작됩니다.
2. Spring Boot가 `@SpringBootApplication`을 통해 애플리케이션 구성을 자동으로 처리하고, `HelloWorldController`를 빈으로 등록합니다.
3. DispatcherServlet이 HTTP 요청을 받고, `/hello` 경로로 들어오는 GET 요청을 `hello()` 메서드로 라우팅합니다.
4. `hello()` 메서드가 `"Hello, World!"` 문자열을 반환하여 클라이언트에 응답합니다.

Spring Boot의 자동 구성과 Spring MVC의 디스패처 서블릿 덕분에 `SpringHelloWorldApplication`에서 `HelloWorldController`를 명시적으로 호출하지 않아도 애플리케이션이 정상적으로 동작하게 됩니다.

## 서버 실행 및 접속 방법

1. **서버 실행**  
    터미널에서 다음 명령어를 사용하여 Spring Boot 애플리케이션을 실행합니다.
    ```bash
    ./mvnw spring-boot:run
    ```
    또는, IDE에서 `SpringHelloWorldApplication` 클래스의 `main()` 메서드를 직접 실행할 수 있습니다.


2. **포트 설정**

    기본적으로 Spring Boot 애플리케이션은 포트 `8080`에서 실행됩니다. 그러나 이 프로젝트에서는 `application.properties` 파일에서 포트를 `8081`로 변경하였습니다.
    `src/main/resources/application.properties` 파일에 다음 설정이 포함되어 있습니다.
    ```bash
    server.port=8081
    ```


3. **접속 방법**

    서버가 실행되면, 웹 브라우저에서 다음 주소로 접속하여 결과를 확인할 수 있습니다:
    ```bash
    http://localhost:8081/hello
    ``` 
   접속 시 "Hello, World!" 메시지가 화면에 출력됩니다.

    ```bash
   Hello, World!
   ```