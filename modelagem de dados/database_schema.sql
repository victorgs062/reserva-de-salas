CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL UNIQUE,
    senha VARCHAR(250) NOT NULL,
    tipo_usuario ENUM('Professor', 'Aluno', 'Coordenacao') NOT NULL
);

CREATE TABLE Sala (
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    capacidade INT NOT NULL,
    recursos VARCHAR(250) NOT NULL,
    status ENUM('Ativa', 'Inativa') NOT NULL
);

CREATE TABLE Disciplina (
    id_disciplina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    codigo INT NOT NULL UNIQUE,
    qtd_alunos INT NOT NULL,
    id_professor INT NOT NULL,
    FOREIGN KEY (id_professor) REFERENCES Usuario(id_usuario)
);


CREATE TABLE Reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME NOT NULL,
    data_hora_inicio DATETIME NOT NULL,
    data_hora_fim DATETIME NOT NULL,
    status ENUM('Ativa', 'Cancelada', 'Pendente', 'Concluida') NOT NULL,
    id_usuario INT NOT NULL,
    id_sala INT NOT NULL,
    id_disciplina INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_sala) REFERENCES Sala(id_sala),
    FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id_disciplina)
);

CREATE TABLE Notificacao (
    id_notificacao INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    mensagem VARCHAR(250) NOT NULL,
    data_envio DATETIME NOT NULL,
    tipo ENUM('Confirmacao', 'Alteracao', 'Cancelamento') NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE HistoricoReserva (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    data_alteracao DATETIME NOT NULL,
    alteracao VARCHAR(250) NOT NULL,
    usuario_responsavel INT NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES Reserva(id_reserva),
    FOREIGN KEY (usuario_responsavel) REFERENCES Usuario(id_usuario)
);