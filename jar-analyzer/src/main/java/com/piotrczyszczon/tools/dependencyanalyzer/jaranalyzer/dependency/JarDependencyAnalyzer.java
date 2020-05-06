package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream.JarClassesStream;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream.ParallelJarClassesStream;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.jar.JarFile.openJarFile;

public class JarDependencyAnalyzer
{
  private JarClassesStream jarClassesStream = new ParallelJarClassesStream();
  private final ClassDependencyAnalyzer classDependencyAnalyzer;

  public JarDependencyAnalyzer(ClassDependencyAnalyzer classDependencyAnalyzer)
  {
    this.classDependencyAnalyzer = classDependencyAnalyzer;
  }

  public List<JarClassDependency> execute(String analyzedJar, String sourceClassesRegex) throws IOException
  {
    Set<String> jarClassesList = openJarFile(analyzedJar).listJarClasses();

    Set<String> filteredJarClassesList = filterJarClassesList(jarClassesList, sourceClassesRegex);

    return getAllClassesDependencies(filteredJarClassesList);
  }

  private Set<String> filterJarClassesList(Set<String> jarClassesList, String sourceClassesRegex)
  {
    return jarClassesList.stream()
        .filter(jarClassName -> Pattern.compile(sourceClassesRegex).matcher(jarClassName).matches())
        .collect(Collectors.toSet());
  }

  private List<JarClassDependency> getAllClassesDependencies(Set<String> jarClasses)
  {
    return jarClassesStream.openStream(jarClasses)
        .map(classDependencyAnalyzer::getClassDependencies)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  public static JarDependencyAnalyzer createJarDependencyAnalyzer(Arguments arguments)
  {
    return new JarDependencyAnalyzer(ClassDependencyAnalyzer.createClassDependencyAnalyzer(arguments));
  }
}
