# id

### Introduction

It is a distributed ID generator powered by the algorithm of [snowflake](https://github.com/twitter-archive/snowflake), which can:

- generate 53-bit integer (JavaScript comparable)  as unique ID ascending by time
- support at most 16 distributed nodes (4 data centers, each contains 4 machines)
- at most generate 256 IDs per milliseconds from each node

### Demo

https://id.zzc.icu/

```
HTTP/1.1 200 
Content-Type: application/json;charset=utf-8
Cache-Control: no-store, no-cache, must-revalidate, proxy-revalidate
Pragma: no-cache
Expires: no-store
Surrogate-Control: no-store

{
  "id": 27159763599360
}
```

https://id.zzc.icu/type/string

```
HTTP/1.1 200 
Content-Type: application/json;charset=utf-8
Cache-Control: no-store, no-cache, must-revalidate, proxy-revalidate
Pragma: no-cache
Expires: no-store
Surrogate-Control: no-store

{
  "id": "27161314156544"
}
```

https://id.zzc.icu/type/plain

```
HTTP/1.1 200 
Content-Type: application/json;charset=utf-8
Cache-Control: no-store, no-cache, must-revalidate, proxy-revalidate
Pragma: no-cache
Expires: no-store
Surrogate-Control: no-store

27161426952192
```

### Run

Execute following commands to compile and run.

``` sh
git clean -xfd

./mvnw clean && ./mvnw package # macOS and Linux only
.\mvnw.cmd clean && .\mvnw.cmd package # Windows only

java -jar ./target/id.jar
```

### Config

The default config file `application.properties` is like:

```
server.port=8004
snowflake.twepoch=1596240000000
snowflake.datacenterId=0
snowflake.workerId=0
```

See [additional-spring-configuration-metadata.json](./src/main/resources/META-INF/additional-spring-configuration-metadata.json) for more details.

Place user-defined config file at path `<jar-location>/config/application.properties` to override the default one.

### Docker

Execute following commands to pull and run:

``` sh
docker pull messagehelper/id

docker run --detach --name id --publish 8004:8004 --restart always --volume ./mount/config/:/home/app/config/ messagehelper/id
```

Path `/home/app/` in docker container acts as `<jar-location>`.

See [Docker.md](./Docker.md) for more details.

### Develop

Execute following commands before making any change.

``` sh
git config --local core.autocrlf input
git config --local core.safecrlf true
```

### Reference

- https://github.com/twitter-archive/snowflake
- https://developer.twitter.com/en/docs/basics/twitter-ids
- https://www.cnblogs.com/relucent/p/4955340.html
- https://juejin.im/post/6844903562007314440
- https://segmentfault.com/a/1190000011282426

### Others

- All code files are edited by [IntelliJ IDEA](https://www.jetbrains.com/idea/).
- All ".md" files are edited by [Typora](http://typora.io/).
- The style of all ".md" files is [Github Flavored Markdown](https://guides.github.com/features/mastering-markdown/#GitHub-flavored-markdown).
- There is a LF (Linux) at the end of each line.