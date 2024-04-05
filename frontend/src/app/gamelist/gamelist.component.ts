import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gamelist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gamelist.component.html',
  styleUrl: './gamelist.component.css'
})
export class GamelistComponent {
  buttons = [
    { label: 'Cactus Canyon', action: () => this.buttonClicked(1) },
    { label: 'No Fear', action: () => this.buttonClicked(2) },
    { label: 'Congo', action: () => this.buttonClicked(3) }
    // Add more button data as needed
  ];

  buttonClicked(buttonNumber: number) {
    // Handle button click logic here
    console.log('Button ' + buttonNumber + ' clicked');
  }
}
