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