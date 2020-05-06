package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.dependency.ds;

import com.piotrczyszczon.tools.dependencyanalyzer.common.dependency.Dependency;

import java.util.Objects;

public class JarClassDependency
{
  private String classname;
  private Dependency dependency;

  public JarClassDependency(String classname, Dependency dependendency)
  {
    this.classname = classname;
    this.dependency = dependendency;
  }

  public String getClassname()
  {
    return classname;
  }

  public Dependency getDependency()
  {
    return dependency;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    JarClassDependency that = (JarClassDependency) o;
    return Objects.equals(classname, that.classname) && Objects.equals(dependency, that.dependency);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(classname, dependency);
  }
}
