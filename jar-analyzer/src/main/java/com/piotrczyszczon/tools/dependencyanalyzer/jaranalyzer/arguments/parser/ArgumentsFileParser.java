package com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.parser;

import com.piotrczyszczon.tools.dependencyanalyzer.jaranalyzer.arguments.ds.Arguments;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArgumentsFileParser
{
  public Arguments parse(String configFilePath) throws IOException
  {
    return parseYaml(configFilePath);
  }

  private Arguments parseYaml(String configFilePath) throws IOException
  {
    return new Yaml(new Constructor(Arguments.class)).load(openFile(configFilePath));
  }

  private InputStream openFile(String configFilePath) throws IOException
  {
    return Files.newInputStream(Paths.get(configFilePath));
  }
}
