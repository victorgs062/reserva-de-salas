CREATE TABLE Notificacao (
    id_notificacao INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    mensagem VARCHAR(250) NOT NULL,
    data_envio DATETIME NOT NULL,
    tipo ENUM('CONFIRMACAO', 'ALTERACAO', 'CANCELAMENTO') NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);