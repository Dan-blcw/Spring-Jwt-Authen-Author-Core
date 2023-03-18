## Demo Restful API Security , Role management, token generation and validation

## This is an experiment

- the purpose is to understand what the core of JWT is, how a miniature spring security configuration works,understand how I configure all the HTTP security for our application, if you are still confused about this, watch it to understand how works, I have a pretty solid explanation of the core parts(for Vietnamese people).
- The ones I use in this test are java, postgres DB, Spring boot, Spring Security, Lombox,JSON Web Tokens (JWT),BCrypt, JPA, Hibernate ...
## Prerequisites

- JDK 8
- Maven
- Postgres

## Summary chart
 
- src
   - main
      -- com.DanCreate.loginregisEmail
          - config 
                    - ApplicationConfigAuxiliary
                    - AuthenticationFilter
                    - SecurityConfiguration
          - controller
                    - AuthenticationController
          - model
               - dtos
                    -- AuthenticateDtos
                    -- RegisterDtos
                    -- TokenType
                    -- User
               - entities
                    -- Role
                    -- Token
          - reponsitory
                    - TokenRepository
                    - UserRepository
          - response
                    - AuthTokenResponse
          - runDemo
                    - testController
          - service
                    - LogoutService
                    - AuthenticationService
          - util
                JwtServiceUtil
## End
Okay,that is all and i'm Dan - P-Minh Huong
