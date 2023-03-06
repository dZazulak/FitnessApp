import { Component, OnInit } from '@angular/core';
import { UserClient } from '../users/user.client';
import { AuthenticationService } from '../services/authentication.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-secret',
  templateUrl: './secret.component.html',
  styleUrls: ['./secret.component.css']
})
export class SecretComponent implements OnInit {
  public user: Observable<any> = this.userClient.getUsersInfo();

  constructor(private authenticationService: AuthenticationService, private userClient: UserClient){}

  ngOnInit(): void {}

  logout(): void{
    this.authenticationService.logout();
  }
}
