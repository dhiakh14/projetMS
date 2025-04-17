import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { authGuard } from './guards/auth.guard';
import { nonAdminGuardGuard } from './guards/non-admin-guard.guard';

import { HomeComponent } from './pages/home/home.component';

import { FactureListComponent } from './pages/aziz1/facture-list/facture-list.component';
import { FactureFormComponent } from './pages/aziz1/facture-form/facture-form.component';
import { FactureDetailsComponent } from './pages/aziz1/facture-details/facture-details.component';
import { FactureStatComponent } from './pages/aziz1/facture-stat/facture-stat.component';
import { ExchangeRatesComponent } from './pages/aziz1/exchange-rates/exchange-rates.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},

  {path: 'facture', component: FactureListComponent},
  {path: 'addfacture', component: FactureFormComponent},
  { path: 'facture-details/:idF', component: FactureDetailsComponent },
  {path: 'stat', component:FactureStatComponent},
  {path: 'rate', component: ExchangeRatesComponent},
 


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
