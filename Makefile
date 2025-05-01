APP_NAME=final-project

.PHONY: all build test lint package run clean

all: build test lint package

build:
	mvn clean compile

test:
	mvn test

lint:
	mvn checkstyle:check

package:
	mvn package -DskipTests

clean:
	mvn clean
