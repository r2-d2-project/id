# ID 生成器

### 简介

基于 [snowflake](https://github.com/twitter-archive/snowflake) 算法的分布式 ID 生成器，具有下列功能：

- 产生时间升序独一无二的 53 位整数 ID（兼容 JavaScript） 
- 支持最多 16 个分布式节点（4个数据中心，每个数据中心包括4台主机）
- 每个节点每毫秒最多生成 256 个 ID

### 实例和用法

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

### 部署和运行

#### Java

执行下列命令编译并运行。

``` sh
git clean -xfd

./mvnw clean && ./mvnw package # macOS and Linux only
.\mvnw.cmd clean && .\mvnw.cmd package # Windows only

mkdir ./config/

java -jar ./target/id.jar

# optional configuration => ./config/application.properties
```

#### Docker

执行下列命令拉取镜像并运行。

``` sh
docker pull r2d2project/id

docker run --detach --name id --publish 8004:8004 --restart always --volume ./mount/config/:/home/app/config/ r2d2project/id

# optional configuration => ./mount/config/application.properties
```

更多详情参见 [Docker.md](./Docker.md) 。

### 自定义配置

默认配置的内容如下：

``` properties
server.port=8004
snowflake.twepoch=1596240000000
snowflake.datacenterId=0
snowflake.workerId=0
```

更多详情参见 [additional-spring-configuration-metadata.json](../src/main/resources/META-INF/additional-spring-configuration-metadata.json) 。

将自定义配置文件置于路径 `<work-directory>/config/application.properties` 以覆盖默认配置。

### 开发

在做出任何变更之前，执行下列命令。

``` sh
git config --local core.autocrlf false
git config --local core.safecrlf true
git config --local core.eol lf
```

### 参考资料

- https://github.com/twitter-archive/snowflake
- https://developer.twitter.com/en/docs/basics/twitter-ids
- https://www.cnblogs.com/relucent/p/4955340.html
- https://juejin.im/post/6844903562007314440
- https://segmentfault.com/a/1190000011282426
