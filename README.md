# constructor project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
mvn3 compile quarkus:dev
```

Note: `mvn3` refers to my local copy of maven 3.6.3 as a minimum of 3.6.2 is required for quarkus. 

## Creating a native executable

You can create a native executable using: 
```shell script
mvn3 package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/constructor-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Creating a native image

The executable will not run on mac as is a linux executable. To run on mac bake into an image using the 
`Dockerfile`'s provided.

Build as distroless:

```bash
docker build -f src/main/docker/Dockerfile.distroless -t binxley/constructor .
```

Run image with:

```bash
docker run -i --rm -p 8080:8080 binxley/constructor
```

Then access the application on `http://localhost:8080/constructor`
