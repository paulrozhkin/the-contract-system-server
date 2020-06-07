# GoblinSlayerSystemServer

## Documentation
- [Server API](https://app.swaggerhub.com/apis/ForsaiR/GoblinSlayerSystem/)

## Description

Course project on discipline "Методология программной инженерии".
The server application for the contract system in the world "Goblin Slayer".

## Deployment:
This section describes how to deploy a server with an Angular client application.

1. Download [repository](https://github.com/BlackIIIFOX/GoblinSlayerRankSystemClient) client application.
2. Build angular application with the command:
`ng build --prod` (you need to install `Angular CLI` if necessary)
3. Copy all files from `dirClient\dist\GoblinSlayerRankSystemClient` to `dirServer/src/main/resources/public`
4. Run maven script `install` in Intellij
5. Copy Jar file from `dirServer/target/*.jar` to target and
run `java -jar *.jar`
