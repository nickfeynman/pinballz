import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { GamelistComponent } from './gamelist/gamelist.component';
import {AsyncPipe} from "@angular/common";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [GamelistComponent]
})
export class AppComponent {
  constructor(private httpClient: HttpClient) {
  }

  serverMessage = this.httpClient.get<{message: string}>("api/message");
}


