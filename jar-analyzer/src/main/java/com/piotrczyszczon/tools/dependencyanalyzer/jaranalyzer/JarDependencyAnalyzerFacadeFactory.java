package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;

public class JarDependencyAnalyzerFacadeFactory
{
  public JarDependencyAnalyzerFacade create(Arguments arguments)
  {
    if (arguments.getMaximumLevel() > 1)
    {
      return new MultiLevelDependencyAnalyzerFacade();
    }
    else
    {
      return new SingleLevelJarDependencyAnalyzerFacade();
    }
  }
}
