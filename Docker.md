# Docker

### Build

``` sh
git clean -xfd
docker build -t messagehelper/id .
```

### Run

(Example)

#### Script

``` sh
docker run --detach --name id --publish 8004:8004 --restart always --volume ./mount/config/:/home/app/config/ messagehelper/id
```

#### Compose

``` yaml
version: '3.3'
services:
  id:
    container_name: id
    image: messagehelper/id
    ports:
      - '8004:8004'
    restart: always
    volumes:
      - './mount/config/:/home/app/config/'
```

``` sh
docker-compose up --detach
```

