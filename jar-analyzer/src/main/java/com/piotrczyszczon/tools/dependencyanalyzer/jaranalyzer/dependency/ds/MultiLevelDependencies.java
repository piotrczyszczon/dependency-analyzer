package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds;

import java.util.List;
import java.util.Map;

public class MultiLevelDependencies
{
  private final List<JarClassDependency> firstLevelDependencies;
  private final Map<String, List<JarClassDependency>> dependencyHierarchyMap;

  public MultiLevelDependencies(List<JarClassDependency> firstLevelDependencies, Map<String, List<JarClassDependency>> dependencyHierarchyMap)
  {
    this.firstLevelDependencies = firstLevelDependencies;
    this.dependencyHierarchyMap = dependencyHierarchyMap;
  }

  public List<JarClassDependency> getFirstLevelDependencies()
  {
    return firstLevelDependencies;
  }

  public Map<String, List<JarClassDependency>> getDependencyHierarchyMap()
  {
    return dependencyHierarchyMap;
  }
}
