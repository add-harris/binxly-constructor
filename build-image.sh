#!/usr/bin/env bash

# build distroless
docker build -f src/main/docker/Dockerfile.distroless -t binxly/constructor .
# build standard native
#docker build -f src/main/docker/Dockerfile.native -t binxly/constructor .
