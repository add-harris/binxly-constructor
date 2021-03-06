# Binxly Constructor

This project uses Quarkus, the Supersonic Subatomic Java Framework.

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

Note: The executable will not run on mac as is a linux executable. To run on mac bake into an image using the
`Dockerfile`'s provided.

## Creating a native image

Build as distroless:

```bash
docker build -f src/main/docker/Dockerfile.distroless -t binxley/constructor .
```

Run image with:

```bash
docker run -i --rm -p 8080:8080 binxley/constructor
```

Then access the application on `http://localhost:8080/constructor`

## Build

The current is managed by Google Cloud Build, configured in the `cloudbuild.yaml`
file. This will build the docker image and push it to `gcr`. 

However, Cloud Build can not handle building the native executable. This must 
be pre-built and pushed to github. So either make sure the native executable is
up-to-date before pushing or run the provider helper script `git-push.sh`

