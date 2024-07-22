Para iniciar la aplicacion en Maven, ejecute en consola: 

mvn spring-boot:run

Para hacer build en Maven, Docker, ejecute en consola:

./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar

docker build -t springio/gs-spring-boot-docker .


Para iniciar la App en docker:

docker run -p 8080:8080 -t springio/gs-spring-boot-docker

Para iniciar Redis en un contenedor Docker:

docker run -d --name redis -p 6379:6379 redis:5.0.3

Para entrar al cliente de redis, ejecute en consola:
docker exec -it redis sh
# redis-cli
127.0.0.1:6379> keys *
(empty list or set)

Despues de crear una nueva nave:
127.0.0.1:6379> keys *
1) "naves::1"

Se adjunta la collection Postman con todos los metodos de la APIRest 

