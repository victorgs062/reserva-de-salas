import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RouterModule } from '@angular/router';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-reservaslist',
  imports: [FormsModule,MdbFormsModule,  RouterModule],
  templateUrl: './reservaslist.component.html',
  styleUrl: './reservaslist.component.scss'
})
export class ReservaslistComponent {

  
    delete(){
      Swal.fire({
      title: 'Tem certeza?',
      text: 'Deseja cancelar essa reserva?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim',
      cancelButtonText: 'Não'
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire('Cancelada!', 'A reserva foi cancela, sala disponivel', 'success');
      }
    });
    }
}
