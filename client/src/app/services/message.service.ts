import { Injectable } from '@angular/core';
 
@Injectable({
  providedIn: 'root',
})

/**
 * Service for storing messages created inside the Angular application.
 * These messages help understand what happens on underground when particular actions are made.
 */
export class MessageService {
  messages: string[] = [];
 
  add(message: string) {
    this.messages.push(message);
    if (this.messages.length > 10) {this.messages = this.messages.slice(1)}
  }
 
  clear() {
    this.messages = [];
  }
}