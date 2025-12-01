import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Disciplinadto } from '../models/disciplinadto';
import { Observable } from 'rxjs';
import { Disciplina } from '../models/disciplina';

@Injectable({
  providedIn: 'root'
})
export class DisciplinaService {

  http = inject(HttpClient)
  API = "http://localhost:8085/disciplinas"
  
  constructor() { }

  criarDisciplina(disciplina: Disciplinadto): Observable<String>{
      return this.http.post<String>(this.API, disciplina, {responseType: 'text' as 'json'})
    }
  
  listarTodas(): Observable<Disciplina[]>{
      return this.http.get<Disciplina[]>(this.API)
    }
  
  
  deletarDisciplina(id:number): Observable<String>{
      return this.http.delete<String>(this.API+"/"+id, {responseType: 'text' as 'json'})
    }
  
  
  atualizarDisciplina(id: number, disciplina: Disciplinadto): Observable<String>{
      return this.http.put<String>(this.API+"/"+id, disciplina, {responseType: 'text' as 'json'})
    }
  
  
  buscarPorId(id: number): Observable<Disciplina>{
      return this.http.get<Disciplina>(this.API+"/"+id)
    }
  
  
}
