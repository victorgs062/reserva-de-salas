import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { tap, Observable } from 'rxjs';
import { API_URL } from '../app.config';

interface AuthResponse {
  accessToken: string;
  refreshToken: string;
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = `${API_URL}/api/auth`;

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
}