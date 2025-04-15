import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authState = new BehaviorSubject<boolean>(false);
  private readonly TOKEN_KEY = 'access_token';

  constructor(private http: HttpClient) {}

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
    this.authState.next(true);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  clearToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this.authState.next(false);
  }

  checkAuthState(): void {
    const token = this.getToken();
    this.authState.next(!!token);
  }

  get isAuthenticated$(): Observable<boolean> {
    return this.authState.asObservable();
  }

 
}