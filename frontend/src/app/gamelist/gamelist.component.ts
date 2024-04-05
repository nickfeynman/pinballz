import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-gamelist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gamelist.component.html',
  styleUrl: './gamelist.component.css'
})
export class GamelistComponent {

  constructor(private http: HttpClient) {}

  buttons = [
    { label: 'Star Trek -TNG', action: () => this.buttonClicked(1) },
    { label: 'Deadpool', action: () => this.buttonClicked(2) },
    { label: 'Whirlwind', action: () => this.buttonClicked(3) },
    { label: 'Pulp Fiction', action: () => this.buttonClicked(4) },
    { label: 'Congo', action: () => this.buttonClicked(5) },
    { label: 'The Shadow', action: () => this.buttonClicked(6) },
    { label: 'Spectrum', action: () => this.buttonClicked(7) }

    // Add more button data as needed
  ];

  buttonClicked(buttonNumber: number) {
    // Handle button click logic here
    console.log('Button ' + buttonNumber + ' clicked');
    this.http.get(`http://192.168.1.100:8080/hdmi/${buttonNumber}`).subscribe(
        (response) => {
          console.log('HTTP call successful:', response);
        },
        (error) => {
          console.error('HTTP call failed:', error);
        }
    );
  }
}
