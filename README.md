Kotlin Server Framework Smackdown
---------------------------------


## Micronaut

Dev:
```
./gradlew -t :js-client:run
./gradlew -t :micronaut-server:testRun
```

Prod:
```
export JASYNC_CLIENT_HOST=localhost
export JASYNC_CLIENT_PORT=5432
export JASYNC_CLIENT_DATABASE=postgres
export JASYNC_CLIENT_USERNAME=postgres
export JASYNC_CLIENT_PASSWORD=password
./gradlew :micronaut-server:run
```

Container Build & Run:
```
./gradlew :micronaut-server:dockerBuild
docker run -it --network host \
  -eJASYNC_CLIENT_HOST=localhost -eJASYNC_CLIENT_PORT=5432 \
  -eJASYNC_CLIENT_DATABASE=postgres \
  -eJASYNC_CLIENT_USERNAME=postgres -eJASYNC_CLIENT_PASSWORD=password \
  micronaut-server
```

GraalVM Native Image:
*Broken: jasync_sql_extensions uses `ClassLoader.defineClass`*
```
./gradlew :quarkus-server:dockerBuildNative
```

- GORM is nice but doesn't seem to play well with MPP & jasync
    - Annotations for Entity can't be in MPP project
- Coroutine support
- Compile-time DI
- TestContainers: Some struggle to create a bean with the dynamic config



## Quarkus

Dev:
```
./gradlew -t :js-client:run
./gradlew :quarkus-server:quarkusDev
```

Prod:
```
export QUARKUS_DATASOURCE_REACTIVE_URL=postgresql://localhost:5432/postgres
export QUARKUS_DATASOURCE_USERNAME=postgres
export QUARKUS_DATASOURCE_PASSWORD=password
./gradlew :quarkus-server:build
java -jar quarkus-server/build/quarkus-server-unspecified-runner.jar
```

Container Build & Run:
```
./gradlew :quarkus-server:dockerBuild
docker run -it --network host \
  -eQUARKUS_DATASOURCE_REACTIVE_URL=postgresql://localhost:5432/postgres -eQUARKUS_DATASOURCE_USERNAME=postgres -eQUARKUS_DATASOURCE_PASSWORD=password \
  quarkus-server
```

GraalVM Native Image:
*Broken: Static assets do not work. `Cannot construct instance of bars.Bar`* 
```
./gradlew :quarkus-server:nativeDockerBuild
```

- BOM Difficulties with Kotlin 1.4
- Gradle Plugin classpath not configurable
- No coroutines


## Spring Boot

Dev:
```
./gradlew -t :js-client:run
./gradlew -t :springboot-server:classes
./gradlew :springboot-server:bootRun
```

Prod:
```
export SPRING_R2DBC_URL=r2dbc:postgresql://localhost/postgres
export SPRING_R2DBC_USERNAME=postgres
export SPRING_R2DBC_PASSWORD=password
./gradlew :springboot-server:run
```
(TODO: Disable DevTools?)

Container Build & Run:
```
./gradlew :springboot-server:bootBuildImage
docker run -it --network host \
  -e SPRING_R2DBC_URL=r2dbc:postgresql://localhost/postgres -eSPRING_R2DBC_USERNAME=postgres -eSPRING_R2DBC_PASSWORD=password \
  springboot-server
```

GraalVM Native Image:
*Could not create native image due to variety of issues*
```
./gradlew :springboot-server:bootBuildImage -Pnative

# Config Gen (With the native-image-agent installed)
export SPRING_R2DBC_URL=r2dbc:postgresql://localhost/postgres
export SPRING_R2DBC_USERNAME=postgres
export SPRING_R2DBC_PASSWORD=password
export JAVA_HOME=~/bin/graalvm-ce-java11-20.3.0
export PATH=$JAVA_HOME/bin:$PATH
./gradlew :springboot-server:bootJar
java -agentlib:native-image-agent=config-output-dir=springboot-server/src/main/resources/META-INF/native-image \
  -jar springboot-server/build/libs/springboot-server.jar
```

- R2DBC template requires data class annotations


## Ktor

Dev:
```
./gradlew -t :js-client:run
./gradlew :ktor-server:testRun
```

Prod:
```
export JASYNC_CLIENT_HOST=localhost
export JASYNC_CLIENT_PORT=5432
export JASYNC_CLIENT_DATABASE=postgres
export JASYNC_CLIENT_USERNAME=postgres
export JASYNC_CLIENT_PASSWORD=password
./gradlew :ktor-server:run
```

Container Build & Run:
```
./gradlew :ktor-server:jibDockerBuild
docker run -it --network host \
  -eJASYNC_CLIENT_HOST=localhost -eJASYNC_CLIENT_PORT=5432 \
   -eJASYNC_CLIENT_DATABASE=postgres \
   -eJASYNC_CLIENT_USERNAME=postgres -eJASYNC_CLIENT_PASSWORD=password \
  ktor-server
```

- Autoreload is currently broken
- Exposed (JetBrains SQL) is not Reactive
- MPP Client


## Perf Tests
```
./gradlew :perf-test:gatlingRun
```


## TODO
- `html-client` with `webjars-locator`
- Copy js to server project