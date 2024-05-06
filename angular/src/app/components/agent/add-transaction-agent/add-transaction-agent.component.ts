import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from 'src/app/core/models/account';
import { Transaction } from 'src/app/core/models/transaction';
import { AccountService } from 'src/app/core/services/account.service';
import { TransactionService } from 'src/app/core/services/transaction.service';
import { Validators } from '@angular/forms';


@Component({
  selector: 'app-add-transaction-agent',
  templateUrl: './add-transaction-agent.component.html',
  styleUrls: ['./add-transaction-agent.component.css']
})
export class AddTransactionagentComponent implements OnInit {
  transactionForm: FormGroup;
  accounts: Account[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private accountService: AccountService,
    private transactionService: TransactionService // Inject the TransactionService
  ) {
    this.transactionForm = this.fb.group({
      sender: ['', Validators.required],
      receiver: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(0)]],
      typeT: ['', Validators.required] // Changed from 'type' to 'typeT'
    });
  }

  ngOnInit(): void {
    this.getAllAccounts();
  }

  getAllAccounts() {
    this.accountService.getAllAccount().subscribe(
      (response: Account[]) => {
        this.accounts = response;
      },
      (error) => {
        console.error('Error fetching accounts:', error);
      }
    );
  }

  submitTransaction() {
    if (this.transactionForm.valid) {
      const transactionData: Transaction = this.transactionForm.value;
  
      // Add current date to the transactionData object
      const currentDate = new Date(); // Get current date
      transactionData.date = currentDate;
  
      console.log('Transaction data:', transactionData); // Log the transaction data
      // Call the service to add the transaction
      this.transactionService.addTransaction(transactionData).subscribe(
        (response) => {
          // Redirect to a success page or handle success scenario
          console.log('Transaction added successfully:', response);
          this.router.navigate(['/agent/transaction']);
        },
        (error) => {
          // Handle error scenario
          console.error('Error adding transaction:', error);
        }
      );
    } else {
      // Mark all fields as touched to display validation messages
      this.transactionForm.markAllAsTouched();
    }
  }
  
  
  
}
