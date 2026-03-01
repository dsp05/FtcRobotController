#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

# Webots home
export WEBOTS_HOME="${WEBOTS_HOME:-/Applications/Webots.app/Contents}"

if [ ! -d "$WEBOTS_HOME" ]; then
    echo "Error: Webots not found at $WEBOTS_HOME"
    echo "Install Webots or set WEBOTS_HOME environment variable."
    exit 1
fi

CONTROLLER_JAR="${WEBOTS_HOME}/lib/controller/java/Controller.jar"
if [ ! -f "$CONTROLLER_JAR" ]; then
    echo "Error: Controller.jar not found at $CONTROLLER_JAR"
    exit 1
fi

# Directories
SRC_DIR="$SCRIPT_DIR/sim/src/main/java"
TEAMCODE_DIR="$SCRIPT_DIR/../TeamCode/src/main/java"
OUT_DIR="$SCRIPT_DIR/controllers/FtcBridge"

# Clean and create output directory
BUILD_DIR="$SCRIPT_DIR/sim/build/classes"
rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"
mkdir -p "$OUT_DIR"

# Find all Java sources
echo "Compiling..."
find "$SRC_DIR" "$TEAMCODE_DIR" -name "*.java" > /tmp/ftc_sim_sources.txt

# Compile
javac --release 11 \
    -cp "$CONTROLLER_JAR" \
    -d "$BUILD_DIR" \
    @/tmp/ftc_sim_sources.txt

# Package into JAR with manifest
echo "Main-Class: FtcBridge" > /tmp/ftc_manifest.txt
jar cfm "$OUT_DIR/FtcBridge.jar" /tmp/ftc_manifest.txt -C "$BUILD_DIR" .

echo "Build successful. FtcBridge.jar deployed to $OUT_DIR"

# Launch Webots with the world
echo "Launching Webots..."
open -a Webots "$SCRIPT_DIR/worlds/ftc_field.wbt"
