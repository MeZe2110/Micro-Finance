import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterRequest} from "../modules/register-request";
import {AuthenticationResponse} from "../modules/authentication-response";
import {VerificationRequest} from "../modules/verification-request";
import {AuthenticationRequest} from "../modules/authentication-request";

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

}
