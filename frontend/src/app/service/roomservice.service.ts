import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from '../models/room';

@Injectable({
  providedIn: 'root'
})
export class RoomserviceService {

  http = inject(HttpClient)
  API = "http://localhost:8085/salas"


  constructor() { }

  criar(room: Room): Observable<String>{
    return this.http.post<String>(this.API, room, {responseType: 'text' as 'json'})
  }

  listar(): Observable<Room[]>{
    return this.http.get<Room[]>(this.API)
  }


  deletar(id:number): Observable<String>{
    return this.http.delete<String>(this.API+"/"+id, {responseType: 'text' as 'json'})
  }


  atualizar(id: number, room: Room): Observable<String>{
    return this.http.put<String>(this.API+"/"+id, room, {responseType: 'text' as 'json'})
  }


  buscarPorId(id: number): Observable<Room>{
    return this.http.get<Room>(this.API+"/"+id)
  }


}
