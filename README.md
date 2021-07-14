# GoblinSlayerSystemServer

Course project on discipline "Методология программной инженерии" in ITMO university.
The server application for the contract system in the world "Goblin Slayer".

---

## Documentation
- [dbDiagram](https://github.com/paulrozhkin/the-contract-system-server/blob/master/architecture/Database/schema.database) (copy to https://dbdiagram.io/d)
- [Server API](https://github.com/paulrozhkin/the-contract-system-server/blob/master/architecture/OpenApi/api.yaml) (copy to https://editor.swagger.io/)
- [Vision](https://docs.google.com/document/d/1v9y4WRPyGFKc2vYpef0RAII6H3hu_6BteBNNabEQrt4/edit?usp=sharing)
- [SRS](https://docs.google.com/document/d/1GuGBWnk01gyCZ_quvsVYsAwuujcAvJF3VFE-AENrZhA/edit?usp=sharing)
- [UC](https://docs.google.com/document/d/1W0LttqI8l5DLXEj_xDsUxQFXCKT3JOnX457nTiAJdzM/edit?usp=sharing)
- [SDP](https://docs.google.com/document/d/1gGAdmLFFV3Mnit6tC0noWmpcAjFWzCy2LnP6-FkwEkc/edit?usp=sharing)
- [BC](https://docs.google.com/document/d/1Q4miZHVgvsvHZHKNIofd2p-m-RFCBa4sEeq6GsrlpWM/edit?usp=sharing)
- [RL](https://docs.google.com/document/d/1SFaHzvcG8X34E34smEvTiTXnqi0El5OJ9S0cJYgifJI/edit?usp=sharing)
- [Gloss](https://docs.google.com/document/d/1jVtoOmR9ThoDD123HPH280iEx5iIAhtQ9C4Zyc4ctSk/edit?usp=sharing)
- [SAD](https://docs.google.com/document/d/18d3eoLBNV5zT73ljh_fpYsyj6Ia5jzJV495qChK7FRs/edit?usp=sharing)

## Linked Repositories
- [Client](https://github.com/paulrozhkin/the-contract-system-web-client)
- [Modelio](https://github.com/paulrozhkin/the-contract-system-modelio)

---

## Deployment:
This section describes how to deploy a server.

### Deployment diagram

<a href="#"> <img title="Contract System" src="https://raw.githubusercontent.com/paulrozhkin/the-contract-system-server/master/architecture/images/DeploymentDiagram.png" alt="Deployment diagram" width="800"/> </a>

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

## MULTIPART (MultipartProperties)
# Enable multipart uploads
spring.servlet.multipart.enabled=true

# Threshold after which files are written to disk.
spring.servlet.multipart.file-size-threshold=2KB

# Max file size.
spring.servlet.multipart.max-file-size=200MB

# Max Request Size
spring.servlet.multipart.max-request-size=215MB

# All files uploaded through the REST API will be stored in this directory
files.upload-dir=<path to files>
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
[Jenkins](http://jenkins.paulrozhkin.ru/blue/organizations/jenkins/the-contract-system-server-pipeline/branches) automatically deploys software to production server when merged into master.
For changing pipeline see Jenkinsfile in source folder. 

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2020 © <a href="https://github.com/paulrozhkin" target="_blank">Paul Rozhkin</a>.
