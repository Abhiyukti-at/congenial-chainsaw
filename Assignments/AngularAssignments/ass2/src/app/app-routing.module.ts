import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterFormComponent } from './register-form/register-form.component';
import { FormvalidationComponent } from './formvalidation/formvalidation.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ListUserComponent } from './list-user/list-user.component';


const routes: Routes = [
  {path: 'Login', component: FormvalidationComponent},
  {path: 'Register', component: RegisterFormComponent},
  {path: 'Update', component: EditUserComponent},
  {path: 'Show', component: ListUserComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
