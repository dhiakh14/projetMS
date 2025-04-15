  import { Injectable } from '@angular/core';
  import { jwtDecode } from 'jwt-decode';


  @Injectable({
    providedIn: 'root'
  })
  export class TokenService {
    private readonly TOKEN_KEY = 'auth_token';

  get token(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  set token(token: string | null) {
    if (token) {
      localStorage.setItem(this.TOKEN_KEY, token);
    } else {
      localStorage.removeItem(this.TOKEN_KEY);
    }
  }
  getDecodedToken(): any {
    const token = this.token;
    if (token) {
      return jwtDecode(token);
    }
    return null;
  }

  getUserId(): number | null {
    const decoded = this.getDecodedToken();
    console.log("Decoded Token:", decoded);
    return decoded?.idUser || null; 
  }

  isAuthenticated(): boolean {
    return this.token !== null;
  }

 

  getUserRoles(): string[] {
    const decoded = this.getDecodedToken();
    console.log('Decoded Token:', decoded); 
  
    if (decoded?.roles && Array.isArray(decoded.roles)) {
      return decoded.roles.map((role: string) => role.toUpperCase()); 
    }
  
    return [];
  }
  
  
  clearToken(): void {
    localStorage.removeItem(this.TOKEN_KEY); 
  }
  
  }
