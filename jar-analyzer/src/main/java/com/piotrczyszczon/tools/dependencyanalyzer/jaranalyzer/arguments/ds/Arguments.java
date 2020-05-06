package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds;

import java.util.List;

public class Arguments
{
  private List<String> classPath;
  private String sourcejar;
  private String sourceClassesRegex;
  private String dependencyJarRegex;
  private String dependencyClassesRegex;
  private String outputPath;
  private int maximumLevel = 1;

  public List<String> getClassPath()
  {
    return classPath;
  }

  public void setClassPath(List<String> classPath)
  {
    this.classPath = classPath;
  }

  public String getSourcejar()
  {
    return sourcejar;
  }

  public void setSourcejar(String sourcejar)
  {
    this.sourcejar = sourcejar;
  }

  public String getSourceClassesRegex()
  {
    return sourceClassesRegex;
  }

  public void setSourceClassesRegex(String sourceClassesRegex)
  {
    this.sourceClassesRegex = sourceClassesRegex;
  }

  public String getDependencyJarRegex()
  {
    return dependencyJarRegex;
  }

  public void setDependencyJarRegex(String dependencyJarRegex)
  {
    this.dependencyJarRegex = dependencyJarRegex;
  }

  public String getDependencyClassesRegex()
  {
    return dependencyClassesRegex;
  }

  public void setDependencyClassesRegex(String dependencyClassesRegex)
  {
    this.dependencyClassesRegex = dependencyClassesRegex;
  }

  public String getOutputPath()
  {
    return outputPath;
  }

  public void setOutputPath(String outputPath)
  {
    this.outputPath = outputPath;
  }

  public int getMaximumLevel()
  {
    return maximumLevel;
  }

  public void setMaximumLevel(int maximumLevel)
  {
    this.maximumLevel = maximumLevel;
  }
}
