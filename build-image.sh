#!/usr/bin/env bash

./build-exec.sh
docker build -f src/main/docker/Dockerfile.distroless -t binxly/constructor .
