# Tiny Url

## Table of Contents

1. [General Info](#general-info)
2. [Requirements](#requirements)
3. [Deployment](#deploy)
4. [Examples](#examples)

## General Info

---

- Spring Boot + Kotlin + Gradle 
- Redis as database

## Requirements

---

- **JVM** 21
- **Gradle** 8.7 or higher
- **Docker** 18.06 or higher
- **Docker-compose** 1.25.0 or higher
- **Redis**

### Build

- `gradle clean build`

## Deploy

### Just run scripts

- run `./1build_and_cp.exe`
- run `./2run.exe`

### Launch via IntelliJ

- Make sure you have a redis up or `docker run --rm -it -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest`
- Launch via IntelliJ

### Launch via docker-compose

- Goto root dir then `cp build/libs/tinyurl.jar Docker/tinyurl.jar`
- then `docker-compose up --build`

## Examples

1. create a tiny url, POST localhost:10000/api/v1/tiny-url
```json
{
  "originalUrl" : "https://www.magiciendecode.fr"
}
```
example of response
```json
{
    "shortUrl": "https://my-tiny.test/aHR0cHM",
    "originUrl": "https://www.magiciendecode.fr",
    "expiredIn": 2592000
}
```
2. get a tiny url by hash, GET localhost:10000/api/v1/tiny-url/aHR0cHM
example of response
```json
{
    "shortUrl": "https://my-tiny.test/aHR0cHM",
    "originUrl": "https://www.magiciendecode.fr",
    "expiredIn": 2592000
}
```