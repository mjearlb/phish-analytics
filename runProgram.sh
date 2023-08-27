#!/bin/bash -ex

mvn compile
mvn exec:java -Dexec.mainClass="com.mjearlb.app.App"
