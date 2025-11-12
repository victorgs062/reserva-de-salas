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

  @Component({
    selector: 'app-reserva',
    imports: [MdbFormsModule, FormsModule],
    templateUrl: './reserva.component.html',
    styleUrl: './reserva.component.scss'
  })
  export class ReservaComponent {


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
  constructor() {}

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
          id_usuario: 2,
          id_disciplina: 1,
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
  this.reservaDto.id_usuario = 2;
  this.reservaDto.id_disciplina = 1;

  if (!this.reservaDto.id_sala && this.salaId) {
    this.reservaDto.id_sala = this.salaId;
  }

  if (this.reservaId) {
    this.reservaService.atualizar(this.reservaId, this.reservaDto).subscribe({
      next: () => {
        Swal.fire('Atualizado!', 'Reserva atualizada com sucesso.', 'success');
        this.route.navigate(['/admin/room/reservalist']);
      },
      error: err => alert(JSON.stringify(err))
    });
  } else {
    this.reservaService.criar(this.reservaDto).subscribe({
      next: () => {
        Swal.fire('Criado!', 'Reserva criada com sucesso.', 'success');
        this.route.navigate(['/admin/room/reservalist']);
      },
      error: err => alert(JSON.stringify(err))
    });
  }
}

  }


