import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserControllerService } from 'src/app/services/services';
import { TokenService } from 'src/app/token/token.service';
import { MatDialog } from '@angular/material/dialog';
import { ForgotPasswordDialogComponent } from '../forgot-password-dialog/forgot-password-dialog.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserControllerService,
    private tokenService: TokenService,
    private router: Router,
    private dialog: MatDialog
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = null;

    const credentials = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    };

    this.userService.login({ body: credentials }).subscribe({
      next: (response: any) => {
        if (response.access_token) {
          this.tokenService.token = response.access_token;
          this.router.navigate(['/profile']);
        }
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.status === 401 
          ? 'Invalid username or password' 
          : 'An error occurred during login';
      }
    });
  }

  openForgotPasswordDialog(): void {
    const dialogRef = this.dialog.open(ForgotPasswordDialogComponent, {
      width: '400px',
      data: { email: this.loginForm.value.username }
    });

    dialogRef.afterClosed().subscribe(email => {
      if (email) {
        this.userService.forgotpassword({ username: email }).subscribe({
          next: () => {
            this.errorMessage = null;
            alert('Password reset instructions sent to ' + email);
          },
          error: (error) => {
            this.errorMessage = error.status === 404
              ? 'No account found with this email address'
              : 'Failed to send reset instructions. Please try again later.';
          }
        });
      }
    });
  }
}