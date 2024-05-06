import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { Account } from 'src/app/core/models/account';
import { AccountService } from 'src/app/core/services/account.service';
declare var $: any; // Declare jQuery



@Component({
  selector: 'app-account-admin',
  templateUrl: './account-admin.html', 
  styleUrls: ['./account-admin.css']
  
})

export class AccountAdminComponent implements OnInit {
  accounts!: Account[];
  searchTerm: string = '';
  

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private accountService: AccountService
  ) {}


  get filteredAccounts() {
    return this.accounts.filter(account => {
      const searchTermLower = this.searchTerm.toLowerCase();
      const accountDate = new Date(account.date);
      const formattedDate = `${('0' + accountDate.getDate()).slice(-2)}-${('0' + (accountDate.getMonth() + 1)).slice(-2)}-${accountDate.getFullYear()}`;
      
      return (account.user?.firstname ?? '').toLowerCase().includes(searchTermLower) ||
             (account.user?.lastname ?? '').toLowerCase().includes(searchTermLower) ||
             account.numAccount.toString().includes(this.searchTerm) ||
             account.rib.toString().includes(this.searchTerm) ||
             account.balance.toString().includes(this.searchTerm) ||
             formattedDate.includes(searchTermLower) ||
             account.typeA.toLowerCase().includes(searchTermLower);
    });
  }
  
  
  

  ngOnInit(): void {
    // Fetch account data from the service
    this.accountService.getAllAccount().subscribe((data) => {
      this.accounts = data; // Assign the fetched data to the accounts variable

      // Initialize DataTable with options
      $('#accountTable').DataTable({
        paging: true, // Enable pagination
        searching: true, // Enable search functionality
        lengthChange: true, // Enable length change
        info: true, // Enable info display
      });
    });
  }
}
