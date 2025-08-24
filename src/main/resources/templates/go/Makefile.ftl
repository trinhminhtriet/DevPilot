# Go Makefile
.PHONY: build test clean

build:
	go build -o bin/${projectName} ./...

test:
	go test ./...

clean:
	rm -rf bin/
