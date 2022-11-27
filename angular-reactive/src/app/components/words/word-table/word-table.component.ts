import { ChangeDetectorRef, Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Word } from 'src/app/models/word';
import { WordBlockingService } from 'src/app/services/word-blocking.service';
import { WordReactiveService } from 'src/app/services/word-reactive.service';

@Component({
  selector: 'app-word-table',
  providers: [WordBlockingService, WordReactiveService],
  templateUrl: './word-table.component.html',
})
export class WordTableComponent {
  wordArray: Word[] = [];
  selectedWord?: Word;
  mode: string;
  page: number;
  size: number;

  constructor(
    private wordBlockingService: WordBlockingService,
    private wordReactiveService: WordReactiveService,
    private changeDetectorRef: ChangeDetectorRef
  ) {
    this.mode = 'reactive';
    this.page = 1;
    this.size = 50;
  }

  resetData() {
    this.wordArray = [];
  }

  onSelect(word: Word): void {
    this.selectedWord = word;
    this.changeDetectorRef.detectChanges();
  }

  requestWordsReactive(): void {
    this.resetData();

    const wordObservable: Observable<Word> =
      this.wordReactiveService.getWordsReactive(this.page, this.size);

    wordObservable.subscribe((word) => {
      this.wordArray.push(word);
      this.changeDetectorRef.detectChanges();
    });
  }

  requestWordsBlocking(): void {
    this.resetData();

    this.wordBlockingService
      .getWordsBlocking(this.page, this.size)
      .subscribe((words) => (this.wordArray = words));
  }
}
