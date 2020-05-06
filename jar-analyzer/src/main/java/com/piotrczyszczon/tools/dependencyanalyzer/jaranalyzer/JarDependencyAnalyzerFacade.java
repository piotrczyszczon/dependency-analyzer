package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;

import java.io.IOException;

public interface JarDependencyAnalyzerFacade
{
  void execute(Arguments arguments) throws IOException;
}
