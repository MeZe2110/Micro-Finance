import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from 'src/app/core/models/account';
import { AccountService } from 'src/app/core/services/account.service';

@Component({
  selector: 'app-account-client',
  templateUrl: './account-client.component.html',
  styleUrls: ['./account-client.component.css']
})
export class AccountClientComponent implements OnInit {
  accounts: Account[] = [];
  showAddForm: boolean = false; // Flag to control the visibility of the add form

  constructor(private accountService: AccountService, private router: Router) { }

  openAddForm() {
    this.showAddForm = true;
  }

  closeAddForm() {
    this.showAddForm = false;
  }

  ngOnInit(): void {
    // Fetch accounts for the current user
    this.accountService.getAllAccount().subscribe((data: Account[]) => {
      this.accounts = data;
    });
  }

  redirectToAddAccountForm(): void {
    this.showAddForm = !this.showAddForm; // Toggle the showAddForm flag
  }

  deleteAccount(account: Account): void {
    if (confirm('Are you sure you want to delete this account?')) {
      this.accountService.deleteAccount(account.idAccount).subscribe(() => {
        // Remove the deleted account from the accounts array
        this.accounts = this.accounts.filter(a => a !== account);
      });
    }
  }

  getImagePath(typeA: string): string {
    if (typeA === 'SAVING_ACCOUNT') {
      return 'assets/images/saving-account.jpg';
    } else {
      return 'assets/images/current-account1.jpg';
    }
  }
}
