
-- 1. Cria o banco de dados se não existir
CREATE DATABASE IF NOT EXISTS rentgames_db_teste;
USE rentgames_db_teste;

-- 2. Tabela de Usuários (Login e Permissões)
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL,
    contato VARCHAR(20),
    cpf VARCHAR(20),
    endereco VARCHAR(200),
    perfil ENUM('ADMIN', 'FUNCIONARIO') DEFAULT 'FUNCIONARIO'    
);

-- 3. Tabela de Jogos
CREATE TABLE IF NOT EXISTS jogos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ano INT,
    plataforma VARCHAR(50),
    valor_aluguel DECIMAL(10, 2) NOT NULL,
    estoque INT DEFAULT 0,
    vendas INT DEFAULT 0,
    dias_aluguel INT DEFAULT 0
);

-- 4. Tabela de Clientes (Opcional, caso use o cadastro separado)
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    cpf VARCHAR(20) UNIQUE,
    endereco VARCHAR(200)
);

-- 5. Tabela de Aluguéis (Ajustada para o seu AluguelDAO atual)
CREATE TABLE IF NOT EXISTS alugueis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NULL, -- Pode ser NULL pois você salva os dados abaixo como texto
    nome_cliente VARCHAR(100),
    contato VARCHAR(50),
    cpf VARCHAR(20),
    endereco VARCHAR(200),
    id_jogo INT NOT NULL,
    dias_aluguel INT NOT NULL,
    data_inicio DATE NOT NULL,
    devolvido BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_jogo_aluguel FOREIGN KEY (id_jogo) REFERENCES jogos(id)
);

-- 6. Tabela de Financeiro (Caixa)
CREATE TABLE IF NOT EXISTS transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    tipo ENUM('ENTRADA', 'SAIDA') NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data VARCHAR(20) NOT NULL
);

-- ---------------------------------------------------------
-- DADOS INICIAIS PARA TESTE (Opcional)
-- ---------------------------------------------------------

-- Usuário padrão para login (Email: admin@admin.com / Senha: 123)
INSERT IGNORE INTO usuarios (nome, email, senha, perfil) 
VALUES ('Administrador', 'admin@rentgames.com', 'admin123', 'ADMIN');

-- Alguns jogos iniciais
INSERT IGNORE INTO jogos (nome, ano, plataforma, valor_aluguel, estoque, dias_aluguel) VALUES 
('The Witcher 3', 2015, 'PC', 15.00, 5, 7),
('God of War Ragnarok', 2022, 'PS1', 25.00, 3, 7),
('Elden Ring', 2022, 'PC', 20.00, 4, 7);

-- Saldo inicial do caixa
INSERT IGNORE INTO transacoes (descricao, tipo, valor, data) 
VALUES ('Abertura de Caixa', 'ENTRADA', 200.00, '22/03/2026');

-- Exemplo de cliente
INSERT IGNORE INTO clientes (nome, email, telefone, cpf, endereco)
VALUES ('Ana Clara', 'aninha@email.com', '123456789', '1547896452', 'Rua Dois');

-- Exemplo de aluguel
INSERT IGNORE INTO alugueis (nome_cliente, contato, cpf, endereco, id_jogo, dias_aluguel, data_inicio, devolvido)
VALUES ('Maria', '1549782', '15464585552', 'Rua Cinco', '1', '7', '2026-08-23', FALSE);