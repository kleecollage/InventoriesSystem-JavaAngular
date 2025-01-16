import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: false,

  templateUrl: './product-list.component.html',
  styles: ``
})
export class ProductListComponent  implements OnInit{

  products: Product[];

  constructor(private productService: ProductService, private router: Router) { }


  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts() {
    this.productService.getProductsList().subscribe((data => {
      this.products = data;
    }));
  }

  editProduct(id: number) {
    this.router.navigate(["/edit-product", id]);
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe({
      next: (data) => this.getProducts(),
      error: (errors) => console.log(errors)
    });
  }
}
