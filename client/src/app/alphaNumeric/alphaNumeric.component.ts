import {Component, Input, OnInit} from '@angular/core';
import { AlphaNumericService } from '../shared';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';

import {catchError, map, startWith, tap} from 'rxjs/operators';
import {Observable} from "rxjs";


@Component({
  selector: 'app-alphaNumeric-list',
  templateUrl: './alphaNumeric.component.html',
  styleUrls: ['./alphaNumeric.component.css'],
  providers: [AlphaNumericService]
})
export class AlphaNumericListComponent implements OnInit {
  @Input() phoneNumber =  '';
  p: number = 1;
  pageSize: number = 25;
  totalResult: number =  0;
  result: Observable<any>;

  angForm: FormGroup;
  loading: boolean;
  showResult = false;
  showError = false;


  constructor(private alphaNumericService: AlphaNumericService, private fb: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.angForm = this.fb.group({
      phoneNumber: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.showResult = false;
  }

  getPage(page: number) {
    this.showResult = true;
    this.showError = false;
    this.loading = true;
    const start = (page - 1) * this.pageSize;
    this.result = this.alphaNumericService.getPageResults(this.phoneNumber, start, this.pageSize).pipe(
      tap(res => {
        this.totalResult = res.totalCount;
        this.p = page;
        this.loading = false;
      }),
      map(res => res.results),
      catchError(error => {
        console.log('Caught search error the right way!');
        this.showResult = false;
        this.showError = true;
        return Observable.of({ results: null });
      })
    );
  }
}
