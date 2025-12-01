import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common'; //

import Swal from 'sweetalert2';
import { Reserva } from '../../models/reserva';
import { ReservaService } from '../../service/reserva.service';
import { AuthService } from '../../service/authservice.service';



@Component({
  selector: 'app-reservaslist',
  imports: [FormsModule,MdbFormsModule,  RouterModule, DatePipe],
  templateUrl: './reservaslist.component.html',
  styleUrl: './reservaslist.component.scss'
})
export class ReservaslistComponent {
  lista: Reserva[] = []
  auth = inject(AuthService)

  IdBusca: number | null = null

  reservaService = inject(ReservaService)

  constructor(){
    this.listarTodos()
  }

  listarTodos(){
    this.reservaService.listar().subscribe({
      next: lista =>{
        this.lista = lista
      },error: err =>{
        alert(JSON.stringify(err))
      }
    })
  }


  
    deletar(reserva: Reserva){
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
      if(reserva.id_reserva !== undefined){
          if (result.isConfirmed) {
          Swal.fire('Excluído!', 'A reserva foi excluida, sala dsiponivel.', 'success');
          this.reservaService.deletar(reserva.id_reserva).subscribe({
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
      this.reservaService.buscarPorId(this.IdBusca).subscribe({
        next: retorno => {
          this.lista = [retorno]
        },error: err => {
          Swal.fire('Ops!', 'Reserva não encontrada', 'warning');
          this.listarTodos()

        }
      })  
    }else{
      this.listarTodos()
    }
  }
}
