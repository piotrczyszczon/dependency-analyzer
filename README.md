# Overview
Wrapper for jdeps tool. Created mainly because documentation of jdeps is weak and it does not fully explain output format. Especially when it is used to analyze recursive/multi-level dependencies.

This tool is designed in the way it calls jdeps to analyze class dependencies of source jar file so familiarity with jdeps will be helpful.

# Usage
As that tool works by invoking jdeps command it should be available on PATH when running it.
```
dependency-analyzer <configuration_file>
```
# Example configuration file
```yaml
---
# Tool will analyze source jar file and look for dependencies. It will search for dependencies only withing classpath
classPath:
  - 
# Source Jar is the file to analyze. Tool will scan classes of sourceJar and print their dependencies
sourceJar: 
# Usually it is not efficient to analyze all classes of some jar file. sourceClassesRegex allows you to narrow analyzed classes
# to only these that match regular expresion. It is applied to class name including package
# eg: sourceClassesRegex: com.piotrczyszczon.*   - it will analyze only classes inside package com.piotrczyszczon
sourceClassesRegex: .*
# dependencyJarRegex is used to filter out results. by typing name of jar you can exclude this jar from the output. 
dependencyJarRegex:
# Similar to dependencyJarRegex, here you can filter out classes from output. eg. you can narrow output packages to see only most interesting dependencies
dependencyClassesRegex: .*
# File to print output of the analysis
outputPath:
# Amount of levels of dependencies that you want to analyze
# Eg: class  A uses class B, and Class B uses class C
# when maximumLevel is set to 1
#      you will only see in the output that class B is used by class A
# when maximumLevel is set to 2
#      you will see that class B is used by class A
#      but also that class C is used by class A
maximumLevel: 1
...
```

# Output format 

## When maximumLevel: 1

Here we can only see dependencies from sourceJar to targetJar so output can have following form
```yaml
<source jar class> -> <class that is used by class in source jar>

first_class_in_source_jar -> first_class_in_target_jar
first_class_in_source_jar -> second_class_in_target_jars
second_class_in_source_jar -> first_class_in_target_jar
```

## When maximumLevel: > 1
Here we show dependencies of source jar and dependencies of their dependencies.

Each dependency level is separated by tab. On first level we have straight dependencies from source jar to target jar.
Then in next levels each dependency is analyzed recursively their dependencies are also printed.
```yaml
<source jar class>
    <class that is used by source jar class - first level>
        <class that is used by class from first level>
        ...
```

# Performance
Because we are running jdeps as separate process under the hood it's quite slow. So it's ususaly better to narrow amount of analyzed classes with usage of regular expressions in configuration.
