package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.jar.parser;

import java.util.Optional;

public class JarTfOutputLineParser
{
  public Optional<String> parse(String jarTfOutputLine)
  {
    if (isClassnameLine(jarTfOutputLine))
    {
      return Optional.of(parseClassName(jarTfOutputLine));
    }
    else
    {
      return Optional.empty();
    }
  }

  private boolean isClassnameLine(String jarTfOutputLine)
  {
    return !jarTfOutputLine.endsWith("/");
  }

  private String parseClassName(String jarTfOutputLine)
  {
    return jarTfOutputLine
        .replace(".class", "")
        .replace("/", ".");
  }
}
