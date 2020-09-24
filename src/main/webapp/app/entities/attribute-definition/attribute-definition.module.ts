import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationaaSharedModule } from 'app/shared/shared.module';
import { AttributeDefinitionComponent } from './attribute-definition.component';
import { AttributeDefinitionDetailComponent } from './attribute-definition-detail.component';
import { AttributeDefinitionUpdateComponent } from './attribute-definition-update.component';
import { AttributeDefinitionDeleteDialogComponent } from './attribute-definition-delete-dialog.component';
import { attributeDefinitionRoute } from './attribute-definition.route';

@NgModule({
  imports: [JhipsterSampleApplicationaaSharedModule, RouterModule.forChild(attributeDefinitionRoute)],
  declarations: [
    AttributeDefinitionComponent,
    AttributeDefinitionDetailComponent,
    AttributeDefinitionUpdateComponent,
    AttributeDefinitionDeleteDialogComponent,
  ],
  entryComponents: [AttributeDefinitionDeleteDialogComponent],
})
export class JhipsterSampleApplicationaaAttributeDefinitionModule {}
