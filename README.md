# Selenium Java oktatás

## Lab 1

* Statikus HTML oldal létrehozása

## Lab 2

* Naplózás

```shell
mvn dependency:copy-dependencies
mvn jar:test-jar
junit-platform-console-standalone-1.8.1.jar
java -cp junit-platform-console-standalone-1.8.1.jar;intro-tests-1.0-SNAPSHOT-tests.jar;jars/* org.junit.platform.console.ConsoleLauncher --scan-class-path intro-tests-1.0-SNAPSHOT-tests.jar --disable-ansi-colors --disable-banner
```

Ha viszont mindet egy jar-ba szeretnétek csomagolni, akkor
Maven Assembly Plugint kell használni:

## Locations application

```shell
docker compose up
```


## Jenkins

```shell
docker build -f Dockerfile.jenkins -t locations-jenkins .
docker run -d --network jenkins --volume jenkins-data:/var/jenkins_home --volume /var/run/docker.sock:/var/run/docker.sock --publish 8090:8080 --name locations-jenkins locations-jenkins
docker build -t intro-tests .
mvn -N io.takari:maven:0.7.7:wrapper
```