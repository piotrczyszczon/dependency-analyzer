package com.piotrczyszczon.tools.dependencyanalyzer.common.parser;

import com.piotrczyszczon.tools.dependencyanalyzer.common.dependency.Dependency;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JDepsOutputLineParser
{
  private Pattern DEPENDENCY_LINE_PATTERN = Pattern.compile("^\\s*->\\s*.*\\s*");
  private Pattern JAVA_CORE_PACKAGE_PATTERN = Pattern.compile("^\\s*-> java.*");
  private Pattern DEPENDENCY_PATTERN = Pattern.compile("^\\s*->\\s*([\\S]*)\\s*([^\\n\\r]*).*");

  public Optional<Dependency> parse(String jDepsOutputLine)
  {
    if (isDependencyLine(jDepsOutputLine) && isNotJavaCore(jDepsOutputLine))
    {
      return parseDependencyLine(jDepsOutputLine);
    }
    else
    {
      return Optional.empty();
    }
  }

  private Optional<Dependency> parseDependencyLine(String jDepsOutputLine)
  {
    Matcher dependencyMatcher = DEPENDENCY_PATTERN.matcher(jDepsOutputLine);
    if (dependencyMatcher.matches())
    {
      String dependency = dependencyMatcher.group(1);
      String module = dependencyMatcher.group(2);
      return Optional.of(new Dependency(dependency, module));
    }
    else
    {
      throw new RuntimeException("Failed to parse JDeps line Output: " + jDepsOutputLine);
    }
  }

  private boolean isDependencyLine(String jDepsOutputLine)
  {
    return DEPENDENCY_LINE_PATTERN.matcher(jDepsOutputLine).matches();
  }

  private boolean isNotJavaCore(String jDepsOutputLine)
  {
    return !JAVA_CORE_PACKAGE_PATTERN.matcher(jDepsOutputLine).matches();
  }
}
