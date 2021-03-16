#!/usr/bin/env bash

docker run -i --rm \
        -p 8080:8080 \
        -e CORS_ORIGIN="http://localhost:3000" \
        binxly/constructor
