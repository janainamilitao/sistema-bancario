# Instalando o Docker Compose

Considerando que você já tem o Docker instalado, execute os seguintes comandos para instalar o Docker Compose.

```sh
sudo su
COMPOSE_VERSION=1.24.0
curl -L https://github.com/docker/compose/releases/download/$COMPOSE_VERSION/docker-compose-`uname -s`-`uname -m` > /usr/bin/docker-compose
chmod +x /usr/bin/docker-compose
exit
```

## Inicie os conteiners

Use os comandos a seguir para iniciar os conteineres.

```sh
git clone https://github.com/janainamilitao/sistema-bancario
cd sistema-bancario
docker-compose -f docker-compose_sistema-bancario.yml create
docker-compose -f docker-compose_sistema-bancario.yml start
```

Acesse a API da aplicação através da URL http://172.17.0.1:8080/swagger-ui.html.


Para visualizar os conteineres em execução, digite o comando abaixo.

```sh
docker-compose -f docker-compose_sistema-bancario.yml ps
docker-compose -f docker-compose_sistema-bancario.yml top
```

Para visualizar os logs de todos os conteineres, use o comando abaixo.

```sh
docker-compose -f docker-compose_sistema-bancario.yml logs
```

Para visualizar os logs de um conteiner especifico, digite:

```sh
docker-compose -f docker-compose_sistema-bancario.yml logs NOME_SERVICO
```

O nome do serviço é declarado no arquivo ``docker-compose_sistema-bancario.yml``. Exemplo:

```sh
docker-compose -f docker-compose_sistema-bancario.yml logs sistema-bancario
```

Use os comandos a seguir para parar e remover os conteineres.

```sh
docker-compose -f docker-compose_sistema-bancario.yml stop
docker-compose -f docker-compose_sistema-bancario.yml rm
```

Para obter mais informações sobre o Docker Compose acesse:

* [https://docs.docker.com/compose/reference/](https://docs.docker.com/compose/reference/)
* [https://docs.docker.com/compose/compose-file/](https://docs.docker.com/compose/compose-file/)
