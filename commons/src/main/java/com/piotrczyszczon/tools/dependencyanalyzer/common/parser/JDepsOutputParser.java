package com.piotrczyszczon.tools.dependencyanalyzer.common.parser;

import com.piotrczyszczon.tools.dependencyanalyzer.common.dependency.Dependency;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class JDepsOutputParser
{
  private JDepsOutputLineParser jDepsOutputLineParser = new JDepsOutputLineParser();

  public Set<Dependency> parse(BufferedReader commandOutputReader) throws IOException
  {
    Set<Dependency> dependencies = new HashSet<>();
    String jDepsOutputLine;
    while ((jDepsOutputLine = commandOutputReader.readLine()) != null)
    {
      Optional<Dependency> optionalDependency = jDepsOutputLineParser.parse(jDepsOutputLine);
      optionalDependency.ifPresent(dependencies::add);
    }

    return dependencies;
  }
}
