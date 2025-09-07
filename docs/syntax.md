
## Package cấu trúc

```
com.trinhminhtriet.app.cli.skel
│
├── command/
│   ├── common/         # Các lệnh dùng chung: 
│   ├── rust/           # Lệnh riêng cho Rust: InitRustCommand, RefactorRustCommand, ...
│   ├── python/         # Lệnh riêng cho Python: InitPythonCommand, ...
│   ├── go/             # Lệnh riêng cho Go: InitGoCommand, ...
│   ├── typescript/     # Lệnh riêng cho TypeScript: InitTypescriptCommand, ...
│   └── ...             # Có thể mở rộng cho các ngôn ngữ khác
│
├── service/            # Interface và logic cho các service (ConfigService, TemplateRenderService, TomlService, ...)
│   ├── impl/           # Triển khai các service
│
├── model/              # Các class model, DTO, entity dùng chung
│
├── util/               # Các tiện ích, helper dùng chung
│
├── config/             # Cấu hình ứng dụng, constants, enums
│
├── templates/          # Các file template cho từng ngôn ngữ và file chuẩn
│
├── resources/          # File cấu hình, tài nguyên ngoài (application.yml, logback.xml, ...)
│
└── SkelApplication.java # Entry point Spring Boot
```

common: Các lệnh dùng chung như `info`, `config`, `clean`, `doctor`, `upgrade`, `release`, `interactive`, `scaffold`, `serve`, `doc`, `add`, `deps`, `format`, `lint`, `test` sẽ được đặt trong package này. Các lệnh này không phụ thuộc vào ngôn ngữ cụ thể và có thể áp dụng cho mọi dự án.


## Syntax đề xuất cho CLI

```
skel <group> <action> [options]
```

Ví dụ:
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

Cách này giúp bạn:
- Gom nhóm các lệnh theo chủ đề (`project`, `config`, `test`, `add`, ...)
- Dễ thêm subcommand mới mà không xung đột tên
- Tích hợp Picocli dễ dàng với annotation `@Command(subcommands = {...})`

Bạn muốn tôi tạo khung lệnh theo cấu trúc này cho các nhóm lệnh không?