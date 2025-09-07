
## Package Structure

```
com.trinhminhtriet.app.cli.skel
│
├── command/
│   ├── common/         # Shared commands: 
│   ├── rust/           # Rust-specific commands: InitRustCommand, RefactorRustCommand, ...
│   ├── python/         # Python-specific commands: InitPythonCommand, ...
│   ├── go/             # Go-specific commands: InitGoCommand, ...
│   ├── typescript/     # TypeScript-specific commands: InitTypescriptCommand, ...
│   └── ...             # Extendable for other languages
│
├── service/            # Interfaces and logic for services (ConfigService, TemplateRenderService, TomlService, ...)
│   ├── impl/           # Service implementations
│
├── model/              # Shared model, DTO, entity classes
│
├── util/               # Shared utilities and helpers
│
├── config/             # Application configuration, constants, enums
│
├── templates/          # Template files for each language and standard files
│
├── resources/          # Configuration files, external resources (application.yml, logback.xml, ...)
│
└── SkelApplication.java # Spring Boot entry point
```

common: Shared commands such as `info`, `config`, `clean`, `doctor`, `upgrade`, `release`, `interactive`, `scaffold`, `serve`, `doc`, `add`, `deps`, `format`, `lint`, `test` are placed in this package. These commands are language-agnostic and can be applied to any project.


## Proposed CLI Syntax

```
skel <group> <action> [options]
```

Examples:
- `skel project init --lang rust --name myproj`
- `skel project info --dir .`
- `skel project clean`
- `skel project release`
- `skel config set author.name="Your Name"`
- `skel config show`
- `skel test run`
- `skel lint run`
- `skel format run`
- `skel deps list`
- `skel deps update`
- `skel add readme`
- `skel add license`
- `skel scaffold microservice`
- `skel serve web`
- `skel doc generate`
- `skel upgrade`
- `skel doctor`

This approach helps you:
- Group commands by topic (`project`, `config`, `test`, `add`, ...)
- Easily add new subcommands without name conflicts
- Integrate Picocli easily with the `@Command(subcommands = {...})` annotation

Would you like me to create a command skeleton following this structure for the command groups?