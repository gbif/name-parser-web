# DEPRECATION WARNING
**This project has been discontinued and a name parser frontend is now part of the main [GBIF portal](https://www.gbif.org/tools/name-parser)!***



# GBIF Name Parser Web Frontend
Name parser web frontend using Struts.

# Building

Default settings, suitable for development, are defined in the Maven `pom.xml`.

````shell
mvn clean install
mvn jetty:run
````

These are overridden for a deployment buildusing two Maven profiles, `tools` and `dev`.
