import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RoomserviceService } from '../../service/roomservice.service';
import { Room } from '../../models/room';



@Component({
  selector: 'app-reserva',
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './reserva.component.html',
  styleUrl: './reserva.component.scss'
})
export class ReservaComponent {

    room: Room = new Room(0,0, false, '',0)
  
    reserva = {
    data: '',
    hora: ''
  };

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
        this.room.rent = true
      },error: err => {
        alert(err)
      }
    })
  }
  
  confirmarReserva() {
    console.log('Data:', this.reserva.data);
    console.log('Hora:', this.reserva.hora);
    this.route.navigate(['/admin/room'])

    alert(`Reserva confirmada para ${this.reserva.data} às ${this.reserva.hora}`);
  }


}
