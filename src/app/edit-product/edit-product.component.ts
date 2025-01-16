import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-edit-product',
  standalone: false,

  templateUrl: './edit-product.component.html',
  styles: ``
})
export class EditProductComponent implements OnInit {
  product: Product = new Product();
  id: number;

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe({
      next: (data) => this.product = data,
      error: (errors: any) => console.log(errors)
    });
  };

  onSubmit() {
    this.saveProduct();
  }

  saveProduct() {
    this.productService.updateProduct(this.id, this.product).subscribe({
      next: (data) => this.goListProducts(),
      error: (errors: any) => console.log(errors)
    })
  }

  goListProducts() {
    this.router.navigate(['/products']);
  }
}
