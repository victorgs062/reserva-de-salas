import { Component, inject } from '@angular/core';
import { Room } from '../../models/room';
import { RoomserviceService } from '../../service/roomservice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';


import Swal from 'sweetalert2';
import { Usuarios } from '../../models/usuarios';
import { UsuariosService } from '../../service/usuarios.service';


@Component({
  selector: 'app-roomdetails',
  imports: [FormsModule, MdbFormsModule],
  templateUrl: './roomdetails.component.html',
  styleUrl: './roomdetails.component.scss'
})
export class RoomdetailsComponent {



  room: Room = new Room(0,'',0, '', '','')
  roomService = inject(RoomserviceService)

  route = inject(Router)
  router = inject(ActivatedRoute)

  constructor(){
    let id = this.router.snapshot.params['id']
    if(id > 0){
      this.buscarPorId(id)
    }
  }

  buscarPorId(id: number){
    this.roomService.buscarPorId(id).subscribe({
      next: room => {
        this.room = room
      },error: err => {
        alert(err)
      }
    })
  }

  criar(){
    Swal.fire({
            title: 'Sala salva',
            text: '',
            icon: 'success',
            showCancelButton: false,
            confirmButtonColor: '#35d630ff',
            confirmButtonText: 'Ok',

          }).then((result) => {

            if(this.room.id_sala !== undefined && this.room.id_sala > 0){
              this.roomService.atualizar(this.room.id_sala, this.room).subscribe({
                next: mesagem => {
                  this.route.navigate(['/admin/room'])
                },error: err => {
                  alert(err)
                }
              })
            }else{
              this.roomService.criar(this.room).subscribe({
                next: mesagem => {
                  
                  this.route.navigate(['/admin/room'])
                },error: err => {
                  alert(err)
                }
              })
            }



          })


}

}

