import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent }          from './app/app.component';

import { provideHttpClient }     from '@angular/common/http';
import { provideToastr }            from 'ngx-toastr';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    provideToastr({
      positionClass: 'toast-top-right',
      timeOut: 3000,
      closeButton: true,
      progressBar: true,
    }),
  ],
});
