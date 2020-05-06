package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.jar;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.jar.parser.JarTfOutputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class JarFile
{
  private final String pathToJar;

  private JarTfCommand jarTfCommand = new JarTfCommand();
  private JarTfOutputParser jarTfOutputParser = new JarTfOutputParser();

  private JarFile(String pathToJar)
  {
    this.pathToJar = pathToJar;
  }

  public Set<String> listJarClasses() throws IOException
  {
    BufferedReader jarTfOutputReader = jarTfCommand.execute(pathToJar);

    return jarTfOutputParser.parse(jarTfOutputReader);
  }

  public static JarFile openJarFile(String pathToJar)
  {
    return new JarFile(pathToJar);
  }
}
