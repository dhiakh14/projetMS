import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CodeInputModule } from 'angular-code-input';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HeaderComponent } from './pages/header/header.component';
import { FooterComponent } from './pages/footer/footer.component';

import { SidebarComponent } from './pages/sidebar/sidebar.component';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { ToastrModule } from 'ngx-toastr';
import { FactureDetailsComponent } from './pages/aziz1/facture-details/facture-details.component';
import { FactureFormComponent } from './pages/aziz1/facture-form/facture-form.component';
import { FactureListComponent } from './pages/aziz1/facture-list/facture-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgChartsModule } from 'ng2-charts';
import { FactureStatComponent } from './pages/aziz1/facture-stat/facture-stat.component';
import { ExchangeRatesComponent } from './pages/aziz1/exchange-rates/exchange-rates.component';






export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
   
    HeaderComponent,
    FooterComponent,
   
    SidebarComponent,
    HomeComponent,
    
    FactureListComponent,
    FactureDetailsComponent,
    FactureFormComponent,
    FactureStatComponent,
    ExchangeRatesComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CodeInputModule,
    MatDialogModule,
    NgChartsModule,
    ReactiveFormsModule,
    
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
  providers: [],

  bootstrap: [AppComponent]
})
export class AppModule { }
