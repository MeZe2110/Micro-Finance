import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Transaction } from '../../core/models/transaction';
import { AutomaticTransfer } from '../../core/models/automatic-transfer';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8089/fundsphere'


  addTransaction(transaction: Transaction) {
    return this.http.post(`${this.baseUrl}/transaction/add-transaction`, transaction);
  }

  updateTransaction(transaction: Transaction) {
    return this.http.put(`${this.baseUrl}/transaction/update-transaction`, transaction);
  }

  getAlltransaction() {
    return this.http.get<Transaction[]>(`${this.baseUrl}/transaction/show-transaction`);
  }

  deleteTransaction(id: number) {
    return this.http.delete(`${this.baseUrl}/transaction/delete-transaction/${id}`);
  }
}
