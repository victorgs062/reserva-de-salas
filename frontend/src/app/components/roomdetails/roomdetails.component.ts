import { Component, inject } from '@angular/core';
import { Room } from '../../models/room';
import { RoomserviceService } from '../../service/roomservice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';


import Swal from 'sweetalert2';


@Component({
  selector: 'app-roomdetails',
  imports: [FormsModule, MdbFormsModule],
  templateUrl: './roomdetails.component.html',
  styleUrl: './roomdetails.component.scss'
})
export class RoomdetailsComponent {

  room: Room = new Room(0,0, false, '',0)
  roomService = inject(RoomserviceService)

  route = inject(Router)
  router = inject(ActivatedRoute)

  constructor(){
    let id = this.router.snapshot.params['id']
    if(id > 0){
      this.findById(id)
    }
  }

  findById(id: number){
    this.roomService.findById(id).subscribe({
      next: room => {
        this.room = room
      },error: err => {
        alert(err)
      }
    })
  }

  save(){
    Swal.fire({
            title: 'Sala salva',
            text: '',
            icon: 'success',
            showCancelButton: false,
            confirmButtonColor: '#35d630ff',
            confirmButtonText: 'Ok',

          }).then((result) => {

            if(this.room.id !== undefined && this.room.id > 0){
              this.roomService.update(this.room.id, this.room).subscribe({
                next: mesagem => {
                  this.route.navigate(['/admin/room'])
                },error: err => {
                  alert(err)
                }
              })
            }else{
              this.roomService.save(this.room).subscribe({
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
