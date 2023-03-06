import { environment } from "src/environments/environment";
import { Observable } from 'rxjs';
import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserClient {
  constructor(private http: HttpClient){}

  getUsersInfo(): Observable<any>{
    return this.http.get(environment.apiUrl + 'users');
  }
}
