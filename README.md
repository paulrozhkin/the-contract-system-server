# GoblinSlayerSystemServer

## Documentation
- [dbDiagram](https://dbdiagram.io/d)
- [Server API](https://app.swaggerhub.com/apis/ForsaiR/GoblinSlayerSystem/)

## Linked Repositories
- [Client](https://github.com/paulrozhkin/the-contract-system-web-client)
- [Modelio](https://github.com/paulrozhkin/the-contract-system-modelio)

## Description

Course project on discipline "Методология программной инженерии".
The server application for the contract system in the world "Goblin Slayer".

## Deployment:
This section describes how to deploy a server.

### Developer environment:
1. Download [repository](https://github.com/BlackIIIFOX/GoblinSlayerRankSystemClient) client application.
2. Install [PostgreSQL](https://www.postgresql.org/download/) on your machine
3. Create a database in PostgreSQL as `the_contract_system`
4. Go to `.\src\main\resources\application.properties` and set up your environment
```
server.port=<server port, for example 3464>

# Angular web client
spring.resources.static-locations=<path to Angular client (can be empty)>

# Database
spring.datasource.url=<Connection url to database like a jdbc:postgresql://localhost/the_contract_system>
spring.datasource.username=<database username>
spring.datasource.password=<database password>
spring.h2.console.enable=true
spring.jpa.hibernate.ddl-auto=validate

# Liquibase
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml

# JWT
jwt.token.secret=<secret key to generate jwt XxnIa43zAUuo1gXHzkM5>
```

Static-location template for Windows: 
```
spring.resources.static-locations=file:///C:/DATA/MyProject/BigProjects/GoblinSlayerRankSystem/Client/the-contract-system-web-client/dist/the-contract-system-web-client
```
Static-location template for Linux:
```
spring.resources.static-locations=file:/data/www/the-contact-system/client
```

5. Run server from maven script or in IntelliJ

### Production environment:
Deployment to a production environment is an automated process through Jenkins CI.
[Jenkins](http://paulrozhkin.ru:8080/blue/organizations/jenkins/the-contract-system-server-pipeline/branches) automatically deploys software to production server when merged into master.
For changing pipeline see Jenkinsfile in source folder. 
