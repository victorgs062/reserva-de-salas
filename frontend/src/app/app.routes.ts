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
    import { DisciplinalistComponent } from './components/disciplinalist/disciplinalist.component';
    import { DisciplinadetailsComponent } from './components/disciplinadetails/disciplinadetails.component';
    import { RoleGuard } from './guards/role.guard';


    export const routes: Routes = [

        {path: '', redirectTo: 'login', pathMatch:'full'},
        {path: 'login', component: LoginComponent},


        {path: 'admin', component: PrincipalComponent, 
            canActivate:[guardGuard],
            children:[
            {path: 'room', component: RoomlistComponent},
            {path: 'room/new', component: RoomdetailsComponent, 
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}},
            
            {path: 'room/edit/:id', component: RoomdetailsComponent,
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}
            },
            
            {path: 'room/reserva/new/:salaId', component: ReservaComponent,
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO', 'ROLE_PROFESSOR']}
            },

            {path: 'room/reserva/edit/:reservaId', component: ReservaComponent,
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO', 'ROLE_PROFESSOR']}
            },

            {path: 'room/reservalist', component: ReservaslistComponent, 
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO', 'ROLE_PROFESSOR']}
            },

            {path: 'users', component: UserslistComponent, 
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}
            },

            {path: 'users/new', component: UsersdetailsComponent, 
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}
            },

            {path: 'users/edit/:id', component: UsersdetailsComponent, 
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}
            },

            {path: 'perfil', component: PerfilComponent},


            {path: 'disciplina', component: DisciplinalistComponent, 
                
            },

            {path: 'disciplina/new', component: DisciplinadetailsComponent,
                canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}
            },

            {path: 'disciplina/edit/:id', component: DisciplinadetailsComponent,
                 canActivate:[RoleGuard], 
                data:{roles: ['ROLE_ADMIN', 'ROLE_COORDENACAO']}
            },

    
        ]}
    ];
