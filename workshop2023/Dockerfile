FROM eclipse-temurin:17-jre
MAINTAINER javadominicano.org
#https://vaadin.com/docs/latest/tools/collaboration/going-to-production
COPY src/main/resources/collaboration-engine/ce-license.json /usr/app/ce/
COPY target/workshop.jar /usr/app/app.jar

EXPOSE 50380

ENTRYPOINT ["java",  "-jar", "/usr/app/app.jar"]