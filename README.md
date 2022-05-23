# bitdotio-java-example
An example of using Java &amp; JDBC with bit.io

This example downloads a subset of a table from [this public database](https://bit.io/dliden/2020_Census_Reapportionment)

First, download the latest version of the [Postgres JDBC](https://jdbc.postgresql.org/) driver, and
put that jar file in this directory.

Then, to run this code (replace the version of the postgres jar with the version you downloaded): 
```
javac BitioExample.java
java -cp .:postgresql-42.3.5.jar BitioExample
```

