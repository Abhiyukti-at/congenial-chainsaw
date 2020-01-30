import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-formvalidation',
  templateUrl: './formvalidation.component.html',
  styleUrls: ['./formvalidation.component.css']
})
export class FormvalidationComponent implements OnInit {
loginForm: FormGroup;
submitted = false;
invalidLogin = false;
  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
  this.loginForm = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });
  }
  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    if (this.loginForm.controls.email.value === 'nikita@gmail.com' && this.loginForm.controls.password.value === 'nikita') {
      console.log('User is authenticated');
      // this.router.navigate('/Login');
    } else {
      this.invalidLogin = true;
    }

  }

}
