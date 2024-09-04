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
    { label: 'Star Trek -TNG', action: () => this.buttonClicked(1), image: 'startrek.png' },
    { label: 'Deadpool', action: () => this.buttonClicked(2), image: 'deadpool.png' },
    { label: 'Whirlwind', action: () => this.buttonClicked(3), image: 'whirlwind.png' },
    { label: 'Pulp Fiction', action: () => this.buttonClicked(4), image: 'pulpfiction.png' },
    { label: 'Congo', action: () => this.buttonClicked(5), image: 'congo.png' },
    { label: 'The Shadow', action: () => this.buttonClicked(6), image: 'theshadow.png' },
    { label: 'Spectrum', action: () => this.buttonClicked(7), image: 'spectrum.png' },
    { label: 'NYC PINS', action: () => this.buttonClicked(8), image: 'nycpins.png' }

    // Add more button data as needed
  ];

  buttonClicked(buttonNumber: number) {
    // production
    const ipAddress = '192.168.1.100';

    // development
    //const ipAddress = '192.168.1.161';

    console.log('Button ' + buttonNumber + ' clicked');
    if (buttonNumber !== 0) {
      this.http.get(`http://${ipAddress}:8080/hdmi/${buttonNumber}`).subscribe(
          (response) => {
            console.log('HTTP call successful:', response);
          },
          (error) => {
            console.error('HTTP call failed:', error);
          }
      );
    } else {
      console.log('Button number is zero, no HTTP call made.');
    }
  }
}
