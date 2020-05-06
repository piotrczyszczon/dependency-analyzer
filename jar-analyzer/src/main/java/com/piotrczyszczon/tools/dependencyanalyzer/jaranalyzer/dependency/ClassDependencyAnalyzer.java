package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency;

import com.piotrczyszczon.tools.dependencyanalyzer.common.DependencyAnalyzer;
import com.piotrczyszczon.tools.dependencyanalyzer.common.classpath.Classpath;
import com.piotrczyszczon.tools.dependencyanalyzer.common.dependency.Dependency;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassDependencyAnalyzer
{
  private DependencyAnalyzer dependencyAnalyzer;
  private final String dependencyJarRegex;
  private final String dependencyClassesRegex;

  public ClassDependencyAnalyzer(DependencyAnalyzer dependencyAnalyzer, String dependencyJarRegex, String dependencyClassesRegex)
  {
    this.dependencyAnalyzer = dependencyAnalyzer;
    this.dependencyJarRegex = dependencyJarRegex;
    this.dependencyClassesRegex = dependencyClassesRegex;
  }

  List<JarClassDependency> getClassDependencies(String className)
  {
    try
    {
      Set<Dependency> classDependencies = analyzeClassDependencies(className);
      return filterTargetModule(classDependencies).map(jarClassDependency(className)).collect(Collectors.toList());
    } catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private Set<Dependency> analyzeClassDependencies(String className) throws IOException
  {
    return dependencyAnalyzer.analyzeDependencies(className);
  }

  private Stream<Dependency> filterTargetModule(Set<Dependency> classDependencies)
  {
    return classDependencies.stream().filter(filterConfiguredDependencyJar(dependencyJarRegex)).filter(filterConfiguredDependencyClasses(dependencyClassesRegex));
  }

  private Predicate<? super Dependency> filterConfiguredDependencyJar(String dependencyJarRegex)
  {
    return dependency -> Pattern.compile(dependencyJarRegex).matcher(dependency.getModule()).matches();
  }

  private Predicate<? super Dependency> filterConfiguredDependencyClasses(String dependencyClassesRegex)
  {
    return dependency -> Pattern.compile(dependencyClassesRegex).matcher(dependency.getPath()).matches();
  }

  private Function<Dependency, JarClassDependency> jarClassDependency(String className)
  {
    return dependency -> new JarClassDependency(className, dependency);
  }

  public static ClassDependencyAnalyzer createClassDependencyAnalyzer(Arguments arguments)
  {
    DependencyAnalyzer dependencyAnalyzer = new DependencyAnalyzer(Classpath.of(arguments.getClassPath()));

    return new ClassDependencyAnalyzer(dependencyAnalyzer, arguments.getDependencyJarRegex(), arguments.getDependencyClassesRegex());
  }
}
