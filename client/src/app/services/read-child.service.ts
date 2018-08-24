import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Child } from '../models/child';
import { MessageService } from './message.service';
import { Urls } from '../models/urls'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class ReadChildService {
  private log(message: string) {
    this.messageService.add(`ReadChildService: ${message}`);
  }
  
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  ReadChildren(id: number): Observable<Child[]> {
    const url = `${Urls.childrenUrl}/id/${id}`;
  
    return this.http.get<Child[]>(url).pipe(
      tap(_ => this.log(`fetched children from family id=${id}`)),
      catchError(this.handleError<Child[]>(`ReadChildren from family id=${id}`))
      );
  }

	/**
	 * A public service which returns a Child object containing data from DB of a child entity with a given PESEL value 
	 */
  ReadChild(pesel: string): Observable<Child> {
    const url = `${Urls.childrenUrl}/pesel/${pesel}`;
  
    return this.http.get<Child>(url).pipe(
      tap(_ => this.log(`fetched child with pesel=${pesel}`)),
      catchError(this.handleError<Child>(`ReadChild with pesel=${pesel}`))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // Send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Show error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
