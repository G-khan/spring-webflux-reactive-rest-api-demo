# spring-webflux-reactive-rest-api-demo
 
Building Reactive app with Spring Webflux

[![Project Status: Active – The project has reached a stable, usable state and is being actively developed.](https://www.repostatus.org/badges/latest/active.svg)]


Examples

* Reactive Endpoints
* Mono, Flux structures
* Functional Reactive Endpoints
* WebClient & WebTestClient
* R2DBC with PostgreSQL

    ## Requests

<code>
 
    ###  
    GET http://localhost:8099/api/v1/users  
      
    ###  
    POST http://localhost:8099/api/v1/users  
    Content-Type: application/json  
      
    {  
      "name": "Betül",  
      "score": 52  
    }  
      
    ###  
    PUT http://localhost:8099/api/v1/users/24  
    Content-Type: application/json  
      
    {  
      "name": "Gokhanadev",  
      "score": 52  
    }  
    
    ###  
    DELETE http://localhost:8099/api/v1/users/24  

    ###  
    GET http://localhost:8099/api/v1/users/1  
      
    ###  
    GET http://localhost:8099/api/v1/users/flux  
      
    ###  
    GET http://localhost:8099/api/v1/users/guests/1
</code>
