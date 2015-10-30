# GBIF Name Parser Web Frontend

Name parser web frontend

# Building

Default settings, suitable for development, are defined in the Maven `pom.xml`.

````shell
mvn clean install
mvn jetty:run
````

These are overridden for a deployment buildusing two Maven profiles, `tools` and `dev`.
