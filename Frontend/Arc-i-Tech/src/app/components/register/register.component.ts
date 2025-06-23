import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';
import { CommonModule } from '@angular/common';
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
  submitted = false;
  message = '';
  alertType = '';

registrationForm!: FormGroup<{
  fname: FormControl<string | null>;
  username: FormControl<string | null>;
  email: FormControl<string | null>;
  password: FormControl<string | null>;
  confirmpassword: FormControl<string | null>;
  address: FormControl<string | null>;
  mob: FormControl<string | null>;
  city: FormControl<string | null>;
  pcode: FormControl<string | null>;
}>;


  constructor(private fb: FormBuilder, private registrationService: RegistrationService) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group(
      {
        fname: this.fb.control('', Validators.required),
        username: this.fb.control('', Validators.required),
        email: this.fb.control('', [Validators.required, Validators.email]),
        password: this.fb.control('', [Validators.required, Validators.minLength(6)]),
        confirmpassword: this.fb.control('', Validators.required),
        address: this.fb.control('', Validators.required),
        mob: this.fb.control('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
        city: this.fb.control('', Validators.required),
        pcode: this.fb.control('', [Validators.required, Validators.pattern('^[0-9]{4,6}$')])
      },
      { validators: this.passwordsMatchValidator }
    );
  }

  get f() {
    return this.registrationForm.controls;
  }

  passwordsMatchValidator(group: AbstractControl): ValidationErrors | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmpassword')?.value;
    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registrationForm.invalid) {
      return;
    }

const user = this.registrationForm.value as User;

    this.registrationService.userRegister(user).subscribe({
      next: () => {
        this.message = 'Registration successful!';
        this.alertType = 'alert-success';
        this.registrationForm.reset();
        this.submitted = false;
      },
      error: () => {
        this.message = 'Registration failed. Please try again.';
        this.alertType = 'alert-danger';
      }
    });
  }
}
