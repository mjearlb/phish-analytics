#!/bin/bash -ex

mvn package
java -cp target/phish-analytics-1.0-SNAPSHOT.jar com.mjearlb.app.App
