import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../models/item';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private baseUrl = "http://localhost:8081/items"; 

  constructor(private http: HttpClient) { }

  getAll(): Observable<Item[]>{
    return this.http.get<Item[]>(`${this.baseUrl}/all`);
  }

  getById(id: number): Observable<Item>{
    return this.http.get<Item>(`${this.baseUrl}/${id}`);
  }
  
  add(item: Item): Observable<Item> {
    return this.http.post<Item>(`${this.baseUrl}/register`, item);
  }

  update(id: number, item: Item): Observable<Item> {
    return this.http.put<Item>(`${this.baseUrl}/update/${id}`, item);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}


