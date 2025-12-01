import { Component, inject } from '@angular/core';
import { Student } from '../../models/student';
import { StudentserviceService } from '../../service/studentservice.service';
import { RoomserviceService } from '../../service/roomservice.service';
import { Room } from '../../models/room';
import { RouterLink } from "@angular/router";
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';

import { AuthService } from '../../service/authservice.service';


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

  auth = inject(AuthService)

  roomService = inject(RoomserviceService)
  constructor(){
    this.listar()
  }

  listar(){
    this.roomService.listar().subscribe({
      next: lista => {
        this.lista = lista
      },error: err => {
        alert(err)
      }
    })
  }


  deletar(room: Room){
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
        if(room.id_sala != undefined){
          if (result.isConfirmed) {
            this.roomService.deletar(room.id_sala).subscribe({
          next: mesage => {
            Swal.fire('Excluído!', 'A sala foi removido com sucesso.', 'success');
            this.listar()
          },error: err => {
            Swal.fire('Ops!', 'Não foi possível excluir a sala verifique se há reservas vinculadas.', 'error');
          }
        })
      }
        }
      });

  }

  Buscar(){
    if(this.IdBusca !== null){
      this.roomService.buscarPorId(this.IdBusca).subscribe({
        next: retorno => {
          this.lista = [retorno]
        },error: err => {
          Swal.fire('Ops!', 'Sala não encontrada', 'warning');

        }
      })  
    }else{
      this.listar()
    }
  }
}
