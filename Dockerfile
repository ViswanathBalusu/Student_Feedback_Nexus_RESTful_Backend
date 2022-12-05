FROM maven:3.8.6-eclipse-temurin-17-alpine as build_stage

WORKDIR /home/assignment3

COPY . .

RUN mvn compile war:war

FROM tomcat:jre17

COPY --from=build_stage /home/assignment3/target/assignment3-0.0.1-SNAPSHOT \
						/usr/local/tomcat/webapps/assignment.war

CMD ["catalina.sh", "run"]