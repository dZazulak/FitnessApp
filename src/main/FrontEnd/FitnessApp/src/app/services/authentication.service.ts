import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthentication } from '../users/authentication.user';
import { UserClient } from '../users/user.client';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private tokenKey = 'token';
  private userKey = 'username';

  constructor(private userAuthenticaton: UserAuthentication, private router: Router) {}

  public login(username: string, password: string): void {
    this.userAuthenticaton.login(username, password).subscribe((token) => {
      localStorage.setItem(this.tokenKey, token);
      localStorage.setItem(this.userKey, username);
      this.getUserInfo(username);
    });
  }

  public register(username: string, password: string, firstName: string, lastName: string): void {
    this.userAuthenticaton.register(username, password, firstName, lastName).subscribe((token) => {
      this.router.navigate(['/login']);

    });
  }

  public getUserInfo(username: string|null){
    this.userAuthenticaton.getUserInfo(username).subscribe((token)=>{
      this.router.navigate(['/home']);
    })
  }

  public logout(){
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userKey);
    this.router.navigate(['/login']);
  }

  public isLoggedIn(): boolean{
    let token = localStorage.getItem(this.tokenKey);
    let user = localStorage.getItem(this.userKey);
    return token != null && token.length > 0 && user != null;
  }

  public getToken(): string | null{
    return this.isLoggedIn() ? localStorage.getItem(this.tokenKey) : null;
  }
}
