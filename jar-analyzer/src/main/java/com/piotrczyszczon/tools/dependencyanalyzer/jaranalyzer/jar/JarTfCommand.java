package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.jar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JarTfCommand
{
  public BufferedReader execute(String pathToJar) throws IOException
  {
    Runtime rt = Runtime.getRuntime();

    String[] command = {"jar.exe", "tf", pathToJar};

    Process proc = rt.exec(command);

    return new BufferedReader(new InputStreamReader(proc.getInputStream()));
  }
}
