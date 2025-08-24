# TypeScript Makefile
.PHONY: build clean

build:
	npm install
	npm run build

clean:
	rm -rf dist/
