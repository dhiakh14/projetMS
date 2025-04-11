import { Component, OnInit } from '@angular/core';
import { UserControllerService } from '../../services/services';
import { UserRepresentation } from 'src/app/services/models';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  currentUser: UserRepresentation | null = null;
  isLoading = true;
  errorMessage: string | null = null;

  constructor(private userService: UserControllerService) {}

  ngOnInit(): void {
    this.fetchCurrentUser();
  }

  fetchCurrentUser(): void {
    this.userService.getCurrentUser().subscribe({
      next: (user) => {
        this.currentUser = user;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load user profile';
        this.isLoading = false;
        console.error('Error fetching user:', error);
      }
    });
  }
}