{
  "name": "${projectName}",
  "version": "0.1.0",
  "description": "A TypeScript project",
  "main": "dist/index.js",
  "scripts": {
    "build": "tsc",
    "start": "node dist/index.js",
    "test": "echo \"No test specified\" && exit 0"
  },
  "author": "${user.name} <${user.email}>",
  "license": "MIT",
  "devDependencies": {
    "typescript": "^5.0.0"
  }
}
