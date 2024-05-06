import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from 'src/app/core/models/account';
import { AccountService } from 'src/app/core/services/account.service';

@Component({
  selector: 'app-add-account-client',
  templateUrl: './add-account-client.component.html',
  styleUrls: ['./add-account-client.component.css']
})
export class AddAccountClientComponent implements OnInit {
  accountForm!: FormGroup;
  currentDate: string = new Date().toISOString().split('T')[0]; // Initialize currentDate with today's date

  constructor(private formBuilder: FormBuilder, private accountService: AccountService,private router: Router) { }

  ngOnInit(): void {
    this.createAccountForm(); // Call createAccountForm on component initialization
    this.generateRandomValues(); // Call generateRandomValues to set random values for numAccount and rib
  }

  createAccountForm(): void {
    this.accountForm = this.formBuilder.group({
      numAccount: ['', Validators.required],
      rib: ['', Validators.required],
      balance: [0], // Set balance to 0 by default
      typeA: ['', Validators.required],
      date: [this.currentDate, Validators.required] // Set the initial value of date to currentDate
    });
  }

  generateRandomValues(): void {
    // Generate random values for numAccount and rib
    this.accountForm.patchValue({
      numAccount: Math.floor(Math.random() * 1000000) + 1,
      rib: Math.floor(Math.random() * 1000000000) + 1
    });
  }

  addAccount(): void {
    if (this.accountForm && this.accountForm.valid) {
      const newAccount: Account = this.accountForm.value;
      console.log('Account to be added:', newAccount); // Log the object being submitted
      this.accountService.addAccount(newAccount).subscribe(
        (response) => {
          console.log('Account added successfully:', response);
          this.accountForm.reset();
          // Navigate back to customer/dashboard
          this.router.navigate(['/customer/dashboard']);
        },
        (error) => {
          console.error('Error adding account:', error);
          // Optionally, you can display an error message to the user
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }
  
  
}
