package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.jar.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class JarTfOutputParser
{
  JarTfOutputLineParser jarTfOutputLineParser = new JarTfOutputLineParser();

  public Set<String> parse(BufferedReader commandOutputReader) throws IOException
  {
    Set<String> classNames = new HashSet<>();

    String jarTfOutputLine;
    while ((jarTfOutputLine = commandOutputReader.readLine()) != null)
    {
      Optional<String> dependency = jarTfOutputLineParser.parse(jarTfOutputLine);
      dependency.ifPresent(classNames::add);
    }
    return classNames;
  }
}
