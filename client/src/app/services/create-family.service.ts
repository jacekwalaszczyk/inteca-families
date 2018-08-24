import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Family } from '../models/family';
import { Father } from '../models/father';
import { MessageService } from './message.service';
import { Urls } from '../models/urls'
import { FamilyContent } from '../models/family-content';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

/**
 * Service for creating a family's entity in DB
 */
export class CreateFamilyService {

private log(message: string) {
  this.messageService.add(`CreateFamilyService: ${message}`);
}


constructor(
  private http: HttpClient,
  private messageService: MessageService) { }

  /**
   *  Public method for a family cration
   *  POST: create the family on the server
   */
  async CreateFamily(familyContent: FamilyContent): Promise<FamilyContent>
  {
    try{
      return await this.http.post<FamilyContent>(Urls.familiesContentUrl, familyContent, httpOptions).toPromise();
    }
    catch(err){
      this.handleError<Family>('addFamily');
      return null;
    }
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