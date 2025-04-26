import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItemListComponent } from './components/item-list/item-list.component';
import { ItemFormComponent } from './components/item-form/item-form.component';
import { ItemDetailComponent } from './components/item-detail/item-detail.component';

const routes: Routes = [
  {path: '', redirectTo: 'items', pathMatch:'full'},
  {path: 'items', component: ItemListComponent},
  {path: 'items/add', component: ItemFormComponent},
  {path: 'items/edit/:id', component: ItemFormComponent},
  {path: 'items/:id', component: ItemDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
