// src/app/components/register/register.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { RegistrationService } from '../service/registration.service';
import { User } from './User';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private registrationService: RegistrationService,
    private toastr: ToastrService
  ) {
    this.registerForm = this.fb.group({
      fname:    ['', Validators.required],
      username: ['', [Validators.required, Validators.minLength(4)]],
      address:  ['', Validators.required],
      mob:      ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      city:     ['', Validators.required],
      pcode:    ['', [Validators.required, Validators.pattern(/^\d{6}$/)]]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const user: User = this.registerForm.value;
      this.registrationService.userRegister(user).subscribe({
        next: () => {
          this.toastr.success('Registration successful!', 'Success');
          this.registerForm.reset();
        },
        error: err => {
          this.toastr.error('Registration failed. Please try again.', 'Error');
        }
      });
    } else {
      this.toastr.warning('Please fill all required fields correctly.', 'Warning');
    }
  }
}
