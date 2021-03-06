#!/usr/bin/env bash

$HOME/bin/apache-maven-3.6.3/bin/mvn package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.distroless -t binxly/constructor .
