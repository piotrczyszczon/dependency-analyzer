package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SerialJarClassesStream implements JarClassesStream
{
  private final int limit;

  public SerialJarClassesStream(int limit)
  {
    this.limit = limit;
  }

  @Override
  public Stream<String> openStream(Set<String> jarClasses)
  {
    ArrayList<String> jarClassesList = new ArrayList<>(jarClasses);

    return IntStream.range(0, limit).mapToObj(jarClassesList::get);
  }
}
