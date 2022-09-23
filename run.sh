./gradlew clean build jar -Pjasypt.encryptor.password=${JAVA_KEY}
java -jar build/libs/book-0.0.1.jar --jasypt.encryptor.password=${JAVA_KEY}
