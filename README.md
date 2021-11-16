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

