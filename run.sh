#!/bin/sh
sbt ";clean;reload;docker:stage" && docker-compose up --build
