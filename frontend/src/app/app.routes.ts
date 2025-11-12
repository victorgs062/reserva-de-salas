import { Routes } from '@angular/router';
import { PrincipalComponent } from './components/layout/principal/principal.component';
import { RoomlistComponent } from './components/roomlist/roomlist.component';
import { RoomdetailsComponent } from './components/roomdetails/roomdetails.component';
import { ReservaComponent } from './components/reserva/reserva.component';
import { LoginComponent } from './components/layout/login/login.component';
import { UserslistComponent } from './components/userslist/userslist.component';
import { UsersdetailsComponent } from './components/usersdetails/usersdetails.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { ReservaslistComponent } from './components/reservaslist/reservaslist.component';
import { guardGuard } from './guards/guard.guard';

export const routes: Routes = [

    {path: '', redirectTo: 'login', pathMatch:'full'},
    {path: 'login', component: LoginComponent},


    {path: 'admin', component: PrincipalComponent, 
        canActivate:[guardGuard],
        children:[
        {path: 'room', component: RoomlistComponent},
        {path: 'room/new', component: RoomdetailsComponent},
        {path: 'room/edit/:id', component: RoomdetailsComponent},
        { path: 'room/reserva/new/:salaId', component: ReservaComponent },
        { path: 'room/reserva/edit/:reservaId', component: ReservaComponent },
        {path: 'room/reservalist', component: ReservaslistComponent},
        {path: 'users', component: UserslistComponent},
        {path: 'users/new', component: UsersdetailsComponent},
        {path: 'users/edit/:id', component: UsersdetailsComponent},
        {path: 'perfil', component: PerfilComponent},

   
    ]}
];
