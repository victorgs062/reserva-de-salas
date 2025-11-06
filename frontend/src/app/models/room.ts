export class Room {
    id_sala?: number
    nome!: string
    capacidade!: number
    recursos!: string
    tipoSala!: string
    tipoSalaBloco!: string
    

    constructor(id_sala: number, nome: string, capacidade: number, recursos: string, tipoSala: string, tipoSalaBloco:string){
        this.id_sala = id_sala,
        this.nome = nome,
        this.capacidade = capacidade,
        this.recursos = recursos,
        this.recursos = recursos,  
        this.tipoSala = tipoSala,  
        this.tipoSalaBloco = tipoSalaBloco  
    }
}
