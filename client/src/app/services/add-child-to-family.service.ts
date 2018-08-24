import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from './message.service';
import { Child } from '../models/child';
import { Family } from '../models/family';
import { Urls } from '../models/urls'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

/**
 * Service for adding children to the family
 */
export class AddChildToFamilyService {

  private log(message: string) {
    this.messageService.add(`CreateFamilyService: ${message}`);
  }

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /**
   * This method appends a child to the family and stores child's entity in DB
   */
  AddChildToFamily(family: Family, child: Child): Observable<Child>{
    child.id = family.id;
    return this.http.post<Child>(Urls.childrenUrl, child, httpOptions).pipe(
      tap((child: Child) => this.log(`added child w/ pesel=${child.pesel}: ${child.firstName} ${child.secondName}`)),
      catchError(this.handleError<Child>('addChildToFamily'))
    );;
  }

  /**
   * Appends a collection of children to the family and stores entities of children in DB
   */
  AddChildrenToFamily(family: Family, children: Child[]): Promise<Child[]> {
    return Promise.all(
      children.map(
        (child: Child) => this.AddChildToFamily(family, child).toPromise()
      )
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
