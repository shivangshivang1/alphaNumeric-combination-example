import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders} from '@angular/common/http';

@Injectable()
export class AlphaNumericService {

  constructor(private http: HttpClient) {}

  getPageResults(phoneNumber: any, startIndex: any, maxResult: any): Observable<any> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('Access-Control-Allow-Headers', 'Content-Type')
      .append('Access-Control-Allow-Methods', 'GET')
      .append('Access-Control-Allow-Origin', '*');
    return this.http.get('http://localhost:8080/api/combinations/' + phoneNumber + '?startIndex=' + startIndex + '&maxResult=' + maxResult, {headers});
  }
}
