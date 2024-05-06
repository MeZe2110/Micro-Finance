import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-dashboard-admin',
  templateUrl: './dashboard-admin.component.html',
  styleUrls: ['./dashboard-admin.component.css']
})
export class DashboardAdminComponent {
  showAccountAdmin: boolean = false;


  constructor(private authService: AuthenticationService, private router: Router){}

  
  redirectToCompte() {
    this.router.navigateByUrl('/admin/compte');
  }
  isCompteRoute(): boolean {
    return this.router.url === '/admin/compte';
  }


  redirectToTransaction() {
    this.router.navigateByUrl('/admin/transaction');
  }
  isTransactionRoute(): boolean {
    return this.router.url === '/admin/transaction';
  }
  


  logout() {
    this.authService.logout().subscribe(() => {
      // Handle successful logout:
      this.clearUserData();  // Call a function to clear user data
      this.router.navigate(['/login']);  // Redirect to login route (assuming 'router' is injected)
    }, (error) => {
      // Handle logout error
      console.error("Error logging out:", error);  // Example error handling
    });
  }

  private clearUserData() {

    // Remove user data from local storage, cookies, etc. (if applicable)
    localStorage.removeItem('currentUser');  // Example using localStorage
    // Add additional logic to remove data from other storage mechanisms (if used)

}


}
