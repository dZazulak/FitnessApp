import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthentication } from '../users/authentication.user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private tokenKey = 'token';

  constructor(private userAuthenticaton: UserAuthentication, private router: Router) {}

  public login(username: string, password: string): void {
    this.userAuthenticaton.login(username, password).subscribe((token) => {
      localStorage.setItem(this.tokenKey, token);
      this.router.navigate(['/']);
    });
  }

  public register(username: string, password: string, firstName: string, lastName: string): void {
    this.userAuthenticaton.register(username, password, firstName, lastName).subscribe((token) => {
      localStorage.setItem(this.tokenKey, token);
      this.router.navigate(['/']);
    });
  }

  public logout(){
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);
  }

  public isLoggedIn(): boolean{
    let token = localStorage.getItem(this.tokenKey);
    return token != null && token.length > 0;
  }

  public getToken(): string | null{
    return this.isLoggedIn() ? localStorage.getItem(this.tokenKey) : null;
  }
}
