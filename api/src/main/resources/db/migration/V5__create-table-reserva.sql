CREATE TABLE Reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME NOT NULL,
    data_hora_inicio DATETIME NOT NULL,
    data_hora_fim DATETIME NOT NULL,
    status ENUM('ATIVA', 'CANCELADA', 'PENDENTE', 'CONCLUIDA') NOT NULL,
    id_usuario INT NOT NULL,
    id_sala INT NOT NULL,
    id_disciplina INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_sala) REFERENCES Sala(id_sala),
    FOREIGN KEY (id_disciplina) REFERENCES Disciplina(id_disciplina)
);