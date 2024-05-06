import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { RegisterRequest } from "../models/register-request";
import { AuthenticationResponse } from "../models/authentication-response";
import { AuthenticationRequest } from "../models/authentication-request";
import { Observable } from "rxjs";
import { UserShow } from "../models/user-show";


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl: string = 'http://localhost:8089/fundsphere'
  constructor(private http: HttpClient) { }

  register(registerRequest: RegisterRequest) {
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/register`, registerRequest)
  }

  login(
    authRequest: AuthenticationRequest
  ) {
    return this.http.post<AuthenticationResponse>
      (`${this.baseUrl}/login`, authRequest);
  }

  showUsers(): Observable<UserShow[]> {
    return this.http.get<UserShow[]>(`${this.baseUrl}/show-users`);
  }

  logout(): Observable<any> {
    return this.http.post(`${this.baseUrl}/logout`, null); // Assuming your backend accepts a simple POST request for logout
  }




}
