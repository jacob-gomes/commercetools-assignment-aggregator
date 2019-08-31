JDK version: 1.8.0_221 
Maven Version: 3.6.1
OS: Windows 7
Technology Used: Spring boot 2
Message broker used: Kafka -- CloudKarafka

===========================================================================================================================

For building the code:

The code uses maven to build the artifactory 

Open a cmd terminal, naviagate to the location of pom.xml(./../commercetools-assignment-aggregator)


Use the following command to build:
mvn clean install


Sonar check:
mvn verify jacoco:report sonar:sonar 


I have pasted a sample result in the directory

===========================================================================================================================
To run the application, execute the following command:
java -jar target\commercetools-assignment-aggregator-0.0.1-SNAPSHOT.jar

Port exposed: 80

Swagger-ui:
Sample URL: http://localhost/swagger-ui.html

Have used H2 database:
URL: http://localhost/h2-console/
username: user
password: password