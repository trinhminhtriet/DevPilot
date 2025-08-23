# Skel CLI

## Intro

**Skel CLI** is a command-line tool built with **Spring Boot**, **Picocli**, and **Freemarker**.  
It helps developers quickly generate project scaffolding for multiple languages such as **Rust**, *
*Golang**, **Python**, and **TypeScript**, including essential files like `README.md`, `LICENSE`,
`.gitignore`, `.editorconfig`, `.vscode/settings.json`, and `CHANGELOG.md`.

The goal of this project is to provide a unified, extensible CLI tool for initializing projects with
best practices and templates.

---

## ✨ Features

- 🚀 Generate project scaffolds for multiple languages (Rust, Go, Python, TypeScript).
- 📑 Built-in templates for:
    - `README.md`
    - `LICENSE`
    - `CHANGELOG.md`
    - `.editorconfig`
    - `.gitignore`
    - `.gitattributes`
    - `.vscode/settings.json`
- 🛠 Extensible design to easily add more languages and templates.
- 🔌 Powered by **Picocli** for CLI commands and **Freemarker** for template rendering.
- 📊 Integrated with **SLF4J + Logback** for structured logging.

---


## 🚀 Installation

### Prerequisites

- **Java 17+**
- **Maven 3.8+**

### Build

```shell
git clone https://github.com/your-username/skel-cli.git
cd skel-cli
mvn clean package
```

## 💡 Usage

### Run help

```shell
java -jar target/skel-0.0.1-SNAPSHOT.jar --help
```

### Check version

```shell

java -jar target/skel-0.0.1-SNAPSHOT.jar version
```

### Generate Rust project

```shell
java -jar target/skel-0.0.1-SNAPSHOT.jar init rust --name MyRustApp --dir ./MyRustApp
```

### Generate Go project

```shell
java -jar target/skel-0.0.1-SNAPSHOT.jar init go --name MyGoApp --dir ./MyGoApp
```

### Generate Python project

```shell
java -jar target/skel-0.0.1-SNAPSHOT.jar init python --name MyPythonApp --dir ./MyPythonApp
```

### Install as a shortcut command (Linux/macOS)

```shell
# Copy JAR and create shortcut script in $HOME/.local/bin
bash install.sh
# Make sure $HOME/.local/bin is in your PATH
export PATH="$HOME/.local/bin:$PATH"
```

Now you can use:

```shell
skel init rust --name MyRustApp --dir ./MyRustApp
skel init go --name MyGoApp --dir ./MyGoApp
skel init python --name MyPythonApp --dir ./MyPythonApp
```

## 🤝 How to contribute

We welcome contributions!

- Fork this repository;
- Create a branch with your feature: `git checkout -b my-feature`;
- Commit your changes: `git commit -m "feat: my new feature"`;
- Push to your branch: `git push origin my-feature`.

Once your pull request has been merged, you can delete your branch.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.