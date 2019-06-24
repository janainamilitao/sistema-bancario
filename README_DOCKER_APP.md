
## Compilando a imagem Docker da aplicação

Considerando que você já tem o Docker-CE instalado, use o
comando a seguir para gerar uma imagem docker para a aplicação.

* Baixe o código do repositório Git com o comando a seguir.

```sh
git clone https://github.com/janainamilitao/sistema-bancario
cd sistema-bancario
VERSION=1.0.0
docker build -t jmilitao/sistema-bancario:$VERSION .
docker tag jmilitao/sistema-bancario:$VERSION jmilitao/sistema-bancario:latest
```

* Envie a imagem para Docker Hub com os comandos abaixo.

```sh
docker login -u USERNAME -p PASS
docker push jmilitao/sistema-bancario:$VERSION
docker push jmilitao/sistema-bancario:latest
```

## Iniciando o conteiner da aplicação

Use o comando a seguir para iniciar um conteiner do banco de dados.

```sh
sudo mkdir -p /docker/postgresql/sistema-bancario/data
sudo chown -R 999:999 /docker/postgresql/sistema-bancario/data

docker run -p 5433:5432 -d --name postgres \
--restart=always \
-v /docker/postgresql/sistema-bancario/data:/var/lib/postgresql/data \
-e POSTGRES_DB="sistema-bancario" \
-e POSTGRES_PASSWORD="postgres" \
-e POSTGRES_USER="postgres" \
postgres
```

Use o comando a seguir para iniciar um conteiner da aplicação.

```sh
docker run -d -p 8080:8080 --name sistema-bancario \
-e DATASOURCE_URL="postgresql://172.17.0.1:5433/sistema-bancario" \
-e DATASOURCE_USERNAME="postgres" \
-e DATASOURCE_PASSWORD="postgres" \
jmilitao/sistema-bancario:latest
```

Acesse a API da aplicação na URL http://172.17.0.1:8080/swagger-ui.html

Use os comandos a seguir visualizar o log dos conteineres.

```sh
docker logs -f postgres
docker logs -f sistema-bancario
```

## Referências do Docker

https://docs.docker.com/engine/tutorials/dockerimages/

https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-16-04

https://docs.docker.com/engine/reference/commandline/commit/

https://www.digitalocean.com/community/tutorials/docker-explained-how-to-create-docker-containers-running-memcached
