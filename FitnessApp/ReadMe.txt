Fitness Application:


Tech Stack:
Microservices: Spring Boot applications
UserService, ActivityService, EurekaServer


Databased used: Postgres, MongoDB
Service Discovery : Eureka(spring cloud netflix)
API Gateway: Spring Cloud Routing Reactive gateway
Auth: Keyclock(Open source IAM) (PKCE flow)
Messaging Queue : RabbitMQ (Spring AMQP)
Configuration server:  Spring cloud Config
AI Capability: Gemini


Set UP:
Postgres and Mongo : installed via installers

RabbitMQ:

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
ACCESS : localhost:15672 guest/guest


EurekaServer : Access GUI via: http://localhost:8761/ 

Config Server: Access GUI via: 
 -> http://localhost:8888/activity-service/default
 -> http://localhost:8888/user-service/default
 -> http://localhost:8888/ai-service/default
 
API Gateway: localhost:8080/..

Keycloak:

docker run -p 127.0.0.1:8181:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.7.0 start-dev

ACCESS: http://localhost:8181/ admin/admin : 
->create a Realm and a client id inside realm.
-> create a user in realm (user1/password1)

JWKS : 
1. go to keycloak
2. 	go to your realm
3. from hambuger menu configure -> realm settings -> endpoint -> openid endpoint configuration -> copy the jwks_uri


For AiService configuration, set the environmental variable as :
GEMINI_URL="https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=";GEMINI_KEY=<GEMINI_API_KEY>



---------------------------------------------------------------------------------------------------

UI React app created by vite

react-redux, redux toolkit, react router (allows to add routes in app : url), material ui, react-oauth2 code pkce, axios



----------------------------------------------------------------------------------------------------
Testing:

1. log into keycloak localhost:8181
2. Create a new user
3. log into react app  localhost:5173 using newly created user(the new user is synced automatically in your usersevice app)
4. create a few activities
5. Verify that activities are added in activities list
6. click on any activity and notice that recommendation