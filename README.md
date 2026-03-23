# 🎮 RentGames - Sistema de Gestão de Aluguéis de Jogos

### 🚀 Status do Projeto: Em Desenvolvimento

O projeto encontra-se em fase de migração de persistência em memória (DataStore) para banco de dados relacional (MySQL) e refinamento das regras de negócio.

---

## 🎯 Objetivo do Software

O **RentGames** foi desenvolvido para facilitar o gerenciamento de locadoras de jogos de videogame. O sistema visa automatizar o controle de estoque, o registro de clientes e aluguéis, além de fornecer uma visão clara do fluxo de caixa e da saúde financeira do estabelecimento.

---

## 🛠 Tecnologias Aplicadas

As tecnologias utilizadas seguem a grade do curso de Desenvolvimento de Software:

- **Linguagem:** Java (JDK 17+)
- **Interface Gráfica:** Java Swing (NetBeans GUI Builder)
- **Banco de Dados:** MySQL 8.0
- **Conectividade:** JDBC (Java Database Connectivity)
- **Arquitetura:** Padrão DAO (Data Access Object) e Modelagem Orientada a Objetos.

---

## 👥 Time de Desenvolvedores

- **Nicolas** - Desenvolvedor Full Stack / DBA

---

## 📋 Funcionalidades do Sistema (Requisitos)

### 🔑 Segurança e Acesso

- **Autenticação:** Sistema de login com diferentes níveis de permissão (ADMIN e FUNCIONÁRIO).
- **Gestão de Usuários:** Cadastro, listagem e controle de acesso para colaboradores.

### 📦 Gestão de Catálogo e Clientes

- **Controle de Jogos:** Cadastro de títulos com ano, gênero, valor de aluguel e controle automático de estoque.
- **Gestão de Clientes:** Registro completo de locatários com CPF, contato e endereço.

### 🕹 Operacional (Locação)

- **Registro de Aluguéis:** Vinculação de jogos a clientes com cálculo de data de devolução.
- **Baixa Automática:** O sistema reduz o estoque do jogo automaticamente no momento do aluguel.
- **Sistema de Devolução:** Registro de retorno do jogo com reposição automática no estoque.

### 💰 Financeiro (Caixa)

- **Fluxo de Caixa:** Registro de entradas e saídas financeiras.
- **Saldo em Tempo Real:** Cálculo automático do saldo atual disponível no caixa.
- **Histórico de Transações:** Listagem detalhada de todas as movimentações financeiras.

---

## ⚙️ Como Inicializar o Banco de Dados

Para rodar o sistema, certifique-se de executar o script SQL de criação das tabelas no seu servidor MySQL local (localhost).

1. Certifique-se de que o banco `rentgames_db` foi criado.
2. Utilize o driver `mysql-connector-j` nas bibliotecas do projeto.
3. Configure as credenciais de acesso na classe `dao.Conexao`.
