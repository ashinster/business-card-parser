import { Component } from '@angular/core';
import { ParseService} from './parse-service.service';
import { ContactInfo } from './contact-info';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Business Card Parser';
  placeholderText = 'Enter Business Card Input';

  documentInput: string;

  contactInfo: ContactInfo;

  constructor(private parseService: ParseService) {

  }

  submit(): void {
    this.parseService.parse(this.documentInput).subscribe(
      (data: ContactInfo) => {
        this.contactInfo = data;
      }
    );
  }

}
