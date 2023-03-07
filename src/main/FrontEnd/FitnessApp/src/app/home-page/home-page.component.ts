import { Component, OnInit } from '@angular/core';
import { UserClient } from '../users/user.client';
import { AuthenticationService } from '../services/authentication.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  public user: any = this.userClient.getUserInfo();

  public users: Observable<any> = this.userClient.getUsersInfo();


  constructor(private authenticationService: AuthenticationService, private userClient: UserClient){}

  ngOnInit(): void {
    console.log(this.user);
    console.log(this.users);
  }

  logout(): void{
    this.authenticationService.logout();
  }
}

