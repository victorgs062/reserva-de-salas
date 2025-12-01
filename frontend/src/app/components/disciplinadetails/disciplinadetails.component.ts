import { Component, inject } from '@angular/core';
import { Disciplina } from '../../models/disciplina';
import { DisciplinaService } from '../../service/disciplina.service';
import { ActivatedRoute, Router } from '@angular/router';

import Swal from 'sweetalert2';
import { Disciplinadto } from '../../models/disciplinadto';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { UsuariosService } from '../../service/usuarios.service';
import { Usuarios } from '../../models/usuarios';



@Component({
  selector: 'app-disciplinadetails',
  imports: [FormsModule, MdbFormsModule],
  templateUrl: './disciplinadetails.component.html',
  styleUrl: './disciplinadetails.component.scss'
})
export class DisciplinadetailsComponent {

  professores: Usuarios[] = [];
  usuarioService = inject(UsuariosService);

  disciplina: Disciplina = new Disciplina()
  disciplinadto: Disciplinadto = new Disciplinadto()
  disciplinaService = inject(DisciplinaService)

  route = inject(Router)
  router = inject(ActivatedRoute)


  

  constructor(){
    this.carregarProfessores();

    let id = this.router.snapshot.params['id']
    if(id > 0){
      this.buscarPorId(id)
    }
  }



  buscarPorId(id: number){
    this.disciplinaService.buscarPorId(id).subscribe({
      next: disciplina => {
        this.disciplina = disciplina
        this.disciplinadto.nome = disciplina.nome
        this.disciplinadto.codigo = disciplina.codigo
        this.disciplinadto.qtd_alunos = disciplina.qtd_alunos
        this.disciplinadto.id_professor = disciplina.professor?.id_usuario
      },error: err => {
        alert(JSON.stringify(err))
      }
    })
  }

  criarDisciplina(){


    Swal.fire({
            title: 'Disciplina salva',
            text: '',
            icon: 'success',
            showCancelButton: false,
            confirmButtonColor: '#35d630ff',
            confirmButtonText: 'Ok',

          }).then((result) => {

            if(this.disciplina.id_disciplina !== undefined && this.disciplina.id_disciplina > 0){
              this.disciplinaService.atualizarDisciplina(this.disciplina.id_disciplina, this.disciplinadto).subscribe({
                next: mesagem => {
                  this.route.navigate(['/admin/disciplina'])
                },error: err => {
                  alert(err)
                }
              })
            }else{
              this.disciplinaService.criarDisciplina(this.disciplinadto).subscribe({
                next: mesagem => {
                  this.route.navigate(['/admin/disciplina'])
                },error: err => {
                  alert(JSON.stringify(err))
                }
              })
            }



          })


}
carregarProfessores() {
  this.usuarioService.listarProfessores().subscribe({
    next: lista => {
      this.professores = lista;
      console.log('Professores carregados:', lista);
    },
    error: err => {
      console.error('Erro ao carregar professores:', err);
    }
  });
}


}
