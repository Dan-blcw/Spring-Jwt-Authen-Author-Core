## Demo Restful API Security , Role management, token generation and validation

## This is an experiment

- the purpose is to understand what the core of JWT is, how a miniature spring security configuration works,understand how I configure all the HTTP security for our application, if you are still confused about this, watch it to understand how works, I have a pretty solid explanation of the core parts(for Vietnamese people).
- The ones I use in this test are java, postgres DB, Spring boot, Spring Security, Lombox,JSON Web Tokens (JWT),BCrypt, JPA, Hibernate ...
## Prerequisites

- JDK 8
- Maven
- Postgres

## Summary chart
<!--  
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
                                                                          JwtServiceUtil.java
                                                              -SecurityTestingApplication.java
                                                  - resources
                                                              -application.properties
                                             - pom.xml
 -->

![image](https://user-images.githubusercontent.com/127305381/226135049-c4daf190-694b-408e-bef8-b1d76e175449.png)


## End
Okay,that is all and i'm Dan - P-Minh Huong
