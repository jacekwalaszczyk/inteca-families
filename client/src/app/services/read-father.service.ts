import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Father } from '../models/father';
import { MessageService } from './message.service';
import { Urls } from '../models/urls'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ReadFatherService {
  private log(message: string) {
    this.messageService.add(`ReadFatherService: ${message}`);
  }
  
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  ReadFathers(): Observable<Father[]> {
    return this.http.get<Father[]>(Urls.fathersUrl).pipe(
      tap(fathers => this.log('fetched fathers')),
       catchError(this.handleError('ReadFathers', []))
      );
  }

  public ReadFather(id: number): Observable<Father> {
    const url = `${Urls.fathersUrl}/${id}`;
    return this.http.get<Father>(url).pipe(
      tap(_ => this.log(`fetched father id=${id}`)),
      catchError(this.handleError<Father>(`ReadFather id=${id}`))
    )};

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
 
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
 
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
