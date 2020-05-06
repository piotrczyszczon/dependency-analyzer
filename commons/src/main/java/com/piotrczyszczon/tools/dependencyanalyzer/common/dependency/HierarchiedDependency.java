package com.piotrczyszczon.tools.dependencyanalyzer.common.dependency;

import java.util.Objects;

public class HierarchiedDependency
{
  private String parent;
  private Dependency dependency;

  public HierarchiedDependency(String parent, Dependency dependency)
  {
    this.parent = parent;
    this.dependency = dependency;
  }

  public HierarchiedDependency(Dependency dependency)
  {
    this.parent = "<NO_PARENT>";
    this.dependency = dependency;
  }

  public String getParent()
  {
    return parent;
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
    HierarchiedDependency that = (HierarchiedDependency) o;
    return Objects.equals(parent, that.parent) && Objects.equals(dependency, that.dependency);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(parent, dependency);
  }
}
