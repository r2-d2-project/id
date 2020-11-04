# ID Generator

### Introduction

It is a distributed ID generator powered by the algorithm of [snowflake](https://github.com/twitter-archive/snowflake), which can:

- generate 53-bit integer (JavaScript comparable)  as unique ID ascending by time
- support at most 16 distributed nodes (4 data centers, each contains 4 machines)
- at most generate 256 IDs per milliseconds from each node

### Instance and Usage

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

https://id.zzc.icu/time

```
HTTP/1.1 200 
Content-Type: application/json;charset=utf-8
Cache-Control: no-store, no-cache, must-revalidate, proxy-revalidate
Pragma: no-cache
Expires: no-store
Surrogate-Control: no-store

{
  "id": 27887635202048,
  "timestamp": 1603048504688,
  "rfc3339": "2020-10-18T19:15:04.688Z"
}
```

https://id.zzc.icu/time/27887399788544

```
HTTP/1.1 200 
Content-Type: application/json;charset=utf-8
Cache-Control: no-store, no-cache, must-revalidate, proxy-revalidate
Pragma: no-cache
Expires: no-store
Surrogate-Control: no-store

{
  "id": 27887399788544,
  "timestamp": 1603048447214,
  "rfc3339": "2020-10-18T19:14:07.214Z"
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
Content-Type: text/plain;charset=UTF-8
Cache-Control: no-store, no-cache, must-revalidate, proxy-revalidate
Pragma: no-cache
Expires: no-store
Surrogate-Control: no-store

27161426952192
```

### Deploy and Run

#### Java

Execute following commands to compile and run.

``` sh
git clean -xfd

./mvnw clean && ./mvnw package # macOS and Linux only
.\mvnw.cmd clean && .\mvnw.cmd package # Windows only

mkdir ./config/

java -jar ./target/id.jar

# optional configuration => ./config/application.properties
```

#### Docker

Execute following commands to pull image and run.

``` sh
docker pull r2d2project/id

docker run --detach --name id --publish 8004:8004 --restart always --volume ./mount/config/:/home/app/config/ r2d2project/id

# optional configuration => ./mount/config/application.properties
```

See [Docker.md](./Docker.md) for more details.

### Custom Configuration

The content of the default configuration is like:

``` properties
server.port=8004
snowflake.twepoch=1596240000000
snowflake.datacenterId=0
snowflake.workerId=0
```

See [additional-spring-configuration-metadata.json](../src/main/resources/META-INF/additional-spring-configuration-metadata.json) for more details.

Place custom configuration file at path `work-directory>/config/application.properties` to override the default one.

### Develop

Execute following commands before making any change.

``` sh
git config --local core.autocrlf false
git config --local core.safecrlf true
git config --local core.eol lf
```

### Reference

- https://github.com/twitter-archive/snowflake
- https://developer.twitter.com/en/docs/basics/twitter-ids
- https://www.cnblogs.com/relucent/p/4955340.html
- https://juejin.im/post/6844903562007314440
- https://segmentfault.com/a/1190000011282426
