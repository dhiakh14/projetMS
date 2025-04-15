<<<<<<< HEAD
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
=======
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
>>>>>>> origin/lahmer
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CodeInputModule } from 'angular-code-input';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HeaderComponent } from './pages/header/header.component';
import { FooterComponent } from './pages/footer/footer.component';
import { AddTaskComponent } from './pages/add-task/add-task.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { SidebarComponent } from './pages/sidebar/sidebar.component';
import { AffecterRoleComponent } from './pages/affecter-role/affecter-role.component';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { TaskDetailsComponent } from './pages/task-details/task-details.component';
import { ToastrModule } from 'ngx-toastr';
import { GanttChartListComponent } from './pages/gantt-chart-list/gantt-chart-list.component';
<<<<<<< HEAD
import { AboutComponent } from './pages/about/about.component';
import { QRCodeModule } from 'angularx-qrcode';
import { NgChartsModule } from 'ng2-charts';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxPaginationModule } from 'ngx-pagination';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { KeycloaksecurityComponent } from './keycloaksecurity/keycloaksecurity.component';
import { KeycloakService } from './services/keycloak/keycloak.service';

export function kcFactory(keycloakService: KeycloakService) {
  return () => keycloakService.init();
}

=======
import { FactureDetailsComponent } from './pages/aziz1/facture-details/facture-details.component';
import { FactureFormComponent } from './pages/aziz1/facture-form/facture-form.component';
import { FactureListComponent } from './pages/aziz1/facture-list/facture-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FactureStatComponent } from './pages/aziz1/facture-stat/facture-stat.component';
>>>>>>> origin/lahmer





export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    ProfileComponent,
    HeaderComponent,
    FooterComponent,
    AddTaskComponent,
    TasksComponent,
    SidebarComponent,
    AffecterRoleComponent,
    HomeComponent,
    TaskDetailsComponent,
    GanttChartListComponent,
<<<<<<< HEAD
    AboutComponent,
    DashboardComponent,
    ResetPasswordComponent,
    KeycloaksecurityComponent,
=======
    FactureListComponent,
    FactureDetailsComponent,
    FactureFormComponent,
    FactureStatComponent

>>>>>>> origin/lahmer
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
<<<<<<< HEAD
    ReactiveFormsModule,
    CodeInputModule,
    MatDialogModule,
    QRCodeModule,
    NgbModule,
    NgChartsModule,
    NgxPaginationModule,
=======
    CodeInputModule,
    MatDialogModule,
    ReactiveFormsModule,
>>>>>>> origin/lahmer
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
  ],
<<<<<<< HEAD
  providers: [
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
=======
  providers: [],

  bootstrap: [AppComponent]
>>>>>>> origin/lahmer
})
export class AppModule { }
