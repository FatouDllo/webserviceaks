# Utilisez une image de base Java
FROM openjdk:17

# Créez un répertoire de travail
WORKDIR /app

# Copiez les fichiers JAR de votre application
COPY ./ /app/monSecondWebService/

WORKDIR /app/monSecondWebService

RUN /app/monSecondWebService/mvnw clean install -DskipTests=true

RUN ./mvnw clean package -DskipTests=true

# Commande d'exécution de l'application
CMD ["java", "-jar", "target/monSecondWebService-0.0.1-SNAPSHOT.jar"]
