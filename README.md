# bc2504p-sb

## Part 1: RESTful API
- @RestController (@Controller + @ ResponseBody)
- @GetMapping, @PostMapping, @DeleteMapping, @PutMapping, @PatchMapping
- @RequestParam, @PathVariable, @RequestBody(Put, Post)
- Class & Interface (xxxxController.java, xxxxOperation.java)
- API endpoint URI: Noun Phrase
- Spring Web (Maven Dependency)

## Part 2: Spring Context
- Create Bean in Spring Context: IoC (@Controller, @Service, @Respository, @Configuration+@Bean, @Component, @ControllerAdvice)
  - @Configuration+@Bean -> i.e. RestTemplate
  - @Component -> i.e. Mapper (Custom class)
  - @ControllerAdvice -> i.e. Global Exception Handling
- Inject Bean: DI
  - @AutoWired (Search the Bean in Spring Context)
  - @Value (Search configuration params from yml)
- Inversion of Control (IoC) + Dependency Injection (DI) -> Server Starts (Bean Cycle)
  - if IoC and DI fails, Server start will fails.
  - Spring automate the dependency check in sequence (observe @autowired)

## Part 3: Database
- JPA + Database Drive (Maven Dependency)
- application.yml -> DB connection properties
- Entity class -> Table (JPA Hibernate -> create table SQL)
  - @Entity, @Table, @Getter, @NoArgsContructor, @AllArgsContructor, @Builder, 
  - @Column, @Id (PK), @GeneratedValue, @ManyToOne, @JoinColumn (FK)
- Repository (CRUD) -> DML Operation (insert, select, update, delete)
  - JPA Methods (Java Method Pattern. i.e. orderByxxxx, findByxxxx, And, Or)
    - Optional<XXXX>
    - List<XXXX>
    - findAll, findById, SaveAll, save, deleteAll, deleteById, 
    - find by FK, i.e. PostRepository.java -> findByUserEntity
  - JPQL (Select entity by custom qery)
  - Native SQL Query (DB specific)

## Part 4: Data Transfer Object + Serialization
- @JsonProperty (Isolate the field name between JSON and Class attribute)
- Serialization: Java Object ->
- Deserialization: JSON -> Java Object
- /dto folder: For Controller use (request / response)
- /model/dto folder: For Service use (call third party)
- Mapper (i.e. Builder Logic)
  - API consumer -> controller -> select Entity -> DTO -> return API consumer
  - API consumer -> controller -> DTO -> Entity -> Database
  - Combine two entities into one DTO
  - Combine two DTO into one DTO

## Part 5: AOP (@ControllerAdvice)
- Able to return a separate program flow
- Return another response type
- For method caller, no longer use try-catch
- throw exception object as usual

## Part 6:
- CommandLineRunner (Bean for AOP)
- Scheduling 
- ObjectMapper
- Redis (RedisTemplate/ RedisManager)

## Spring Boot Project
- Update Code -> Save -> harddrive
- mvn clean install -> generate jar file (target folder)

## Docker Development (demo-helloworld-app call demo-calculator)
- Prepare application.yml (setup the ports)
- Prepare Dockerfile (for building docker image)
- mvn clean install for both projects
- docker build -t demo-helloworld:0.0.1 -f Dockerfile .
- docker build -t demo-calculator:0.0.1 -f Dockerfile .
- docker network create bootcamp-network
- docker run -d --name demo-helloworld-app --network bootcamp-network -p 8180:8080 demo-helloworld:0.0.1
- docker run -d --name demo-calculator-app --network bootcamp-network -p 8181:8081 demo-calculator:0.0.1
- docker run -d --name demo-api-app --network bootcamp-network -p 8182:8082 demo-api:0.0.1

## Docker Development (demo-api connect Database and Redis)

- docker pull postgres:latest
- docker run -d --network bootcamp-network --name postgres-container
  -e POSTGRES_USER=postgres
  -e POSTGRES_PASSWORD=admin1234
  -e POSTGRES_DB=bootcamp_2504p
  -p 5532:5432
  postgres:latest
- docker pull redis:latest
- docker run -d --network bootcamp-network --name redis-container
  -p 6479:6379
  redis:latest
- Dockerfile for demo-api
- application.yml for demo-api (postgres-container and redis-container)
- docker build & docker run