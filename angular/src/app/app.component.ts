import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular';

  constructor() {
    this.loadScript('assets/vendor/bootstrap/dist/js/bootstrap.bundle.min.js');
    this.loadScript('assets/vendor/purecounterjs/dist/purecounter_vanilla.js');
    this.loadScript('assets/vendor/apexcharts/js/apexcharts.min.js');
    this.loadScript('assets/vendor/overlay-scrollbar/js/overlayscrollbars.min.js');
    this.loadScript('assets/vendor/aos/aos.js');

    this.loadScript('assets/js/functions.js');
  }


  loadScript(src: string) {
    const script = document.createElement('script');
    script.src = src;
    script.async = true;
    document.head.appendChild(script);
  }
}
