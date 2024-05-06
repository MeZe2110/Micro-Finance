import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/home/login/login.component';
import { DashboardAdminComponent } from './components/admin/dashboard-admin/dashboard-admin.component';
import { DashboardClientComponent } from './components/client/dashboard-client/dashboard-client.component';
import { DashboardInvestorComponent } from './components/investor/dashboard-investor/dashboard-investor.component';
import { DashboardProjectOwnerComponent } from './components/projectOwner/dashboard-project-owner/dashboard-project-owner.component';
import { ListAllTrainingAdminComponent } from './components/admin/list-all-training-admin/list-all-training-admin.component';
import { ListInscritTrainingAdminComponent } from './components/admin/list-inscrit-training-admin/list-inscrit-training-admin.component';
import { DetailTrainingAdminComponent } from './components/admin/detail-training-admin/detail-training-admin.component';
import { DashboardAgentComponent } from './components/agent/dashboard-agent/dashboard-agent.component';
import { DetailTrainingAgentComponent } from './components/agent/detail-training-agent/detail-training-agent.component';
import { ListInscritTrainingAgentComponent } from './components/agent/list-inscrit-training-agent/list-inscrit-training-agent.component';
import { ListAllTrainingAgentComponent } from './components/agent/list-all-training-agent/list-all-training-agent.component';
import { AccountAdminComponent } from './components/admin/account-admin/account-admin';
import { TransactionAdminComponent } from './components/admin/transaction-admin/transaction-admin';
import { AccountClientComponent } from './components/client/account-client/account-client.component';
import { AddAccountClientComponent } from './components/client/add-account-client/add-account-client.component';
import { TransactionClientComponent } from './components/client/transaction-client/transaction-client.component';
import { AddTransactionClientComponent } from './components/client/add-transaction-client/add-transaction-client.component';
import { TransactionagentComponent } from './components/agent/transaction-agent/transaction-agent.component';
import { AddTransactionagentComponent } from './components/agent/add-transaction-agent/add-transaction-agent.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardAdminComponent,
    DashboardClientComponent,
    DashboardInvestorComponent,
    DashboardProjectOwnerComponent,
    ListAllTrainingAdminComponent,
    ListInscritTrainingAdminComponent,
    DetailTrainingAdminComponent,
    DashboardAgentComponent,
    DetailTrainingAgentComponent,
    ListInscritTrainingAgentComponent,
    ListAllTrainingAgentComponent,
    AccountAdminComponent,
    TransactionAdminComponent,
    AccountClientComponent,
    AddAccountClientComponent,
    TransactionClientComponent,
    AddTransactionClientComponent,
    TransactionagentComponent,
    AddTransactionagentComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
