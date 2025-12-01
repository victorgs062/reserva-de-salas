import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuarios } from '../models/usuarios';
import { UsuariosDto } from '../models/usuarios-dto';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {



  http = inject(HttpClient)

  API = "http://localhost:8085/usuarios"

  constructor() { }

  listarTodos(): Observable<Usuarios[]>{
    return this.http.get<Usuarios[]>(this.API)
  }

  criar(usuarios: UsuariosDto): Observable<String>{
    return this.http.post<String>(this.API, usuarios, {responseType: 'text' as 'json'})
  }


  atualizar(id: number, usuario: UsuariosDto): Observable<String>{
    return this.http.put<String>(this.API+"/"+ id, usuario, {responseType: 'text' as 'json'})
  }

  deletar(id: number): Observable<boolean>{
    return this.http.delete<boolean>(this.API+"/"+id)
  }

  obterPorId(id: number): Observable<Usuarios>{
    return this.http.get<Usuarios>(this.API+"/"+id)
  }

  listarProfessores() {
    return this.http.get<any[]>(this.API+"/professores");
  }


}
