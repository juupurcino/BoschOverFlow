# BoschOverflow
Trabalho final da matéria de Java SpringBoot

# Task One

## Objetivos
Foram feito de acordo com o que foi pedido para o primeiro dia, são elas as:

 * Interfaces
    * Serviços
    * Repositorios
 * Modelos do banco
 * Testes

### Interfaces(Services)
Cada Serviço contém as funções relacionadas as entidades, como criar, deletar ou editar

### Modelos do banco de dados
Cada Banco foi desenvolvido para ter todas as informações necessárias de cada entidade, incluindo as relações, tabela relacional e ID.

 * #### @ManyToOne = Tabela que ira receber uma chave estrangeira de outra
 * #### @OneToMany = Simbolico, a fim de informar o banco a tabela forte da relação e facilitando o armazenamento de novas informações com hashSet ou List

### Repositorios
Vai pesquisar no banco de dados, configurado posteriormente, as funções do JPARepository e as funções novas com seus respectivos retornos, que implementamos nos repositorios

### DTOS
Nos DTOS, se tem os dados que serão requisitados para retorno ou como parametro, que posteriormente será configurado em cada endpoint ou service, para o devido funcionamento do sistema

### Testes
A parte de teste irá posteriormente ser usada para corrigir possiveis erros das funções, e conferir o pleno funcionamento do sistema, sem bugs que possam passar despercebidos. Ele contem testes que serão realizados nos seguintes campos:

 * Email
    * #### Vai testar se tem @, se tem caracteres antes e depois do @ e se ha . depois do @ indicando TLD no email
 * Senha
    * #### Vai testar se possui os caracteres requisitados pelo sistema, se possui Maiusculas e Minusculas, e se possui numeros






