# Intermediate Build For Building Artifacts
FROM maven:3.8.6-eclipse-temurin-17-alpine as build_stage

WORKDIR /home/assignment3

COPY . .

RUN mvn compile war:war

FROM tomcat:jre17

COPY --from=build_stage /home/assignment3/target/assignment3.war \
						/usr/local/tomcat/webapps/assignment3.war

CMD ["catalina.sh", "run"]