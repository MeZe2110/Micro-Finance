import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { Transaction } from 'src/app/core/models/transaction';
import { TransactionService } from 'src/app/core/services/transaction.service';
declare var $: any; // Declare jQuery

@Component({
  selector: 'app-transaction-admin',
  templateUrl: './transaction-admin.html', 
  styleUrls: ['./transaction-admin.css']
})

export class TransactionAdminComponent implements OnInit {
  transactions!: Transaction[];
  searchTerm: string = '';
  
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private transactionService: TransactionService
  ) {}

  get filteredTransactions() {
    return this.transactions.filter(transaction => {
      const searchTermLower = this.searchTerm.toLowerCase();
      const transactionDate = new Date(transaction.date);
      const formattedDate = `${('0' + transactionDate.getDate()).slice(-2)}-${('0' + (transactionDate.getMonth() + 1)).slice(-2)}-${transactionDate.getFullYear()}`;
      
      return transaction.amount.toString().includes(this.searchTerm) ||
             formattedDate.includes(searchTermLower) ||
             transaction.typeT.toLowerCase().includes(searchTermLower) ||
             (transaction.sender?.user?.firstname ?? '').toLowerCase().includes(searchTermLower) ||
             (transaction.sender?.user?.lastname ?? '').toLowerCase().includes(searchTermLower) ||
             (transaction.receiver?.user?.firstname ?? '').toLowerCase().includes(searchTermLower) ||
             (transaction.receiver?.user?.lastname ?? '').toLowerCase().includes(searchTermLower);
    });
  }

  ngOnInit(): void {
    // Fetch transaction data from the service
    this.transactionService.getAlltransaction().subscribe((data) => {
      this.transactions = data; // Assign the fetched data to the transactions variable

      // Initialize DataTable with options
      $('#transactionTable').DataTable({
        paging: true, // Enable pagination
        searching: true, // Enable search functionality
        lengthChange: true, // Enable length change
        info: true, // Enable info display
      });
    });
  }
}
