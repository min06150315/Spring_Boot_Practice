# Java Object-Oriented Concepts for Spring Boot

---

## 1. 객체 지향 프로그래밍의 4대 특징

### 1.1 캡슐화 (Encapsulation)

- **정의**: 객체의 데이터와 메서드를 하나로 묶어 외부에서 직접 접근하지 못하도록 제한하는 기법.
- **예시 코드**:

  ```java
  public class User {
      private String name; // 외부에서 직접 접근 불가
      private int age;

      // Getter와 Setter로 간접 접근
      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public int getAge() {
          return age;
      }

      public void setAge(int age) {
          if (age >= 0) this.age = age; // 유효성 검사를 통한 보호
      }
  }
  ```

### 1.2 상속 (Inheritance)

- **정의**: 부모 클래스의 속성과 메서드를 자식 클래스에서 물려받아 재사용하거나 확장하는 기법.
- **예시 코드**:

  ```java
  public class Animal {
      public void makeSound() {
          System.out.println("Some sound...");
      }
  }

  public class Dog extends Animal {
      @Override
      public void makeSound() {
          System.out.println("Bark!");
      }
  }

  // 사용 예시
  Animal animal = new Dog();
  animal.makeSound(); // 출력: Bark!
  ```

### 1.3 다형성 (Polymorphism)

- **정의**: 같은 메서드를 호출해도 객체 타입에 따라 다른 동작을 수행하도록 하는 기법.
- **예시 코드**:

  ```java
  public interface Shape {
      void draw();
  }

  public class Circle implements Shape {
      @Override
      public void draw() {
          System.out.println("Drawing a circle");
      }
  }

  public class Square implements Shape {
      @Override
      public void draw() {
          System.out.println("Drawing a square");
      }
  }

  // 사용 예시
  Shape shape = new Circle();
  shape.draw(); // 출력: Drawing a circle
  shape = new Square();
  shape.draw(); // 출력: Drawing a square
  ```

### 1.4 추상화 (Abstraction)

- **정의**: 객체의 복잡한 내부 구현은 숨기고 필요한 기능만 외부에 노출하는 기법.
- **예시 코드**:

  ```java
  public abstract class Payment {
      public abstract void processPayment(double amount);
  }

  public class CreditCardPayment extends Payment {
      @Override
      public void processPayment(double amount) {
          System.out.println("Processing credit card payment: $" + amount);
      }
  }

  // 사용 예시
  Payment payment = new CreditCardPayment();
  payment.processPayment(100.0); // 출력: Processing credit card payment: $100.0
  ```

---

## 2. SOLID 원칙

### 2.1 단일 책임 원칙 (SRP, Single Responsibility Principle)

- **정의**: 클래스는 하나의 책임만 가지며, 변경 사유도 하나여야 한다.
- **예시 코드**:

  ```java
  public class User {
      private String name;
      private String email;
      // getter/setter 메서드
  }

  public class UserRepository {
      public void saveUser(User user) {
          // 사용자 데이터를 저장하는 책임
      }
  }

  public class UserService {
      public void registerUser(User user) {
          // 사용자 등록 로직
      }
  }
  ```

### 2.2 개방-폐쇄 원칙 (OCP, Open-Closed Principle)

- **정의**: 소프트웨어 요소는 확장에는 열려 있어야 하지만, 수정에는 닫혀 있어야 한다.
- **예시 코드**:

  ```java
  public interface NotificationService {
      void sendNotification(String message);
  }

  public class EmailNotificationService implements NotificationService {
      @Override
      public void sendNotification(String message) {
          System.out.println("Sending email: " + message);
      }
  }

  public class SmsNotificationService implements NotificationService {
      @Override
      public void sendNotification(String message) {
          System.out.println("Sending SMS: " + message);
      }
  }

  // 새로운 서비스 추가 시 기존 코드를 수정할 필요 없이 구현체를 추가할 수 있음
  ```

### 2.3 리스코프 치환 원칙 (LSP, Liskov Substitution Principle)

- **정의**: 자식 클래스는 언제나 부모 클래스를 대체할 수 있어야 한다.
- **예시 코드**:

  ```java
  public class Rectangle {
      protected int width, height;

      public void setWidth(int width) {
          this.width = width;
      }

      public void setHeight(int height) {
          this.height = height;
      }

      public int getArea() {
          return width * height;
      }
  }

  public class Square extends Rectangle {
      @Override
      public void setWidth(int width) {
          this.width = this.height = width;
      }

      @Override
      public void setHeight(int height) {
          this.width = this.height = height;
      }
  }
  ```

### 2.4 인터페이스 분리 원칙 (ISP, Interface Segregation Principle)

- **정의**: 클라이언트는 자신이 사용하지 않는 메서드에 의존하지 않도록 인터페이스를 분리해야 한다.
- **예시 코드**:

  ```java
  public interface Workable {
      void work();
  }

  public interface Eatable {
      void eat();
  }

  public class Worker implements Workable, Eatable {
      @Override
      public void work() {
          System.out.println("Working...");
      }

      @Override
      public void eat() {
          System.out.println("Eating...");
      }
  }
  ```

### 2.5 의존 역전 원칙 (DIP, Dependency Inversion Principle)

- **정의**: 고수준 모듈은 저수준 모듈에 의존해서는 안 되며, 둘 다 추상화에 의존해야 한다.
- **예시 코드**:

  ```java
  public interface Keyboard {
      void type();
  }

  public class MechanicalKeyboard implements Keyboard {
      @Override
      public void type() {
          System.out.println("Typing with mechanical keyboard");
      }
  }

  public class Computer {
      private Keyboard keyboard;

      public Computer(Keyboard keyboard) {
          this.keyboard = keyboard;
      }

      public void type() {
          keyboard.type();
      }
  }

  // 사용 예시
  Keyboard keyboard = new MechanicalKeyboard();
  Computer computer = new Computer(keyboard);
  computer.type(); // 출력: Typing with mechanical keyboard
  ```

---

## 3. 디자인 패턴 (Design Patterns)

### 3.1 싱글톤 패턴 (Singleton Pattern)

- **정의**: 애플리케이션에서 하나의 인스턴스만 생성하도록 제한하는 패턴.
- **예시 코드**:

  ```java
  public class Singleton {
      private static Singleton instance;

      private Singleton() {} // 생성자를 private으로 제한

      public static Singleton getInstance() {
          if (instance == null) {
              instance = new Singleton();
          }
          return instance;
      }
  }
  ```

### 3.2 팩토리 패턴 (Factory Pattern)

- **정의**: 객체 생성 로직을 별도의 클래스로 분리하여 객체 생성 방법을 캡슐화.
- **예시 코드**:

  ```java
  public interface Product {
      void create();
  }

  public class ConcreteProductA implements Product {
      @Override
      public void create() {
          System.out.println("Creating Product A");
      }
  }

  public class ProductFactory {
      public static Product createProduct(String type) {
          if (type.equals("A")) {
              return new ConcreteProductA();
          }
          return null;
      }
  }

  // 사용 예시
  Product product = ProductFactory.createProduct("A");
  product.create(); // 출력: Creating Product A
  ```

### 3.3 프록시 패턴 (Proxy Pattern)

- **정의**: 특정 객체에 접근하는 방법을 제어하기 위해 대리 객체(프록시)를 사용하는 패턴.
- **예시 코드**:

  ```java
  public interface Service {
      void request();
  }

  public class RealService implements Service {
      @Override
      public void request() {
          System.out.println("Executing request in RealService");
      }
  }

  public class ProxyService implements Service {
      private RealService realService;

      @Override
      public void request() {
          if (realService == null) {
              realService = new RealService();
          }
          System.out.println("Executing proxy logic before calling RealService");
          realService.request();
      }
  }

  // 사용 예시
  Service service = new ProxyService();
  service.request();
  ```

이 예제들을 통해 각 개념과 원칙을 더 잘 이해할 수 있을 것입니다.
