  import { Component, inject } from '@angular/core';
  import { FormsModule } from '@angular/forms';
  import { ActivatedRoute, Router } from '@angular/router';
  import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
  import { RoomserviceService } from '../../service/roomservice.service';
  import { Room } from '../../models/room';
  import { ReservaService } from '../../service/reserva.service';
  import { Reserva } from '../../models/reserva';
  import { ReservaDTO } from '../../models/reserva-dto';

  import Swal from 'sweetalert2';
import { Usuarios } from '../../models/usuarios';
import { UsuariosService } from '../../service/usuarios.service';
import { Disciplina } from '../../models/disciplina';
import { DisciplinaService } from '../../service/disciplina.service';

  @Component({
    selector: 'app-reserva',
    imports: [MdbFormsModule, FormsModule],
    templateUrl: './reserva.component.html',
    styleUrl: './reserva.component.scss'
  })
  export class ReservaComponent {

  professores: Usuarios[] = [];
  usuarioService = inject(UsuariosService);

  disciplinas: Disciplina[] = []
  disciplinaService = inject(DisciplinaService)

    reserva: Reserva = new Reserva()
    reservaDto: ReservaDTO = new ReservaDTO()
    reservaService = inject(ReservaService)
    

    route = inject(Router)
    router = inject(ActivatedRoute)

    salaId?: number;
    reservaId?: number;

  ngOnInit(): void {
    this.router.params.subscribe(params => {
      if (params['salaId']) {
        this.salaId = +params['salaId'];
      }

      if (params['reservaId']) {
        this.reservaId = +params['reservaId'];
        this.buscarPorId(this.reservaId);
      }
    });
  }
  constructor() {
          this.carregarProfessores();
          this.carregarDisciplinas();
  }

    buscarPorId(id: number){
      this.reservaService.buscarPorId(id).subscribe({
        next: reserva => {
          this.reserva = reserva;
          this.reservaDto.dataHoraInicio = reserva.dataHoraInicio;
          this.reservaDto.dataHoraFim = reserva.dataHoraFim;
          this.reservaDto = {
          id_reserva: reserva.id_reserva,
          data: reserva.data,
          dataHoraInicio: reserva.dataHoraInicio,
          dataHoraFim: reserva.dataHoraFim,
          status: reserva.status,
          id_usuario: reserva.usuario?.id_usuario ?? 0 ,
          id_disciplina: reserva.disciplina?.id_disciplina ?? 0,
          id_sala: reserva.id_sala
        };
        },error: err => {
          alert(JSON.stringify(err))
        }
      })



    }




criar() {
  this.reservaDto.data = new Date();
  this.reservaDto.dataHoraInicio = this.reserva.dataHoraInicio;
  this.reservaDto.dataHoraFim = this.reserva.dataHoraFim;
  this.reservaDto.status = 'ATIVA';
  this.reservaDto.id_usuario = this.reservaDto.id_usuario;
  this.reservaDto.id_disciplina = this.reservaDto.id_disciplina;

  if (!this.reservaDto.id_sala && this.salaId) {
    this.reservaDto.id_sala = this.salaId;
  }

  if (this.reservaId) {
    this.reservaService.atualizar(this.reservaId, this.reservaDto).subscribe({
      next: () => {
        Swal.fire('Atualizado!', 'Reserva atualizada com sucesso.', 'success');
        this.route.navigate(['/admin/room/reservalist']);
      },
      error: err => {
        alert(JSON.stringify(err))

      }
    });
  } else {
    this.reservaService.criar(this.reservaDto).subscribe({
      next: () => {
        Swal.fire('Criado!', 'Reserva criada com sucesso.', 'success');
        this.route.navigate(['/admin/room/reservalist']);
      },
      error: err => {
        Swal.fire('Ops!', 'Data ou status da sala são inválidos para concluir a reserva', 'error');
      }
    });
  }
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

carregarDisciplinas() {
  this.disciplinaService.listarTodas().subscribe({
    next: lista => {
      this.disciplinas = lista;
    },
    error: err => {
      console.error('Erro ao carregar disciplinas:', err);
    }
  });
}


}


