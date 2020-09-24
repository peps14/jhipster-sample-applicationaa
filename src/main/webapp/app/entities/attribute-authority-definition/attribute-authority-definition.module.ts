import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationaaSharedModule } from 'app/shared/shared.module';
import { AttributeAuthorityDefinitionComponent } from './attribute-authority-definition.component';
import { AttributeAuthorityDefinitionDetailComponent } from './attribute-authority-definition-detail.component';
import { AttributeAuthorityDefinitionUpdateComponent } from './attribute-authority-definition-update.component';
import { AttributeAuthorityDefinitionDeleteDialogComponent } from './attribute-authority-definition-delete-dialog.component';
import { attributeAuthorityDefinitionRoute } from './attribute-authority-definition.route';

@NgModule({
  imports: [JhipsterSampleApplicationaaSharedModule, RouterModule.forChild(attributeAuthorityDefinitionRoute)],
  declarations: [
    AttributeAuthorityDefinitionComponent,
    AttributeAuthorityDefinitionDetailComponent,
    AttributeAuthorityDefinitionUpdateComponent,
    AttributeAuthorityDefinitionDeleteDialogComponent,
  ],
  entryComponents: [AttributeAuthorityDefinitionDeleteDialogComponent],
})
export class JhipsterSampleApplicationaaAttributeAuthorityDefinitionModule {}
