import { Component } from '@angular/core';

import { Router } from "@angular/router";
import { AuthenticationRequest } from 'src/app/core/models/authentication-request';
import { AuthenticationResponse } from 'src/app/core/models/authentication-response';
import { AuthenticationService } from 'src/app/core/services/authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {};
  authResponse: AuthenticationResponse = {};

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {
  }

  authenticate() {
    this.authService.login(this.authRequest)
      .subscribe({
        next: (response) => {
          this.authResponse = response;
          localStorage.setItem('token', response.accessToken as string);
          const role = this.authResponse.role;
          switch (role) {
            case 'ADMIN':

              this.router.navigate(['admin/dashboard']);
              break;
            case 'CLIENT':

              this.router.navigate(['customer/dashboard']);
              break;
              case 'AGENT':

              this.router.navigate(['agent/dashboard']);
              break;
          }
        }
      }
      );
  }
}