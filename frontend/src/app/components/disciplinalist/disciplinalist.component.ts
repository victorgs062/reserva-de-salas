import { Component, inject } from '@angular/core';
import { Disciplina } from '../../models/disciplina';
import { DisciplinaService } from '../../service/disciplina.service';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../service/authservice.service';


import Swal from 'sweetalert2';


@Component({
  selector: 'app-disciplinalist',
  imports: [FormsModule, MdbFormsModule, RouterLink],
  templateUrl: './disciplinalist.component.html',
  styleUrl: './disciplinalist.component.scss'
})
export class DisciplinalistComponent {
  lista: Disciplina[] = []

  disciplinaService = inject(DisciplinaService)

  IdBusca: number | null = null

  auth = inject(AuthService)


  constructor(){
    this.listarTodas()
  }


  listarTodas(){
    this.disciplinaService.listarTodas().subscribe({
      next: lista => {
        this.lista = lista
      },error: err => {
        alert(JSON.stringify(err))
      }
    })
  }



  
    deletarDisciplina(disciplina: Disciplina){
      Swal.fire({
          title: 'Tem certeza?',
          text: '',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Excluir',
          cancelButtonText: 'Cancelar'
        }).then((result) => {
          if(disciplina.id_disciplina != undefined){
            if (result.isConfirmed) {
              
              this.disciplinaService.deletarDisciplina(disciplina.id_disciplina).subscribe({
            next: mesage => {
              Swal.fire('Excluído!', 'Disciplina removida com sucesso.', 'success');
              this.listarTodas()
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
        this.disciplinaService.buscarPorId(this.IdBusca).subscribe({
          next: retorno => {
            this.lista = [retorno]
          },error: err => {
            Swal.fire('Ops!', 'Disciplina não encontrada', 'warning');
  
          }
        })  
      }else{
        this.listarTodas()
      }
    }
}
