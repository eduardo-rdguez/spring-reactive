import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Word } from '../models/word';

@Injectable()
export class WordBlockingService {
  url: string = 'http://localhost:8080/api/v1/words';

  constructor(private http: HttpClient) {}

  getWordsBlocking(page?: number, size?: number): Observable<Array<Word>> {
    let url = this.url;
    if (page && size) {
      url += '?page=' + page + '&size=' + size;
    }
    return this.http.get<Array<Word>>(url);
  }
}
