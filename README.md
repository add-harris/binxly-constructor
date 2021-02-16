# constructor project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `constructor-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/constructor-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/constructor-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Creating a native image

The executable will not run on mac as is a linux executable. To run on mac bake into an image using the 
`Dockerfile.native` provided.

```bash
docker build -f src/main/docker/Dockerfile.native -t binxley/constructor .
```

Build a sdistroless

```bash
docker build -f src/main/docker/Dockerfile.distroless -t binxley/constructor-distro .
```

Run image with:

```bash
docker run -i --rm -p 8080:8080 binxley/constructor
```

```bash
docker run -i --rm -p 8080:8080 binxley/constructor-distro
```


# RESTEasy JAX-RS

<p>A Hello World RESTEasy resource</p>

Guide: https://quarkus.io/guides/rest-json
