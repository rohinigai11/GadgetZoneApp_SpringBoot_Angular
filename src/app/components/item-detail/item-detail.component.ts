import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Item } from '../../models/item';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../../services/item.service';

@Component({
  selector: 'app-item-detail',
  standalone: true,
  templateUrl: './item-detail.component.html',
  imports: [CommonModule],
})
export class ItemDetailComponent implements OnInit{

  item?: Item;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private itemService: ItemService
  ){}

  ngOnInit(): void {
      const id = this.route.snapshot.paramMap.get('id');
      if(id){
        this.itemService.getById(+id).subscribe({
          next: (data) => this.item = data,
          error: () => this.router.navigate(['/items'])
        });
      }
  }

}
