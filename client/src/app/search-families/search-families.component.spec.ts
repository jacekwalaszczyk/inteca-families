import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchFamiliesComponent } from './search-families.component';

describe('SearchFamiliesComponent', () => {
  let component: SearchFamiliesComponent;
  let fixture: ComponentFixture<SearchFamiliesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchFamiliesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchFamiliesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
