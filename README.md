# Intro
TODO


## Principle of the solution
TODO


## Build
Our project is built and deployed using the Apache Ant toolset.
The build script is entirely implemented in "./build.xml".
In order to build an executable, a user needs to run the command:
```
ant distribute
```
This generates an executable jar file with our main simulation program.


## Usage
Once the executable has been generated, our program may be tested using
```
java -jar dist/BitTracker-<version>.jar
```
All the other options may be discovered using the --help input.


## Test
All these tests are based on JUnit and may be executed using
```
ant test
```


## Generate Java doc
The javadoc of all the classes of this project may be generated using
```
ant doc
```
This results in creating the html file corresponding to each class.
 This file is stored within "./doc".

## Note
Some libraries (such as the Debug or Log libraries) have been reimplemented in this project.
Our re-implementation is a lightweight code that allows this project to only use standard libraries.
This is done on purpose in order to ease the deployment and the review.

## Futur implementations
TODO
1. Javadoc: correct @warning keyword
1. Add the support for thread-affinity by scanning the number of CPU and distributing the threads
1. Finish the implementation of the concurrent checker:
   1. Implement the function to decide and process the clustering between groups of mowers.
   1. Implement a decision function to decide which checker to use (simple of clustered) depending on the number of mower and concurrent threads.
1. Generate different scenarios for unit tests