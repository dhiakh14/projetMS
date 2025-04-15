import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { authGuard } from './guards/auth.guard';
import { nonAdminGuardGuard } from './guards/non-admin-guard.guard';
import { AddTaskComponent } from './pages/add-task/add-task.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { AffecterRoleComponent } from './pages/affecter-role/affecter-role.component';
import { HomeComponent } from './pages/home/home.component';
import { TaskDetailsComponent } from './pages/task-details/task-details.component';
import { GanttChartListComponent } from './pages/gantt-chart-list/gantt-chart-list.component';
<<<<<<< HEAD
import { AboutComponent } from './pages/about/about.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { connectedGuard } from './guards/connected.guard';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {path:'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'tasks', component: TasksComponent , canActivate:[connectedGuard]},
  { path: 'roles', component: AffecterRoleComponent, canActivate:[authGuard] },
  { path: 'task-details/:idTask', component: TaskDetailsComponent , canActivate:[connectedGuard]},
  {path: 'gantt' , component:GanttChartListComponent, canActivate:[connectedGuard]},
  {path: 'about', component:AboutComponent},
  {path: 'dash', component: DashboardComponent, canActivate:[connectedGuard]},
  {path:'reset-password', component: ResetPasswordComponent},
=======
import { FactureListComponent } from './pages/aziz1/facture-list/facture-list.component';
import { FactureFormComponent } from './pages/aziz1/facture-form/facture-form.component';
import { FactureDetailsComponent } from './pages/aziz1/facture-details/facture-details.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'tasks', component: TasksComponent },
  { path: 'roles', component: AffecterRoleComponent, canActivate:[authGuard] },
  { path: 'task-details/:idTask', component: TaskDetailsComponent },
  {path: 'gantt' , component:GanttChartListComponent},
>>>>>>> origin/lahmer

  {
    path: 'activate-account',
    component: ActivateAccountComponent
  },
  { path: 'profile/:idUser', component: ProfileComponent, canActivate: [authGuard] },
  { path: 'notadminusers', component: ProfileComponent, canActivate: [nonAdminGuardGuard] },
<<<<<<< HEAD
  { path: 'addtask', component: AddTaskComponent, canActivate:[connectedGuard] },
  { path: 'tasks/edit/:id', component: AddTaskComponent, canActivate:[connectedGuard] },
=======
  { path: 'addtask', component: AddTaskComponent },
  { path: 'tasks/edit/:id', component: AddTaskComponent },
  {path: 'facture', component: FactureListComponent},
  {path: 'addfacture', component: FactureFormComponent},
  { path: 'facture-details/:idF', component: FactureDetailsComponent },
 

>>>>>>> origin/lahmer

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
