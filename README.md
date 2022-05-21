# Distributed systems lab

This is a backend for the Todo List application based on the Laravel framework.
For more information on Laravel see [https://laravel.com/docs/8.x/releases][https://laravel.com/docs/8.x/releases]

## The docker environment

Requirements:

-   Docker with docker-compose.

Copy the .env.example to a .env file and make the needed changes you want.

Make sure, that the files:

-   docker/php/entrypoint.sh
-   install.sh
    Have the Line Sequence as LN instead of CRLF

Then execute

```
docker-compose up -d
```

in the root directory. After successfully execution the API documentation is available at [http://localhost:8080/api/documentation](http://localhost:8080/api/documentation).

### Fresh install

Since docker does not wait for container setup, there needed to be a separate routine to setup the laravel environment.
So to completely reinstall the docker environment, you need to delete the following file:

-   .installed

You need to also remove the docker container and images.
To remove the container execute `docker-compose down`

## Lab tasks:

-   Todo List application backend
