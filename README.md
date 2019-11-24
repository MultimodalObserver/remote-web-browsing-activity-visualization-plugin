# MO Web Browsing Activity Capture Plugin

Developed by Abraham Cerda (abraham.cerda@usach.cl).

## Build
To build this project you must configure your build tool (Maven, Gradle, integrated IDE build tool or manually) to generate an executable Jar file that must contains the MO-Client JAR file inside.

## Run

To run this project you must run the file that was previously built with the command:

 ~~~
java -jar <path_to_built_jar>
 ~~~

## Using more plugins


To include more plugins within the MO-Client instance that is created by the jar file, you must place the jars of the others plugins in the *plugins* folder that is generated at the jar level after the first MO execution. 
