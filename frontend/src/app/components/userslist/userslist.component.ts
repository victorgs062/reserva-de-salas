import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RouterModule } from '@angular/router';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-userslist',
  imports: [FormsModule, MdbFormsModule, RouterModule],
  templateUrl: './userslist.component.html',
  styleUrl: './userslist.component.scss'
})
export class UserslistComponent {


  delete(){
    Swal.fire({
    title: 'Tem certeza?',
    text: 'Você não poderá recuperar este usuário!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Excluir',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire('Excluído!', 'O usuário foi removido com sucesso.', 'success');
    }
  });
  }
}
