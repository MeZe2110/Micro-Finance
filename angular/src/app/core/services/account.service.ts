import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Account } from '../models/account';
import { AutomaticTransfer } from '../models/automatic-transfer';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseUrl = 'http://localhost:8089/fundsphere'

  constructor(private http: HttpClient) { }

  addAccount(account: Account): Observable<any> {
    const userId = 1; // Static user ID

    // Convert the date to a proper Date object if it's not already
    const date = account.date instanceof Date ? account.date : new Date(account.date);

    // Create a new object with only the properties you want to send
    const accountData = {
      numAccount: account.numAccount,
      rib: account.rib,
      balance: account.balance,
      typeA: account.typeA,
      date: date.toISOString().split('T')[0] // Convert Date to string
    };

    // Send the modified object to the server
    return this.http.post(`${this.baseUrl}/account/add-account/${userId}`, accountData);
  }
  
  

  updateAccount(account: Account) {
    return this.http.put(`${this.baseUrl}/account/update-account`, account);
  }

  getAllAccount() {
    return this.http.get<Account[]>(`${this.baseUrl}/account/show-account`);
  }

  deleteAccount(id: number) {
    return this.http.delete(`${this.baseUrl}/account/delete-account/${id}`);
  }
  
  // getAccountsByUserId(userId: number) {
  //   return this.http.get<Account[]>(`${this.baseUrl}/account/user/${userId}`);
  // }

}
