import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {environment} from '../environments/environment';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TodoListComponent} from './todo-list/todo-list.component';
import {TodoListFooterComponent} from './todo-list-footer/todo-list-footer.component';
import {TodoListHeaderComponent} from './todo-list-header/todo-list-header.component';
import {TodoListItemComponent} from './todo-list-item/todo-list-item.component';
import {FormsModule} from '@angular/forms';
import {ApiService} from './shared/api.service';
import {TodoDataService} from './shared/todo-data.service';

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
    TodoListFooterComponent,
    TodoListHeaderComponent,
    TodoListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [TodoDataService, ApiService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
