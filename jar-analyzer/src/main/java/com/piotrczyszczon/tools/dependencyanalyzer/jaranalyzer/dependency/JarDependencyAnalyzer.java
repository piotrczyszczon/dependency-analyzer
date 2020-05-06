package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream.JarClassesStream;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream.ParallelJarClassesStream;

import java.util.List;
import java.util.Set;

public class JarDependencyAnalyzer
{
  private JarClassesStream jarClassesStream = new ParallelJarClassesStream();
  private final ClassDependencyAnalyzer classDependencyAnalyzer;

  public JarDependencyAnalyzer(ClassDependencyAnalyzer classDependencyAnalyzer)
  {
    this.classDependencyAnalyzer = classDependencyAnalyzer;
  }

  public List<JarClassDependency> execute(String analyzedJar, String sourceClassesRegex)
  {
    Set<String> jarClassesList = open
  }
}
