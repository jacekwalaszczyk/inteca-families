import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from './message.service';
import { Father } from '../models/father';
import { Family } from '../models/family';
import { Urls } from '../models/urls'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

/**
 * Service for adding a father's entity to the family
 */
export class AddFatherToFamilyService {

  private log(message: string) {
    this.messageService.add(`AddFatherToFamilyService: ${message}`);
  }

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /**
   * This method appends a father to the family and stores father's entity in DB
   */
  AddFatherToFamily(family: Family, father: Father): Observable<Father>{
    father.id = family.id;
    return this.http.post<Father>(Urls.fathersUrl, father, httpOptions).pipe(
      tap((father: Father) => this.log(`added father w/ id=${father.id}: ${father.firstName} ${father.secondName}`)),
      catchError(this.handleError<Father>('addFatherToFamily'))
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
