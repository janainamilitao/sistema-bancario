[Compilando a imagem Docker da aplicação]: #compilando-a-imagem-docker-da-aplicação
[Iniciando o conteiner da aplicação]: #inciando-o-conteiner-da-aplicação


# Conteúdo
1. [Compilando a imagem Docker da aplicação][Compilando a imagem Docker da aplicação]
2. [Iniciando o conteiner da aplicação][Iniciando o conteiner da aplicação]

# Compilando a imagem Docker da aplicação

Considerando que você já tem o Docker-CE instalado, use o
comando a seguir para gerar uma imagem docker para a aplicação.

* Baixe o código do repositório Git com o comando a seguir.

```sh
git clone https://github.com/janainamilitao/sistema-bancario
cd sistema-bancario
./mvnw package
mv target/sistema-bancario-0.0.1-SNAPSHOT.jar sistema-bancario.jar
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

# Iniciando o conteiner da aplicação

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

Se quiser fazer backup do banco criado anteriormente, basta fazer backup do diretório ``/docker/postgresql/sistema-bancario/data``.
