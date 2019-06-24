# Sobre a Aplicação

O objetivo desta aplicação é simular operações bancárias.

## Operações do sistema:

A seguir estão os endpoints disponíveis na API Swagger da aplicação.

* Cadastrar Pessoas

![alt text](images/01_cadastrar_pessoa.png "Cadastro de Pessoa")

* Listar Pessoas

![alt text](images/02_listar_pessoas.png "Listagem de Pessoas")

* Criar Conta

![alt text](images/03_criar_conta.png "Criando uma conta")

* Bloquear Conta

![alt text](images/04_bloquear_conta.png "Bloqueando uma conta")

* Desbloquear Conta

![alt text](images/05_desbloquear_conta.png "Desbloqueando uma conta")

* Listar Contas 

![alt text](images/06_listar_contas.png "Listagem de contas")

* Consultar Saldo

![alt text](images/07_consultar_saldo.png "Consultando saldo")

* Depósito

![alt text](images/08_deposito.png "Depósito")

* Saque

![alt text](images/09_saque.png "Saque")

* Extrato de Conta

![alt text](images/10_extrato.png "Extrato")

* Extrato de Conta por Período

![alt text](images/11_extrato_periodo.png "Extrato por Período")

## Instalando o Docker-CE

* Instale o Docker-CE seguindo as instruções das páginas abaixo de acordo com a
distribuição GNU/Linux.

No Ubuntu: https://docs.docker.com/install/linux/docker-ce/ubuntu

No CentOS: https://docs.docker.com/install/linux/docker-ce/centos

No Debian: https://docs.docker.com/install/linux/docker-ce/debian

* Altere o valor da variárel ``USUARIO`` pelo nome da sua conta no S.O no qual
o Docker foi instalado e execute os comandos abaixo para adicionar o seu usuário
 ao grupo Docker.

```bash
USUARIO="seu-nome-usuario"
sudo usermod -aG docker $USUARIO
sudo setfacl -m user:$USUARIO:rw /var/run/docker.sock
```

## Executando a aplicação usando o Docker

Use o comando a seguir para iniciar um conteiner da aplicação e removê-lo automaticamente após a execução dos testes.

```sh
docker run --rm -i -t --name challenge jmilitao/challenge:latest /bin/bash
```