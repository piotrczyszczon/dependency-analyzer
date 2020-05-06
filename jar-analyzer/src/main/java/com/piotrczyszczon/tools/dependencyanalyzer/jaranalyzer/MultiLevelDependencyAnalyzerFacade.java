package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds.MultiLevelDependencies;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.writer.MultiLevelDependenciesPrinter;

import java.io.IOException;

import static com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.MultiLevelDependencyAnalyzer.createMultiLevelDependencyAnalyzer;

public class MultiLevelDependencyAnalyzerFacade implements JarDependencyAnalyzerFacade
{
  private MultiLevelDependenciesPrinter multiLevelDependenciesPrinter = new MultiLevelDependenciesPrinter();

  @Override
  public void execute(Arguments arguments) throws IOException
  {
    MultiLevelDependencies multiLevelDependencies = executeDependencyAnalyzer(arguments);

    multiLevelDependenciesPrinter.print(multiLevelDependencies, arguments.getOutputPath());
  }

  private MultiLevelDependencies executeDependencyAnalyzer(Arguments arguments) throws IOException
  {
    return createMultiLevelDependencyAnalyzer(arguments).execute(arguments.getSourceJar(), arguments.getSourceClassesRegex());
  }
}
