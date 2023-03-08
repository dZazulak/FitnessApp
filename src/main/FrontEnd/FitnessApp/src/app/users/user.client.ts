import { environment } from "src/environments/environment";
import { map, Observable } from 'rxjs';
import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from '@angular/core';
import { User } from 'src/app/users/user';
import { LoginPageComponent } from "../login-page/login-page.component";

@Injectable({
  providedIn: 'root',
})
export class UserClient implements OnInit{
  constructor(private http: HttpClient){}

  private username = localStorage.getItem("username");

  ngOnInit(): void{}

  getUsersInfo(): Observable<any>{
    return this.http.get(environment.apiUrl + 'users', {responseType: "json"});
  }
}

