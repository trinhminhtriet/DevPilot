# Rust .gitattributes
*.rs linguist-language=Rust
Cargo.toml linguist-language=Rust
Cargo.lock linguist-language=Rust

# Normalize line endings
* text=auto

# Prevent generated files from polluting diffs
/target/ linguist-generated=true

# Optional: treat documentation as text
*.md text

# Optional: ignore formatting files
rustfmt.toml linguist-generated=true
