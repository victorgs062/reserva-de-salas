import { Usuarios } from "./usuarios"

export class Disciplina {
    id_disciplina?: number
    nome!: string
    codigo!: number
    qtd_alunos!: number
    professor!: Usuarios
    nomeProfessor!: string
}
