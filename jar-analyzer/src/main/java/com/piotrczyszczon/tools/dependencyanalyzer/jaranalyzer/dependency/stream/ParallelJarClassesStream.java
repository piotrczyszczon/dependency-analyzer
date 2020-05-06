package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream;

import java.util.Set;
import java.util.stream.Stream;

public class ParallelJarClassesStream implements JarClassesStream
{
  @Override
  public Stream<String> openStream(Set<String> jarClasses)
  {
    return jarClasses.stream().parallel();
  }
}
