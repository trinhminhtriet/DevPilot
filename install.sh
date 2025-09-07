#!/bin/bash

set -ex

JAR="target/devpilot-0.0.1-SNAPSHOT.jar"
BIN_DIR="$HOME/.local/bin"
DEVPILOT_SH="$BIN_DIR/pilot"

mkdir -p "$BIN_DIR"
cp "$JAR" "$BIN_DIR/devpilot-cli.jar"

cat > "$DEVPILOT_SH" <<EOF
#!/bin/sh
java -jar "\$HOME/.local/bin/devpilot-cli.jar" "\$@"
EOF

ls -la "$BIN_DIR"

chmod +x "$DEVPILOT_SH"

echo "Installed devpilot to $DEVPILOT_SH"
echo "Add $BIN_DIR to your PATH if not already."