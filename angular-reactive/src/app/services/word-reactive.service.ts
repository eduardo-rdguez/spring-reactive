import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Word } from '../models/word';

@Injectable()
export class WordReactiveService {
  url: string = 'http://localhost:8080/api/v2/words';

  getWordsReactive(page?: number, size?: number): Observable<Word> {
    return new Observable<Word>((observer) => {
      let url = this.url;
      if (page && size) {
        url += '?page=' + page + '&size=' + size;
      }

      let eventSource = new EventSource(url);
      eventSource.onmessage = (event) => {
        let json = JSON.parse(event.data);
        observer.next(new Word(json.id, json.value));
      };
      eventSource.onerror = (error) => {
        if (eventSource.readyState === 0) {
          eventSource.close();
          observer.complete();
        } else {
          observer.error('Event error: ' + error)
        }
      };
    });
  }
}
