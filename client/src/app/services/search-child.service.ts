import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Child } from '../models/child';
import { MessageService } from './message.service';
import { Urls } from '../models/urls'
import { formatDate } from '@angular/common';

@Injectable({
  providedIn: 'root'
})

export class SearchChildService {
  private log(message: string) {
    this.messageService.add(`SearchChildService: ${message}`);
  }
  
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  private appendCriteria(criteria: string, name: string, value: string): string {
    let prefix: string = '';
    if (value == '') {return criteria;}
    if (criteria != '') {prefix = '&&';}

    return criteria + prefix + name + value;
  }

	/**
	 * A service for searching children matching given criteria.
	 * You can set one, several or all parameters as a searching filter.
	 * The string parameters are treated as a beginning of a target string.
	 * Returns a collection of unique id values identifying families which found children belong to. 
	 */
  searchChild(firstName: string, secondName: string, birthDateRaw: string, pesel: string, sex: string): Observable<number[]> {
    let url: string;
    let searchCriteria: string = '';
    let ids: number[] = [];

    firstName = firstName.trim();
    secondName = secondName.trim();
    pesel = pesel.trim();
    birthDateRaw = birthDateRaw.trim();
    if (birthDateRaw) { birthDateRaw = formatDate(birthDateRaw, 'MM/dd/yyyy', 'en-US')};
    if (sex == null) { sex = ''}
    sex = sex.trim();
  
    if (!firstName && !secondName && !pesel && !birthDateRaw && !sex) {
      return null;
    }

    searchCriteria = this.appendCriteria(searchCriteria, 'firstName=', `${firstName}`);
    searchCriteria = this.appendCriteria(searchCriteria, 'secondName=', `${secondName}`);
    searchCriteria = this.appendCriteria(searchCriteria, 'birthDate=', `${birthDateRaw}`);
    searchCriteria = this.appendCriteria(searchCriteria, 'pesel=', `${pesel}`);
    searchCriteria = this.appendCriteria(searchCriteria, 'sex=', `${sex}`);

    url = `${Urls.childrenUrl}/search?${searchCriteria}`;

    return this.http.get<number[]>(url).pipe(
      tap(children => this.log(`found ${children.length} children matching "${searchCriteria}"`)),
      catchError(this.handleError<number[]>('searchChild', [])))
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
