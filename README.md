# Welcome to spring-reactive 🚀

![Version](https://img.shields.io/badge/version-0.1.0-blue.svg?cacheSeconds=2592000)
[![Documentation](https://img.shields.io/badge/documentation-yes-brightgreen.svg)](https://github.com/eduardo-rdguez/spring-reactive/blob/main/README.md)

> This project has been developed with Spring Boot, Spring Reactive Web, Spring Data Reactive MongoDB and Lombok.

## Specs

- Java `8`
- Gradle `7.5.1`
- Spring Boot `2.7.6`

## MongoDB server

* Run service with `docker-compose -f docker-compose.yml up --build`
* Stops and removes containers with `docker-compose down --rmi all -v`

## Run spring app

* Runs this project as a Spring Boot application with `./gradlew bootRun`

## Run angular app

To start your server:

* Install dependencies with `npm install`
* Start angular app with `ng serve`

Now you can visit http://localhost:4200/words from your browser.

## JMeter

You can view the test results
with `./jmeter.sh -n -t "spring-reactive.jmx" -l "spring-reactive.csv" -e -o "./spring-reactive-results`
or executing `spring-reactive.jmx` with **JMeter in GUI mode**.

## Learn more

* Docker command-line reference: https://docs.docker.com/compose/reference/
* JMeter: https://jmeter.apache.org/usermanual/get-started.html
* Web on Reactive Stack: https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html

## Author

- Website: <https://eduardo-rdguez.github.io/>
- Twitter: [@\_eduardguez](https://twitter.com/\_eduardguez)
- Github: [@eduardo-rdguez](https://github.com/eduardo-rdguez)
