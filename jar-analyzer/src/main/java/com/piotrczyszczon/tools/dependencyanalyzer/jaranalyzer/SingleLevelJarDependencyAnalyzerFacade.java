package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.JarClassDependency;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.writer.JarDependenciesPrinter;

import java.io.IOException;
import java.util.List;

import static com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.JarDependencyAnalyzer.createJarDependencyAnalyzer;

public class SingleLevelJarDependencyAnalyzerFacade implements JarDependencyAnalyzerFacade
{
  private JarDependenciesPrinter jarDependenciesPrinter = new JarDependenciesPrinter();

  @Override
  public void execute(Arguments arguments) throws IOException
  {
    List<JarClassDependency> jarDependencies = executeDependencyAnalyzer(arguments);

    jarDependenciesPrinter.print(jarDependencies, arguments.getOutputPath());
  }

  private List<JarClassDependency> executeDependencyAnalyzer(Arguments arguments) throws IOException
  {
    return createJarDependencyAnalyzer(arguments).execute(arguments.getSourcejar(), arguments.getSourceClassesRegex());
  }
}
