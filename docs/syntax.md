
## Package Structure

```
com.trinhminhtriet.devpilot
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
└── DevPilotApplication.java # Spring Boot entry point
```

common: Shared commands such as `info`, `config`, `clean`, `doctor`, `upgrade`, `release`, `interactive`, `scaffold`, `serve`, `doc`, `add`, `deps`, `format`, `lint`, `test` are placed in this package. These commands are language-agnostic and can be applied to any project.


## Proposed CLI Syntax

```
devpilot <group> <action> [options]
```

Examples:
- `devpilot project init --lang rust --name myproj`
- `devpilot project info --dir .`
- `devpilot project clean`
- `devpilot project release`
- `devpilot config set author.name="Your Name"`
- `devpilot config show`
- `devpilot test run`
- `devpilot lint run`
- `devpilot format run`
- `devpilot deps list`
- `devpilot deps update`
- `devpilot add readme`
- `devpilot add license`
- `devpilot scaffold microservice`
- `devpilot serve web`
- `devpilot doc generate`
- `devpilot upgrade`
- `devpilot doctor`

This approach helps you:
- Group commands by topic (`project`, `config`, `test`, `add`, ...)
- Easily add new subcommands without name conflicts
- Integrate Picocli easily with the `@Command(subcommands = {...})` annotation

Would you like me to create a command skeleton following this structure for the command groups?