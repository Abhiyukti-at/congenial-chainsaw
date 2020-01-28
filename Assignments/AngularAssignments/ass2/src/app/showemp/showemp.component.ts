import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-showemp',
  templateUrl: './showemp.component.html',
  styleUrls: ['./showemp.component.css']
})
export class ShowempComponent implements OnInit {
@Input() details = [];
  constructor() { }

  ngOnInit() {
  }

}
