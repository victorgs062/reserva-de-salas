import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from '../models/room';

@Injectable({
  providedIn: 'root'
})
export class RoomserviceService {

  http = inject(HttpClient)
  API = "http://localhost:8080/api/room"


  constructor() { }

  save(room: Room): Observable<String>{
    return this.http.post<String>(this.API+"/save", room, {responseType: 'text' as 'json'})
  }

  findAll(): Observable<Room[]>{
    return this.http.get<Room[]>(this.API+"/findAll")
  }


  delete(id:number): Observable<String>{
    return this.http.delete<String>(this.API+"/delete/"+id, {responseType: 'text' as 'json'})
  }


  update(id: number, room: Room): Observable<String>{
    return this.http.put<String>(this.API+"/update/"+id, room, {responseType: 'text' as 'json'})
  }


  findById(id: number): Observable<Room>{
    return this.http.get<Room>(this.API+"/findById/"+id)
  }


}
