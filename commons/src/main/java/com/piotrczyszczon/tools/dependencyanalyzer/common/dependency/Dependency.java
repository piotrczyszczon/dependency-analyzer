package com.piotrczyszczon.tools.dependencyanalyzer.common.dependency;

import java.util.Objects;

public class Dependency
{
  private String path;
  private String module;

  public Dependency(String path, String module)
  {
    this.path = path;
    this.module = module;
  }

  public String getPath()
  {
    return path;
  }

  public String getModule()
  {
    return module;
  }

  @Override
  public String toString()
  {
    return String.format("Path [%s] Module [%s]", path, module);
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
    Dependency that = (Dependency) o;
    return Objects.equals(path, that.path) && Objects.equals(module, that.module);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(path, module);
  }
}
