import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Transaction } from 'src/app/core/models/transaction';
import { TransactionService } from 'src/app/core/services/transaction.service';

@Component({
  selector: 'app-transaction-agent',
  templateUrl: './transaction-agent.component.html',
  styleUrls: ['./transaction-agent.component.css']
})
export class TransactionagentComponent implements OnInit {
  searchForm: FormGroup; 
  transactions: Transaction[] = []; // Initialize transactions array

  constructor(private formBuilder: FormBuilder, private transactionService: TransactionService) {
    this.searchForm = this.formBuilder.group({
      searchTerm: ['']
    });
  }

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions() {
    this.transactionService.getAlltransaction().subscribe(transactions => {
      this.transactions = transactions;
    });
  }

  searchTransactions() {
    const searchTerm = this.searchForm.value.searchTerm.trim().toLowerCase();
    if (searchTerm === '') {
      this.loadTransactions();
    } else {
      this.transactions = this.transactions.filter(transaction =>
        (transaction.sender?.user?.firstname?.toLowerCase()?.includes(searchTerm) || false) ||
        (transaction.receiver?.user?.lastname?.toLowerCase()?.includes(searchTerm) || false)
      );
    }
  }
}
