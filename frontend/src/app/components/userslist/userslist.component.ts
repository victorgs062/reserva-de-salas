import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RouterModule } from '@angular/router';

import Swal from 'sweetalert2';
import { Usuarios } from '../../models/usuarios';
import { UsuariosService } from '../../service/usuarios.service';


@Component({
  selector: 'app-userslist',
  imports: [FormsModule, MdbFormsModule, RouterModule],
  templateUrl: './userslist.component.html',
  styleUrl: './userslist.component.scss'
})
export class UserslistComponent {
  lista: Usuarios[] = []

  IdBusca: number | null = null

  usuarioService = inject(UsuariosService)

  constructor(){
    this.listarTodos()
  }

  listarTodos(){
    this.usuarioService.listarTodos().subscribe({
      next: lista =>{
        this.lista = lista
      },error: err =>{
        alert(err)
      }
    })
  }


  deletar(usuario: Usuarios){
    
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
  if(usuario.id_usuario !== undefined){
      if (result.isConfirmed) {
      Swal.fire('Excluído!', 'O usuário foi removido com sucesso.', 'success');
      this.usuarioService.deletar(usuario.id_usuario).subscribe({
        next: mensagem => {
          this.listarTodos();
        },error: err =>{
          alert(err)
        }
      })
    }
  }

  });
  }

    Buscar(){
    if(this.IdBusca !== null){
      this.usuarioService.obterPorId(this.IdBusca).subscribe({
        next: retorno => {
          this.lista = [retorno]
        },error: err => {
          Swal.fire('Ops!', 'Usuário não encontrado', 'warning');
          this.listarTodos()

        }
      })  
    }else{
      this.listarTodos()
    }
  }
}
