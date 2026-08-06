Access GUI via: http://localhost:8761/

InterServiceCommunication


We can create activity for a user in activity service , but only for user which are present in
userservice. Therefor the 2 microservice must communicate with each other.
(ie) activity service will validate the presence of user in userservice by making a call to it
(using REST Client)

(Rest client, webclient, rest template, http interface)
