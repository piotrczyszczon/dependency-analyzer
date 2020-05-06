package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.MultiLevelDependencies;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiLevelDependencyAnalyzer
{
  private static final int INITIAL_LEVEL = 2;
  private final JarDependencyAnalyzer jarDependencyAnalyzer;
  private final ClassDependencyAnalyzer classDependencyAnalyzer;
  private final int maximumLevel;

  public MultiLevelDependencyAnalyzer(JarDependencyAnalyzer jarDependencyAnalyzer, ClassDependencyAnalyzer classDependencyAnalyzer, int maximumLevel)
  {
    this.jarDependencyAnalyzer = jarDependencyAnalyzer;
    this.classDependencyAnalyzer = classDependencyAnalyzer;
    this.maximumLevel = maximumLevel;
  }

  public MultiLevelDependencies execute(String analyzedJar, String sourceClassesRegex) throws IOException
  {
    List<JarClassDependency> firstLevelDependencies = jarDependencyAnalyzer.execute(analyzedJar, sourceClassesRegex);

    Map<String, List<JarClassDependency>> dependencyHierarchyMap = analyzeNextLevelDependencies(firstLevelDependencies, INITIAL_LEVEL, new ConcurrentHashMap<>());

    return new MultiLevelDependencies(firstLevelDependencies, dependencyHierarchyMap);
  }

  private Map<String, List<JarClassDependency>> analyzeNextLevelDependencies(List<JarClassDependency> dependencies, int currentLevel, Map<String, List<JarClassDependency>> dependencyHierarchyMap)
  {
    dependencies.stream().parallel().forEach(jarClassDependency -> {

      String dependencyPath = jarClassDependency.getDependency().getPath();

      if (!dependencyHierarchyMap.containsKey(dependencyPath))
      {
        List<JarClassDependency> nextLevelDependencies = classDependencyAnalyzer.getClassDependencies(dependencyPath);

        dependencyHierarchyMap.put(dependencyPath, nextLevelDependencies);

        if (currentLevel < maximumLevel)
        {
          analyzeNextLevelDependencies(nextLevelDependencies, increaseLevel(currentLevel), dependencyHierarchyMap);
        }
      }
    });
    return dependencyHierarchyMap;
  }

  private int increaseLevel(int currentLevel)
  {
    return currentLevel + 1;
  }
}
