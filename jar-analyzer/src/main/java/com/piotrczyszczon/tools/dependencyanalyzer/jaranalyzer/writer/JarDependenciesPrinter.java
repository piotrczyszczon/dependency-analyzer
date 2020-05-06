package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.writer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JarDependenciesPrinter
{
  public void print(List<JarClassDependency> jarDependencies, String outputFile) throws IOException
  {
    List<String> allPaths = jarDependencies.stream()
        .map(formatJarDependencyOutput())
        .sorted()
        .collect(Collectors.toList());

    Files.write(Paths.get(outputFile), allPaths);
  }

  private Function<JarClassDependency, String> formatJarDependencyOutput()
  {
    return dependency -> String.format("%s -> %s", dependency.getClassname(), dependency.getDependency().getPath());
  }
}
