import { Component } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-usersdetails',
  imports: [MdbFormsModule, RouterModule, FormsModule, CommonModule],
  templateUrl: './usersdetails.component.html',
  styleUrl: './usersdetails.component.scss'
})
export class UsersdetailsComponent {
  user = {
    name: '',
    email: '',
    password: '',
    type: ''
  };

  confirmPassword = '';
  confirmPasswordTouched = false;

  onSubmit() {
    this.confirmPasswordTouched = true;
    if (this.passwordsMatch()) {
      alert('Usuário cadastrado com sucesso!');
      console.log(this.user);
    } else {
      alert('As senhas não coincidem!');
    }
  }

  passwordsMatch(): boolean {
    return this.user.password === this.confirmPassword;
  }
}
