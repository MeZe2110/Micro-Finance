import {Component, OnInit} from '@angular/core';
import {UserShow} from "../../modules/user-show";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit{
  users: UserShow[] = [];
  isLoading = false;
  error: string | null = null;
  displayedColumns: string[] = ['username', 'firstname', 'lastname', 'role', 'mfaEnabled'];



  constructor(private authService: AuthenticationService, private router: Router){}

  ngOnInit(): void {
    this.isLoading = true;

    this.authService.showUsers() // Replace with your service call if using UserService
      .subscribe(users => {
        this.users = users;
        this.isLoading = false;
      }, error => {
        this.error = error.message;
        this.isLoading = false;
      });
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
