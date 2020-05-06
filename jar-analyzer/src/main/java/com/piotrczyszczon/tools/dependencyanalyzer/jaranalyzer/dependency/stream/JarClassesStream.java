package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.stream;

import java.util.Set;
import java.util.stream.Stream;

public interface JarClassesStream
{
  Stream<String> openStream(Set<String> jarClasses);
}
