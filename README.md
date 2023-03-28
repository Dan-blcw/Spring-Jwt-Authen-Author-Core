## Demo Restful API Security , Role management, token generation and validation

## This is an experiment

- the purpose is to understand what the core of JWT is, how a miniature spring security configuration works,understand how I configure all the HTTP security for our application, if you are still confused about this, watch it to understand how works, I have a pretty solid explanation of the core parts(for Vietnamese people).
- The ones I use in this test are java, postgres DB, Spring boot, Spring Security, Lombox,JSON Web Tokens (JWT),BCrypt, JPA, Hibernate ...
## Prerequisites

- JDK 19
- Maven
- Postgres

## Set request body as raw with JSON payload
```
{
    "firstname" : "dan",
    "lastname" : "blcw",
    "email" : "dan_blcw@gmail.com",
    "password" : "475241075"
}
```
## Postman
![image](https://user-images.githubusercontent.com/127305381/228220955-f45d3993-de36-4ab7-9142-dab1d065e8eb.png)
## Summary chart

```
- src
     - main
           -* com.DanCreate.loginregisEmail
                  - config 
                          - ApplicationConfigAuxiliary.java
                          - AuthenticationFilter.java
                          - SecurityConfiguration.java
                  - controller
                          - AuthenticationController.java
                  - model
                      - dtos
                             -- AuthenticateDtos.java
                             -- RegisterDtos.java
                             -- TokenType.java
                             -- User.java
                      - entities
                             -- Role.java
                             -- Token.java
                  - reponsitory
                          - TokenRepository.java
                          - UserRepository.java
                  - response
                          - AuthTokenResponse.java
                  - runDemo
                          - testController.java
                  - service
                          - LogoutService.java
                          - AuthenticationService.java
                  - util
                          - JwtServiceUtil.java
                  - SecurityTestingApplication.java
     - resources
                  -application.properties
- pom.xml
```
## End
Okay,that is all and i'm Dan - P-Minh Huong
