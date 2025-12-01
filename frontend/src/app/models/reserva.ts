import { Disciplina } from "./disciplina"
import { Usuarios } from "./usuarios"

export class Reserva {
    id_reserva?: number
    data!: Date | string
    dataHoraInicio!: Date | string
    dataHoraFim!: Date | string
    status!: string

    usuario?: Usuarios
    disciplina?: Disciplina

    nomeUsuario!: string
    nomeSala!: string
    nomeDisciplina!: string
    id_sala!: number
    id_disciplina!: number
    id_usuario!: number


}
