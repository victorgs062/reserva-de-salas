export class Room {
    id?: number
    capacity!: number
    rent!: boolean
    block!: string
    number!: number

    constructor(id: number, capacity: number, rent: boolean, block: string, number: number){
        this.id = id,
        this.capacity = capacity,
        this.rent = rent,
        this.block = block,
        this.number = number   
    }
}
