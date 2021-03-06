#!/usr/bin/env bash

$HOME/bin/apache-maven-3.6.3/bin/mvn package -Pnative -Dquarkus.native.container-build=true
