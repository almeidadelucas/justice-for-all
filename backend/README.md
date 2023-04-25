# Instruções para utilizar o Backend

* (control + p) e rodar no vsCode comando a seguir:

    ```
    ext install vmware.vscode-boot-dev-pack
    ```

* Adicionar a extensão LOMBOK no seu vsCode

* Abrir a pasta backend

    ```
    cd backend
    ```

* para rodar o backend:

    - abrir a aba JAVA PROJECTS
    - clilcar com o botão direito em "justiceforall" e
    selecionar "run"

## Rodando o serviço Backend com Docker

1. Iniciar as dependências do serviço Spring Boot (ex: banco de dados Postgres):
   
    ```
    docker compose -f justiceforall/docker/compose.yml -p justice-for-all-compose up -d
    ```
2. Construir a imagem da aplicação Spring Boot (pode demorar um pouco):

    ```
    docker build -t justice-for-all-spring-app ./justiceforall
    ```
3. Iniciar o Container do serviço Backend:

    ```
    docker run --network=host -p 8080:8080 -t justice-for-all-spring-app
    ```
4. Para verificar se o serviço está rodando corretamente, basta chamar o endpoint ```GET http://localhost:8080/actuator/health``` e verificar se a mensagem abaixo é exibida:

    ```
    {"status":"UP"}
    ```

## Usuário inicial 
Para facilitar os testes consumindo o Backend, um usuário com perfil "ADMIN" já é automaticamente criado assim que a aplicação se inicia. Os dados do usuário estão abaixo:

- ID: 1
- Email: admin@email.com
- Senha: password
