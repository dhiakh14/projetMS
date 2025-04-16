import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Type } from 'src/app/common/livrable';
import { LivrableService } from 'src/app/serviceLiv/livrable.service';


@Component({
  selector: 'app-add-livrable',
  templateUrl: './add-livrable.component.html',
  styleUrls: ['./add-livrable.component.css']
})
export class AddLivrableComponent  {
  livrForm: FormGroup;
  types = Object.values(Type);

  constructor(
    private fb: FormBuilder,
    private livrableService: LivrableService,
    private router: Router
  ) {
    // Initialize the form group in the constructor
    this.livrForm = this.fb.group({
      title: ['', Validators.required],
      projectName: ['', Validators.required],
      type: ['', Validators.required],
      description: ['', Validators.required],
      due_date: ['', Validators.required],
    });
  }

  

  onSubmit(): void {
    if (this.livrForm.valid) {
      const newLivrable = this.livrForm.value;

      console.log(newLivrable.title);
      console.log(newLivrable.projectName);
      console.log(newLivrable.type);
      console.log(newLivrable.description);
      console.log(newLivrable.due_date);
      
      console.log(newLivrable);

      this.livrableService.addLivrable(newLivrable).subscribe((response) => {
        // You can handle success or reset the form here
        console.log('Livrable added successfully:', response);
        this.router.navigate(['/livrables']);
      });
    }
  }
}
