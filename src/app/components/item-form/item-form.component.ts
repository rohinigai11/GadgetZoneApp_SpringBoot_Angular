import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Item } from '../../models/item';
import { ItemService } from '../../services/item.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-item-form',
  standalone: true,
  templateUrl: './item-form.component.html',
  imports: [FormsModule,CommonModule],
})
export class ItemFormComponent {

  item: Item = {
    itemId: 0,
    name: '',
    brandName: '',
    category: '',
    price: 0
  };
  isEditMode = false;

  constructor(
    private service: ItemService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.service.getById(+id).subscribe(item => this.item = item);
    }
  }

  onSubmit() {
    if (this.isEditMode) {
      this.service.update(this.item.itemId!, this.item).subscribe(() => this.router.navigate(['/items']));
    } else {
      this.service.add(this.item).subscribe(() => this.router.navigate(['/items']));
    }
  }
}