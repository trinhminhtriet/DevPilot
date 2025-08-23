

## Usage

### Build
mvn clean package

### Run help
java -jar target/skel-0.0.1-SNAPSHOT.jar --help
java -jar target/skel-0.0.1-SNAPSHOT.jar version

### Generate Rust project
java -jar target/skel-0.0.1-SNAPSHOT.jar init rust --name MyRustApp --dir ./MyRustApp
java -jar target/skel-0.0.1-SNAPSHOT.jar init go --name MyGoApp --dir ./MyGoApp
