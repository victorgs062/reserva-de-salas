import { Component, inject } from '@angular/core';
import { AppComponent } from '../../../app.component';
import { Router, RouterOutlet } from '@angular/router';
import { RoomlistComponent } from "../../roomlist/roomlist.component";
import { MenuComponent } from "../menu/menu.component";

@Component({
  selector: 'app-principal',
  imports: [MenuComponent],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.scss'
})
export class PrincipalComponent {

}
