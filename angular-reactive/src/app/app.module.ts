import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WordDetailComponent } from './components/words/word-detail/word-detail.component';
import { WordTableComponent } from './components/words/word-table/word-table.component';
import { WordBlockingService } from './services/word-blocking.service';
import { WordReactiveService } from './services/word-reactive.service';

@NgModule({
  declarations: [AppComponent, WordTableComponent, WordDetailComponent],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [WordBlockingService, WordReactiveService],
  bootstrap: [AppComponent],
})
export class AppModule {}
