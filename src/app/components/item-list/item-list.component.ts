import { Component } from '@angular/core';
import { Item } from '../../models/item';
import { ItemService } from '../../services/item.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-item-list',
  standalone: true,
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.css',
  imports: [CommonModule, RouterModule],
})
export class ItemListComponent {

  item: Item[] = [];

  constructor(private service: ItemService, private router: Router) {}

  ngOnInit(): void {
    this.loadItems();
  }

  loadItems(): void {
    this.service.getAll().subscribe({
      next: (data) => {
        this.item = data;
      },
      error: (err) => {
        console.error('Error loading items', err);
      }
    });
  }

  deleteItem(id: number): void {
    if (confirm('Are you sure you want to delete this item?')) {
      this.service.delete(id).subscribe({
        next: () => {
          this.loadItems();
        },
        error: (err) => {
          console.error('Error deleting item', err);
        }
      });
    }
  }
}
