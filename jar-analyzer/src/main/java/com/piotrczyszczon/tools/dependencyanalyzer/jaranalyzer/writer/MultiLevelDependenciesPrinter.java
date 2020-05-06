package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.writer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.MultiLevelDependencies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultiLevelDependenciesPrinter
{
  private static final int INITIAL_PRINT_LEVEL = 0;
  private static final int MAXIMUM_PRINT_LEVEL = 6;

  public void print(MultiLevelDependencies multiLevelDependencies, String outputFile) throws IOException
  {
    List<String> allPaths = multiLevelDependencies.getFirstLevelDependencies().stream()
        .map(formatJarDependencyOutput(multiLevelDependencies.getDependencyHierarchyMap()))
        .flatMap(List::stream)
        .collect(Collectors.toList());

    Files.write(Paths.get(outputFile), allPaths);
  }

  private Function<JarClassDependency, List<String>> formatJarDependencyOutput(Map<String, List<JarClassDependency>> dependencyHierarchyMap)
  {
    return dependency -> formatDependency(dependency, dependencyHierarchyMap, INITIAL_PRINT_LEVEL);
  }

  private List<String> formatDependency(JarClassDependency dependency, Map<String, List<JarClassDependency>> dependencyHierarchyMap, int currentPrintLevel)
  {
    List<String> result = new ArrayList<>();

    result.add(dependency.getDependency().getPath());
    if (currentPrintLevel < MAXIMUM_PRINT_LEVEL)
    {
      result.addAll(formatDependencies(dependency.getDependency().getPath(), dependencyHierarchyMap, currentPrintLevel + 1));
    }
    return result;
  }

  private List<String> formatDependencies(String dependencyPath, Map<String, List<JarClassDependency>> dependencyHierarchyMap, int currentPrintLevel)
  {
    if (dependencyHierarchyMap.containsKey(dependencyPath))
    {
      return dependencyHierarchyMap.get(dependencyPath).stream()
          .map(dependency -> formatDependency(dependency, dependencyHierarchyMap, currentPrintLevel))
          .flatMap(List::stream)
          .map(line -> String.format("\t%s", line))
          .collect(Collectors.toList());
    }
    else
    {
      return Collections.emptyList();
    }
  }
}
