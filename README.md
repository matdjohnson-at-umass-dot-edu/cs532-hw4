# CS-532 - HW4 - Flink

Repository URL: `https://github.com/matdjohnson-at-umass-dot-edu/cs532-hw4`

## IntelliJ configuration

Steps were written for IntelliJ 2023.3.4 Ultimate Edition.

1) Open directory of project in IntelliJ, triggering auto import of maven project
1) Configure IntelliJ "Project Structure" to use Java 11 JDK
   1) Java 11 can be installed using apt-get, brew, another package manager, or some unmanaged download for Windows
   1) Project Structure is configured from "File" drop down menu at top of screen
1) Create build configuration for `Job` main class
   1) Open the class file, click the play button in margin of class file
1) Configure build configuration to add dependencies in the "provided" scope to the classpath
   1) Select the drop down menu from the "run" toolbar in top right of primary window
   1) Select the "Edit configurations" menu option
   1) Select the build configuration for main class file
   1) Under "Modify Options", select 'Add dependencies within "provided" scope to classpath'
1) Run the build configuration from the IntelliJ "run" toolbar

## Run with maven

The program can also be run with maven.

Ensure that JAVA_HOME is set to a Java 11 JDK.

```bash
./mvnw clean install
java -jar target/HW4-0.1.jar
```

