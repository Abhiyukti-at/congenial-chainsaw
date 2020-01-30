import { Component, OnInit, Input } from '@angular/core';
import { MatTableModule } from '@angular/material';
export interface Emp {
  id: string;
  name: string;
  salary: string;
  department: string;
}



@Component({
  selector: 'app-showemp',
  templateUrl: './showemp.component.html',
  styleUrls: ['./showemp.component.css']
})
export class ShowempComponent implements OnInit {
@Input() details = [{id: '101', name: 'abc', salary: '21321', department: 'abc'},
{id: '101', name: 'abc', salary: '21321', department: 'abc'}];
ELEMENT_DATA: Emp[] = this.details;
displayedColumns: string [] = ['id', 'name', 'salary', 'department'];
dataSource = this.ELEMENT_DATA;
  constructor() { }
  ngOnInit() {
  }
}
