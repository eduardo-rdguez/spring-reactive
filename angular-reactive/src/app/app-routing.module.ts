import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WordTableComponent } from './components/words/word-table/word-table.component';

const routes: Routes = [
  { path: 'words', component: WordTableComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
