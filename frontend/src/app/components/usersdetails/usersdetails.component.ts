import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UsuariosService } from '../../service/usuarios.service';
import { Usuarios } from '../../models/usuarios';
import { UsuariosDto } from '../../models/usuarios-dto';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-usersdetails',
  imports: [MdbFormsModule, RouterModule, FormsModule, CommonModule],
  templateUrl: './usersdetails.component.html',
  styleUrl: './usersdetails.component.scss'
})
export class UsersdetailsComponent {





  usuario: Usuarios = new Usuarios()
  usuarioDto: UsuariosDto = new UsuariosDto()

  usuarioService = inject(UsuariosService)

  route = inject(Router)
  router = inject(ActivatedRoute)

  constructor(){
    let id = this.router.snapshot.params['id']
    if(id > 0){
      this.obterPorId(id)
    }
  }

  obterPorId(id: number){
    this.usuarioService.obterPorId(id).subscribe({
      next: usuario => {
         this.usuario = usuario; // mantemos o objeto original
      // Copiamos apenas os campos necessários para o DTO
        this.usuarioDto.nome = usuario.nome;
        this.usuarioDto.email = usuario.email;
        this.usuarioDto.senha = usuario.senha;
        this.usuarioDto.tipoUsuario = usuario.tipoUsuario;
      },error: err => {
        alert(JSON.stringify(err))
      }
    })
  }

  // user = {
  //   name: '',
  //   email: '',
  //   password: '',
  //   type: ''
  // };

  // confirmPassword = '';
  // confirmPasswordTouched = false;

  // onSubmit() {
  //   this.confirmPasswordTouched = true;
  //   if (this.passwordsMatch()) {
  //     alert('Usuário cadastrado com sucesso!');
  //     console.log(this.usuario);
  //   } else {
  //     alert('As senhas não coincidem!');
  //   }
  // }

  // passwordsMatch(): boolean {
  //   return this.usuario.senha === this.confirmPassword;
  // }


criar(){

    if(this.usuario.id_usuario !== undefined && this.usuario.id_usuario > 0){
      this.usuarioService.atualizar(this.usuario.id_usuario, this.usuarioDto).subscribe({
        next: mesagem => {
          Swal.fire('Atualizado!', 'O cadastro do usuário foi atualizado com sucesso.', 'success');
          this.route.navigate(['/admin/users'])
        },error: err => {
          alert(JSON.stringify(err))
        }
      })
    }else{
      this.usuarioService.criar(this.usuarioDto).subscribe({
        next: mesagem => {
          Swal.fire('Salvo!', 'O usuário foi criado com sucesso.', 'success');
          this.route.navigate(['/admin/users'])
        },error: err => {
          alert(JSON.stringify(err))
        }
    })
    }

  }
}
