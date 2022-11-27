import { Component, Input } from '@angular/core';
import { Word } from 'src/app/models/word';

@Component({
  selector: 'app-word-detail',
  templateUrl: './word-detail.component.html',
})
export class WordDetailComponent {
  @Input() word?: Word;
}
