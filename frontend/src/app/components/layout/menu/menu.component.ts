import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../service/authservice.service'


@Component({
  selector: 'app-menu',
  imports: [RouterModule, MdbCollapseModule, CommonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {

  auth = inject(AuthService)


//  collapsed = false; // Para recolher no desktop
  mobileOpen = false; // Para abrir/fechar no mobile

  // toggleSidebar() {
  //   this.collapsed = !this.collapsed;
  // }

  toggleMobileSidebar() {
    this.mobileOpen = !this.mobileOpen;
  }

    closeMobileSidebar() {
    if (window.innerWidth <= 991) {
      this.mobileOpen = false;
    }
  }
}




