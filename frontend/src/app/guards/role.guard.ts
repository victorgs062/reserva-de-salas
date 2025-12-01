import { ActivatedRouteSnapshot, CanActivateFn } from '@angular/router';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../service/authservice.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {

    const allowedRoles = route.data['roles'] as string[];

    // Pega roles do token
    const userRoles = this.auth.getUserRoles();

    // Verifica interseção
    const hasPermission = allowedRoles.some(r => userRoles.includes(r));

    if (hasPermission) {
      return true;
    }

    // Redireciona para "acesso negado"
    this.router.navigate(['/admin']);
    return false;
  }
}