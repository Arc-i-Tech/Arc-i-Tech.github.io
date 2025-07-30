import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MainPanelComponent } from "./dashboard/main-panel/main-panel.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MainPanelComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Arc-i-Tech';
}
