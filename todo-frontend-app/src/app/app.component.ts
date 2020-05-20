import {Component, OnInit} from '@angular/core';
import {TodoDataService} from './_services/todo-data.service';
import { User , Todo} from './_models';
import {AccountService} from './_services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [TodoDataService]
})
export class AppComponent implements OnInit {
  title = 'todo-frontend-app';
  user: User;
  todos: Todo[] = [];

  constructor(private todoDataService: TodoDataService, private accountService: AccountService) {
    this.accountService.user.subscribe(x => this.user = x);
  }


  ngOnInit(): void {
    
  }


  logout() {
    this.accountService.logout();
  }

}
