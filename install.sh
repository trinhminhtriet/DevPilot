#!/bin/bash

JAR="target/skel-0.0.1-SNAPSHOT.jar"
BIN_DIR="$HOME/.local/bin"
SKEL_SH="$BIN_DIR/skel"

mkdir -p "$BIN_DIR"
cp "$JAR" "$BIN_DIR/skel-cli.jar"

cat > "$SKEL_SH" <<EOF
#!/bin/sh
java -jar "\$HOME/.local/bin/skel-cli.jar" "\$@"
EOF

chmod +x "$SKEL_SH"

echo "Installed skel to $SKEL_SH"
echo "Add $BIN_DIR to your PATH if not already."