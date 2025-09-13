# pilot

```
  ____             ____  _ _       _   
 |  _ \  _____   _|  _ \(_) | ___ | |_
 | | | |/ _ \ \ / / |_) | | |/ _ \| __|
 | |_| |  __/\ V /|  __/| | | (_) | |_
 |____/ \___| \_/ |_|   |_|_|\___/ \__|
```

## Intro

**pilot** is a command-line tool built with **Spring Boot**, **Picocli**, and **Freemarker**.  
It helps developers quickly generate project scaffolding for multiple languages such as **Rust**, **Golang**, **Python**, and **TypeScript**, including essential files like `README.md`, `LICENSE`,
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

- Download the latest JAR from the [releases page](https://github.com/trinhminhtriet/pilot/releases).

### Prerequisites

- **Java 17+**
- **Maven 3.8+**

### Build

```shell
git clone https://github.com/trinhminhtriet/DevPilot.git
cd DevPilot
mvn clean package
```

### Install as a shortcut command (Linux/macOS)

```shell
# Copy JAR and create shortcut script in $HOME/.local/bin
./install.sh
# Make sure $HOME/.local/bin is in your PATH
export PATH="$HOME/.local/bin:$PATH"
```

## 💡 Usage

### Run help

```shell
pilot --help
```

Now you can use:

```shell
pilot project init --name myApp --lang go --dir ./myApp

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