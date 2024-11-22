# UserController.java

## POST /user

Endpoint que cria um novo usuário no banco.

### Parâmetros:

`Body : UserData`

Json com dados do usuário a ser criado.

### Retorno:

String com a mensagem de sucesso ou erro, Status `OK` ou `Bad Request`.

## GET /user

Endpoint que busca os dados dos usuários.

### Parâmetros:

`Query:`
- `name : String` - O nome do usuário sendo buscado
- `page : Integer` - Página da busca, 0 se nulo
- `size : Integer` - Tamanho da página, 2147483647 se nulo

### Retorno:

Uma lista com os usuários encontrados. Se nenhum for encontrado, a lista é retornada vazia.

---

# AuthController.java

## POST /auth

Endpoint para login do usuário.

### Parâmetros:

`Body : UserLoginData`

Json com edv e senha do usuário

### Retorno:

String com o Jwt de autenticação em caso de sucesso.

Caso falhe, retorna a mensagem de erro.