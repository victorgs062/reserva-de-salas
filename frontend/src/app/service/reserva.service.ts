import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Reserva } from '../models/reserva';
import { Observable } from 'rxjs';
import { ReservaDTO } from '../models/reserva-dto';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor() { }

  http = inject(HttpClient)
  API = "http://localhost:8085/reservas"

  criar(reserva: ReservaDTO): Observable<String>{
      return this.http.post<String>(this.API, reserva, {responseType: 'text' as 'json'})
    }
  
  listar(): Observable<Reserva[]>{
    return this.http.get<Reserva[]>(this.API)
  }
  
  
  deletar(id:number): Observable<String>{
    return this.http.delete<String>(this.API+"/"+id, {responseType: 'text' as 'json'})
  }
  
  
  atualizar(id: number, reserva: ReservaDTO): Observable<String>{
    return this.http.put<String>(this.API+"/"+id, reserva, {responseType: 'text' as 'json'})
  }
  
  
  buscarPorId(id: number): Observable<Reserva>{
    return this.http.get<Reserva>(this.API+"/"+id)
  }


}
