import { Component, inject } from '@angular/core';
import { AppComponent } from '../../../app.component';
import { Router, RouterOutlet } from '@angular/router';
import { RoomlistComponent } from "../../roomlist/roomlist.component";
import { MenuComponent } from "../menu/menu.component";
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';


@Component({
  selector: 'app-principal',
  imports: [MenuComponent, MdbCarouselModule],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.scss'
})
export class PrincipalComponent {

}
