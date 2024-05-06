import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/home/login/login.component';
import { DashboardAdminComponent } from './components/admin/dashboard-admin/dashboard-admin.component';
import { DashboardClientComponent } from './components/client/dashboard-client/dashboard-client.component';
import { DashboardInvestorComponent } from './components/investor/dashboard-investor/dashboard-investor.component';
import { AccountAdminComponent } from './components/admin/account-admin/account-admin';
import { DashboardProjectOwnerComponent } from './components/projectOwner/dashboard-project-owner/dashboard-project-owner.component';
import { DashboardAgentComponent } from './components/agent/dashboard-agent/dashboard-agent.component';
import { TransactionAdminComponent } from './components/admin/transaction-admin/transaction-admin';
import { AccountClientComponent } from './components/client/account-client/account-client.component';
import { AddAccountClientComponent } from './components/client/add-account-client/add-account-client.component';
import {TransactionClientComponent } from './components/client/transaction-client/transaction-client.component';
import { AddTransactionClientComponent } from './components/client/add-transaction-client/add-transaction-client.component';
import { TransactionagentComponent } from './components/agent/transaction-agent/transaction-agent.component';
import { AddTransactionagentComponent } from './components/agent/add-transaction-agent/add-transaction-agent.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    component: DashboardAdminComponent,
    children: [
      { path: 'dashboard', component: DashboardAdminComponent },
      { path: 'compte', component: AccountAdminComponent },
      { path: 'transaction', component: TransactionAdminComponent },
    ],
  },
  {
    path: 'agent',
    component: DashboardAgentComponent,
    children: [
      { path: 'dashboard', component: DashboardAgentComponent },
      { path: 'transaction', component: TransactionagentComponent },
      { path: 'addtransaction', component: AddTransactionagentComponent }
    ],
  },
  {
    path: 'customer',
    component: DashboardClientComponent,
    children: [
      { path: 'dashboard', component: DashboardClientComponent },
      { path: 'account', component: AccountClientComponent },
      { path: 'addaccount', component: AddAccountClientComponent },
      { path: 'transaction', component: TransactionClientComponent },
      { path: 'addtransaction', component: AddTransactionClientComponent },
    ],
  },
  {
    path: 'investor',
    component: DashboardInvestorComponent,
    children: [
      { path: 'dashboard', component: DashboardInvestorComponent },
    ],
  },
  {
    path: 'projectOwner',
    component: DashboardProjectOwnerComponent,
    children: [
      { path: 'dashboard', component: DashboardProjectOwnerComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
