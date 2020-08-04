# id

### Introduction

It is a distributed ID generator powered by the algorithm of [snowflake](https://github.com/twitter-archive/snowflake), which can:

- generate 53-bit integer (JavaScript comparable)  as unique ID ascending by time
- support at most 4 distributed nodes

### Develop

Execute following commands.

``` bash
git config --local core.autocrlf input
git config --local core.safecrlf true
```

Add following lines to tag `<project>...</project>` in file `.idea/workspace.xml`.

``` xml
  <component name="RunManager">
    <configuration name="Application" type="SpringBootApplicationConfigurationType" factoryName="Spring Boot">
      <module name="id" />
      <option name="SPRING_BOOT_MAIN_CLASS" value="io.github.messagehelper.id.Application" />
      <option name="PROGRAM_PARAMETERS" value="--server.port=8004" />
      <option name="ALTERNATIVE_JRE_PATH" />
      <method v="2">
        <option name="Make" enabled="true" />
      </method>
    </configuration>
  </component>
```

### Reference

- https://github.com/twitter-archive/snowflake
- https://developer.twitter.com/en/docs/basics/twitter-ids
- https://juejin.im/post/6844903562007314440
- https://segmentfault.com/a/1190000011282426

### Others

- All code files are edited by [IntelliJ IDEA](https://www.jetbrains.com/idea/).
- All ".md" files are edited by [Typora](http://typora.io/).
- The style of all ".md" files is [Github Flavored Markdown](https://guides.github.com/features/mastering-markdown/#GitHub-flavored-markdown).
- There is a LF (Linux) at the end of each line.