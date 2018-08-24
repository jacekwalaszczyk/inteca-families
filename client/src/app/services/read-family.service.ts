import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Family } from '../models/family';
import { FamilyContent } from '../models/family-content';
import { MessageService } from './message.service';
import { Urls } from '../models/urls'

@Injectable({
  providedIn: 'root'
})
/**
 * Service for reading data of families
 */
export class ReadFamilyService {
  private log(message: string) {
    this.messageService.add(`ReadFamilyService: ${message}`);
  }

  
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

 	/**
	 * Returns a collection of Family objects containing data of all family entities stored in DB  
	 */
  getFamilies(): Observable<Family[]> {
    return this.http.get<Family[]>(Urls.familiesUrl).pipe(
      tap(families => this.log('fetched families')),
      catchError(this.handleError('getFamilies', []))
    );
  }

	/**
	 * Returns a Family object containing data from DB of a family entity with a given id 
	 */
  getFamily(id: number): Observable<Family> {
    const url = `${Urls.familiesUrl}/${id}`;
    return this.http.get<Family>(url).pipe(
      tap(_ => this.log(`fetched family id=${id}`)),
      catchError(this.handleError<Family>(`getFamily id=${id}`))
    );
  }

	/**
	 * Returns a FamilyContent object containing data from DB of all family's entities with a given id 
	 */
  ReadFamily(id: number): Observable<FamilyContent> {
    const url = `${Urls.familiesContentUrl}/${id}`;
    return this.http.get<FamilyContent>(url).pipe(
      tap(_ => this.log(`fetched familyContent id=${id}`)),
      catchError(this.handleError<FamilyContent>(`ReadFamily id=${id}`))
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
