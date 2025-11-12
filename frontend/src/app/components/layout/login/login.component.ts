import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { AuthService } from '../../../service/authservice.service';

import Swal from 'sweetalert2';



@Component({
  selector: 'app-login',
  imports: [FormsModule, MdbFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  email = '';
  password = '';
  private authService = inject(AuthService);
  private router = inject(Router);

  onLogin() {

    this.authService.login(this.email, this.password).subscribe({
      next: (res) => {
        this.router.navigate(['/admin']);
      },
      error: (err) => {
        this.email = ''
        this.password = ''
        Swal.fire('Ops!', 'Email ou senha inválido', 'warning');


      }
    });
  }
}
