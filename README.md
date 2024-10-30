# CRUD Products
Projeto de CRUD para gerenciamento de produtos, desenvolvido como parte da atividade avaliativa de Desenvolvimento Web II. Este projeto utiliza Java, Spring Boot, Docker e MySQL.

## Pré-requisitos
- Java 21
- Maven 3.8+
- Docker

## Dependências
As dependências principais incluem:
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- MySQL Connector
- Lombok (opcional)

## Configuração e Execução
1. Clone o repositório
```bash
git clone https://github.com/matheusvidal21/crud-products
cd crud-products
```

2. Construção do projeto com Maven
```
mvn clean install
```

3. Iniciando o MySQL com Docker Compose
```
docker-compose up -d
```

4. Executando a Aplicação
```
mvn spring-boot:run
```

6. Testando a API
- Utilize o arquivo `requests.http` para fazer requisições e testar a API. 
- Este arquivo contém endpoints para listar, criar, atualizar e deletar clientes e produtos.

### Comandos para acessar o MySQL no container
Para verificar as tabelas e fazer consultas no MySQL:

1. Acesse o container MySQL
```
docker exec -it mysql-container mysql -u root -p
```
- Quando solicitado, insira a senha (root).

2. Selecionar o banco de dados
```
USE db-products;
```

3. Listar tabelas
```
SHOW TABLES;
```

4. Exemplo de consulta na tabela de produtos
```
SELECT * FROM tb_products;
```

5. Sair do MySQL
```
EXIT;
```

## Autor
- Matheus Vidal (matheusvidal140@gmail.com)
