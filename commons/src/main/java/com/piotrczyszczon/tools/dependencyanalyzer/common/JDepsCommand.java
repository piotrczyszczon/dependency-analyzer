package com.piotrczyszczon.tools.dependencyanalyzer.common;

import com.piotrczyszczon.tools.dependencyanalyzer.common.classpath.Classpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JDepsCommand
{
  public BufferedReader execute(String pathToClass, Classpath classpath) throws IOException
  {
    Runtime rt = Runtime.getRuntime();

    String[] command = {"jdeps.exe", "-verbose:class", "-filter:none", "-cp", classpath.getClassPathString(), pathToClass};

    Process process = rt.exec(command);

    BufferedReader commandOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    return commandOutputReader;
  }
}
