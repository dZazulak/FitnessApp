import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from '../users/user';
import { UserAuthentication } from '../users/authentication.user';
import { LoginPageComponent } from '../login-page/login-page.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [LoginPageComponent]
})
export class HomePageComponent implements OnInit {

  constructor(private userAuthentication: UserAuthentication){}

  public loginForm: FormGroup;
  private username: string|null= localStorage.getItem('username');

  public user: Observable<User[]> = this.userAuthentication.getUserInfo(this.username);

  // public users: Observable<any> = this.userClient.getUsersInfo();

  ngOnInit(): void {}

}

