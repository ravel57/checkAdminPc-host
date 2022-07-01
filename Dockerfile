FROM openjdk:11
EXPOSE 8443
RUN mkdir /app
COPY ./build/libs/checkAdminPcHost-0.0.1-SNAPSHOT.jar /app/checkAdminPcHost.jar
COPY ./src/main/resources/cer.pfx /src/main/resources/cer.pfx
CMD java -jar /app/checkAdminPcHost.jar
