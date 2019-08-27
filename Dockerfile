FROM openjdk:8 
ARG DEPENDENCY=/target/dependency
COPY ${DEPENDENCY} /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.LogisticaApplication"]