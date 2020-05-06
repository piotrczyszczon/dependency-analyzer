package com.piotrczyszczon.tools.dependencyanalyzer.common;

import com.piotrczyszczon.tools.dependencyanalyzer.common.classpath.Classpath;
import com.piotrczyszczon.tools.dependencyanalyzer.common.dependency.Dependency;
import com.piotrczyszczon.tools.dependencyanalyzer.common.parser.JDepsOutputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class DependencyAnalyzer
{
  private final Classpath classpath;

  private JDepsCommand jDepsCommand = new JDepsCommand();
  private JDepsOutputParser jDepsOutputParser = new JDepsOutputParser();

  public DependencyAnalyzer(Classpath classpath)
  {
    this.classpath = classpath;
  }

  public Set<Dependency> analyzeDependencies(String pathToClass) throws IOException
  {
    BufferedReader commandOutputReader = jDepsCommand.execute(pathToClass, classpath);

    return jDepsOutputParser.parse(commandOutputReader);
  }
}
