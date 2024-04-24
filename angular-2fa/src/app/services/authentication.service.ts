import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequest} from "../modules/register-request";
import {AuthenticationResponse} from "../modules/authentication-response";
import {VerificationRequest} from "../modules/verification-request";
import {AuthenticationRequest} from "../modules/authentication-request";
import {Observable} from "rxjs";
import {UserShow} from "../modules/user-show";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl:string='http://localhost:8088/fundsphere'
  constructor(private http:HttpClient) {}

  register(registerRequest: RegisterRequest){
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/register`,registerRequest)
  }

  verifyCode(verificationRequest:VerificationRequest){
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/verify`,verificationRequest);
  }

  login(
    authRequest: AuthenticationRequest
  ) {
    return this.http.post<AuthenticationResponse>
    (`${this.baseUrl}/login`, authRequest);
  }

  showUsers():Observable<UserShow[]>{
    return this.http.get<UserShow[]>(`${this.baseUrl}/show-users`);
  }

  logout(): Observable<any> {
    return this.http.post(`${this.baseUrl}/logout`, null); // Assuming your backend accepts a simple POST request for logout
  }




}
