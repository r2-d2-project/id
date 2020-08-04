# Docker

### Build

``` sh
git clean -xfd
docker build -t zzcgwu/id .
```

### Run

(Example)

#### Script

``` sh
docker run -d --restart on-failure --name id -v ./config/:/home/app/config/ -p 8004:8004 zzcgwu/id
```

#### Compose

``` yaml
version: '3.3'
services:
  id:
    restart: on-failure
    container_name: id
    volumes:
      - './config/:/home/app/config/'
    ports:
      - '8004:8004'
    image: zzcgwu/id
```

``` sh
docker-compose up -d
```

