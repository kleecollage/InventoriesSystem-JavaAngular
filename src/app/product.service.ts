import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/inventory-app/products'

  constructor(private httpClient: HttpClient) { }

  getProductsList(): Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.baseUrl);
  }
}
