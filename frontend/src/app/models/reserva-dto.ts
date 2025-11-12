export class ReservaDTO {
    id_reserva?: number
    data!: Date | string
    dataHoraInicio!: Date | string
    dataHoraFim!: Date | string
    status!: string
    id_usuario!: number
    id_sala!: number
    id_disciplina!: number
}
