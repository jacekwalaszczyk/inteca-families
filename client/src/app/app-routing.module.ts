import { NgModule }               from '@angular/core';
import { RouterModule, Routes }   from '@angular/router';

import { MenuComponent }          from './menu/menu.component';
import { NewFamilyComponent }     from './new-family/new-family.component';
import { FamiliesComponent }      from './families/families.component';
import { FatherDetailComponent }  from './father-detail/father-detail.component';
import { FamilyDetailComponent }  from './family-detail/family-detail.component';
import { ChildrenComponent }      from './children/children.component';
import { ChildDetailComponent }   from './child-detail/child-detail.component';
import { SearchFamiliesComponent } from './search-families/search-families.component';
import { Paths }                   from './models/urls'

const routes: Routes = [
  { path: '', redirectTo: Paths.menu, pathMatch: 'full' },
  { path: Paths.familydetailId, component: FamilyDetailComponent },
  { path: Paths.familydetailIdChild, component: FamilyDetailComponent },
  { path: Paths.fatherdetailId, component: FatherDetailComponent },
  { path: Paths.childdetailPesel, component: ChildDetailComponent },
  { path: Paths.childrenId, component: ChildrenComponent },
  { path: Paths.newfamily, component: NewFamilyComponent },
  { path: Paths.searchfamilies, component: SearchFamiliesComponent },
  { path: Paths.families, component: FamiliesComponent },
  { path: Paths.menu, component: MenuComponent },
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
