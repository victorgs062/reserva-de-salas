import { Component, inject } from '@angular/core';
import { Student } from '../../models/student';
import { StudentserviceService } from '../../service/studentservice.service';
import { RoomserviceService } from '../../service/roomservice.service';
import { Room } from '../../models/room';
import { RouterLink } from "@angular/router";
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';

import Swal from 'sweetalert2';





@Component({
  selector: 'app-roomlist',
  imports: [RouterLink, FormsModule, MdbFormsModule],
  templateUrl: './roomlist.component.html',
  styleUrl: './roomlist.component.scss'
})
export class RoomlistComponent {
  IdBusca: number | null = null

  lista: Room[] = []

  roomService = inject(RoomserviceService)
  constructor(){
    this.findAll()
  }

  findAll(){
    this.roomService.findAll().subscribe({
      next: lista => {
        this.lista = lista
      },error: err => {
        alert(err)
      }
    })
  }


  delete(room: Room){
    Swal.fire({
        title: 'Tem certeza?',
        text: 'Você não poderá recuperar esta sala!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Excluir',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if(room.id != undefined){
          if (result.isConfirmed) {
            
            this.roomService.delete(room.id).subscribe({
          next: mesage => {
            Swal.fire('Excluído!', 'A sala foi removido com sucesso.', 'success');;
            this.findAll()
          },error: err => {
            alert(err)
          }
        })
      }
        }
      });

  }

  Buscar(){
    if(this.IdBusca !== null){
      this.roomService.findById(this.IdBusca).subscribe({
        next: retorno => {
          this.lista = [retorno]
        },error: err => {
          alert("Sala não encontrada")
        }
      })  
    }else{
      this.findAll()
    }
  }
}
