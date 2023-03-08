import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment'
import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import { User } from "./user";

@Injectable({
  providedIn: 'root',
})
export class UserAuthentication{
  constructor(private http: HttpClient){}

public login(username: string, password: string): Observable<string>{
  return this.http.post(
    environment.apiUrl + 'user/login',
    {
      username: username,
      password: password,
    },
    {responseType: 'text'}
  );
}

public register(username: string, password: string, firstName: string, lastName: string): Observable<string>{
  return this.http.post(
    environment.apiUrl + 'create/user',
    {
      username: username,
      password: password,
      firstName: firstName,
      lastName: lastName,
    },
    {responseType: 'text'}
  );
}

public getUserInfo(username: string|null):Observable<User[]>{
  return this.http.get<User[]>(environment.apiUrl + 'user/' + username,
  {
    responseType: "json"
  }
);
}

}
