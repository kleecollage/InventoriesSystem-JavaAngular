import { Component } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  standalone: false,

  templateUrl: './add-product.component.html',
  styles: ``
})
export class AddProductComponent {
  product: Product = new Product();

  constructor(private productService: ProductService, private router:Router) { }

  onSubmit() {
    this.saveProduct();
  }

  saveProduct() {
    this.productService.addProduct(this.product).subscribe({
      next: (data) => {
        this.goProductsList();
      },
      error: (error: any) => { console.log(error) }
    });
  }

  goProductsList() {
    this.router.navigate(['/products'])
  }
}
