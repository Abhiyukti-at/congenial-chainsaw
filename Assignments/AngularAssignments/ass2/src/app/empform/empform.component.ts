import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-empform',
  templateUrl: './empform.component.html',
  styleUrls: ['./empform.component.css']
})
export class EmpformComponent implements OnInit {
@Output() details = new EventEmitter ();
  constructor() { }
  EmpId = '';
  EmpName = '';
  EmpSalary = '';
  EmpDepart = '';

  ngOnInit() {
  }
  onSubmit() {
    const data = {
      id: this.EmpId,
      name: this.EmpName,
      salary: this.EmpSalary,
      department: this.EmpDepart

    };
    this.details.emit(data);
  }
}
