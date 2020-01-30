import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
registerForm: FormGroup;
  constructor(private formBuilder: FormBuilder ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', [ Validators.required]],
      number: ['', [ Validators.required, Validators.maxLength(10)]],
      email: ['', [ Validators.email]],
      password: ['', [Validators.minLength(8),
        Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]],
});
  }
    onsubmit() {
      console.log('Inside Submission');
    }
  }


