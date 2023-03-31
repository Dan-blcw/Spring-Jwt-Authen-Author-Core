## Demo Restful API Security , Role management, token generation and validation

## This is an experiment

- the purpose is to understand what the core of JWT is, how a miniature spring security configuration works,understand how I configure all the HTTP security for our application, if you are still confused about this, watch it to understand how works, I have a pretty solid explanation of the core parts(for Vietnamese people).
- The ones I use in this test are java, postgres DB, Spring boot, Spring Security, Lombox,JSON Web Tokens (JWT),BCrypt, JPA, Hibernate ...
## Prerequisites

- JDK 19
- Maven
- Postgres

## Set request body as raw with JSON payload
#### PostRequest Regis
```
{
    "firstname" : "dan",
    "lastname" : "blcw",
    "email" : "dan_blcw@gmail.com",
    "password" : "475241075"
}
```
#### PostRequest Auth-logout
```
{
    "email" : "dan_blcw@gmail.com",
    "password" : "475241075"
}
```
## Postman
#### Get information from demo-controller 
![image](https://user-images.githubusercontent.com/127305381/229187880-f4c9cada-313f-47e6-a4b4-c98dc0eaec25.png)
#### NOTE 
- that in this test i am not using validation sequence so getting information we have to get token then put in and get information from auth
## Explain
### SecurityFilterChain
![image](https://user-images.githubusercontent.com/127305381/229189609-6e940893-99cd-4237-960b-761393a61d9b.png)
### AuthenticationProvider
![image](https://user-images.githubusercontent.com/127305381/229189811-d033a812-2ca3-4f3a-9706-8a4d6f76dfa7.png)

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
