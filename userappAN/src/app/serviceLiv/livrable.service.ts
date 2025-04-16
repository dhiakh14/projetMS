import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Livrable } from '../common/livrable';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LivrableService {
  
  
  private baseUrl = 'http://localhost:8088/livrable/livrables';

  constructor(private httpClient: HttpClient) { }

  getLivrableList(): Observable<Livrable[]> {
    return this.httpClient.get<Livrable[]>(this.baseUrl);
  }

  addLivrable(livrable: Livrable): Observable<Livrable> {
    return this.httpClient.post<Livrable>("http://localhost:8088/livrable/livrables/add", livrable);
  }

  getLivrableById(id: number): Observable<Livrable> {
    return this.httpClient.get<Livrable>(`http://localhost:8088/livrable/livrables/getById/${id}`);
  }
  updateLivrable(id: number, livrable: Livrable): Observable<Livrable> {
    return this.httpClient.put<Livrable>(`http://localhost:8088/livrable/livrables/update/${id}`, livrable);
  }

  deleteLivrable(id: number) {
    return this.httpClient.delete(`http://localhost:8088/livrable/livrables/delete/${id}`); 
  }
  getLivrablesGroupedByProject(): Observable<{ [projectName: string]: Livrable[] }> {
    const url = `${this.baseUrl}/groupedByProject`;
    return this.httpClient.get<{ [projectName: string]: Livrable[] }>(url);
  }
  
  
  
}  
