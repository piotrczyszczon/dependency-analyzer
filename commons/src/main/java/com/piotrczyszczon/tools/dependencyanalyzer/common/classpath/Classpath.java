package com.piotrczyszczon.tools.dependencyanalyzer.common.classpath;

import java.util.Arrays;
import java.util.List;

public class Classpath
{
  private static final String CLASSPATH_SEPARATOR = ";";

  public List<String> entries;

  public Classpath(List<String> entries)
  {
    this.entries = entries;
  }

  public String getClassPathString()
  {
    return String.join(CLASSPATH_SEPARATOR, entries);
  }

  public static Classpath of(String... entries)
  {
    return new Classpath(Arrays.asList(entries));
  }

  public static Classpath of(List<String> entries)
  {
    return new Classpath(entries);
  }
}
