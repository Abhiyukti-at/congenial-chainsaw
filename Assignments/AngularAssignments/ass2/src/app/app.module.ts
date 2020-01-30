import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule,  CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule, MatTableModule, MatButtonModule,
   MatInputModule, MatFormFieldControl, MatCardModule, MatToolbarModule} from '@angular/material';
import { AppRoutingModule } from './app-routing.module';
import {Routes, RouterModule} from '@angular/router';
import { AppComponent } from './app.component';
import { EmpformComponent } from './empform/empform.component';
import { ShowempComponent } from './showemp/showemp.component';
import { FormvalidationComponent } from './formvalidation/formvalidation.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ListUserComponent } from './list-user/list-user.component';


@NgModule({
  declarations: [
    AppComponent,

    EmpformComponent,
    ShowempComponent,
    FormvalidationComponent,
    RegisterFormComponent,
    EditUserComponent,
    ListUserComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatToolbarModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule

  ],
  exports: [
    MatFormFieldModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
