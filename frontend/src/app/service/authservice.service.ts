import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { tap, Observable } from 'rxjs';
import { API_URL } from '../app.config';


import {jwtDecode} from 'jwt-decode';


interface AuthResponse {
  accessToken: string;
  refreshToken: string;
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = `http://localhost:8085/api/auth`;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/login`, { email, password })
      .pipe(tap(res => this.setTokens(res)));
  }

  refreshToken() {
    const refreshToken = this.getRefreshToken();
    return this.http.post<AuthResponse>(`${this.baseUrl}/refresh`, { refreshToken })
      .pipe(tap(res => this.setTokens(res)));
  }

  logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  }

  getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  getRefreshToken(): string | null {
    return localStorage.getItem('refreshToken');
  }

  isLoggedIn(): boolean {
    return !!this.getAccessToken();
  }

  private setTokens(res: AuthResponse) {
    localStorage.setItem('accessToken', res.accessToken);
    localStorage.setItem('refreshToken', res.refreshToken);
  }


getUserRoles(): string[] {
  const token = this.getAccessToken();
  if (!token) return [];

  const decoded: any = jwtDecode(token);

  const roles = [];

  if (decoded.role) {
    roles.push(...decoded.role.map((r: any) => r.authority));
  }

  if (decoded.roles) {
    roles.push(...decoded.roles.map((r: any) => r.authority));
  }

  return roles;
}
  hasRole(role: string): boolean {
    return this.getUserRoles().includes(role);
  }
}