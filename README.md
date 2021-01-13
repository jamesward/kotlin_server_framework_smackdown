Kotlin Server Framework Smackdown
---------------------------------


## Micronaut
```
./gradlew -t :js-client:run
./gradlew -t :micronaut-server:testRun
```

- GORM is nice but doesn't seem to play well with MPP & jasync
    - Annotations for Entity can't be in MPP project
- Coroutine support
- Compile-time DI
- TestContainers: Some struggle to create a bean with the dynamic config

## Quarkus
```
./gradlew -t :js-client:run
./gradlew :quarkus-server:quarkusDev
```

- No coroutines


## Spring Boot
```
./gradlew -t :js-client:run
./gradlew -t :springboot-server:classes
./gradlew :springboot-server:bootRun
```


## TODO
- `html-client` with `webjars-locator`