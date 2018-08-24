import { Component } from '@angular/core';
import { Paths} from './models/urls'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'The families archive';
  menuPath = Paths.menu;
}
