package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;
import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.parser.ArgumentsFileParser;

import java.io.IOException;

public class Runner
{
  private JarDependencyAnalyzerFacadeFactory jarDependencyAnalyzerFacadeFactory = new JarDependencyAnalyzerFacadeFactory();

  public static void main(String[] args) throws IOException
  {
    if (args.length == 1)
    {
      new Runner().run(args[0]);
    }
    else
    {
      printUsage();
    }
  }

  private void run(String configurationFile) throws IOException
  {
    Arguments arguments = readArguments(configurationFile);

    jarDependencyAnalyzerFacadeFactory.create(arguments).execute(arguments);
  }

  private Arguments readArguments(String configurationFile) throws IOException
  {
    return new ArgumentsFileParser().parse(configurationFile);
  }

  private static void printUsage()
  {
    System.out.println("Usage: dependency-analyzer <configuration_file>");
  }
}
