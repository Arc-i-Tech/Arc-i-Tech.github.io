import { Component } from '@angular/core';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { RouterOutlet } from "@angular/router";
import { HeaderComponent } from "../header/header.component";

@Component({
  selector: 'app-main-panel',
  standalone: true,
  imports: [SidebarComponent, RouterOutlet, HeaderComponent],
  templateUrl: './main-panel.component.html',
})
export class MainPanelComponent {

}
