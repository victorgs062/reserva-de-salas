CREATE TABLE HistoricoReserva (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    data_alteracao DATETIME NOT NULL,
    alteracao VARCHAR(250) NOT NULL,
    usuario_responsavel INT NOT NULL,
    FOREIGN KEY (id_reserva) REFERENCES Reserva(id_reserva),
    FOREIGN KEY (usuario_responsavel) REFERENCES Usuario(id_usuario)
);