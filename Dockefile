FROM maven:3.8.6-eclipse-temurin-11-alpine as build_stage

WORKDIR /home/assignment3

COPY . .

RUN mvn compile war:war

FROM tomcat:jre11

COPY --from=build_stage /home/assignment3/target/assignment.war \
						/usr/local/tomcat/webapps/assignment.war

CMD ["catalina.sh", "run"]