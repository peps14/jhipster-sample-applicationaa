import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationaaSharedModule } from 'app/shared/shared.module';
import { RegistryComponent } from './registry.component';
import { RegistryDetailComponent } from './registry-detail.component';
import { RegistryUpdateComponent } from './registry-update.component';
import { RegistryDeleteDialogComponent } from './registry-delete-dialog.component';
import { registryRoute } from './registry.route';

@NgModule({
  imports: [JhipsterSampleApplicationaaSharedModule, RouterModule.forChild(registryRoute)],
  declarations: [RegistryComponent, RegistryDetailComponent, RegistryUpdateComponent, RegistryDeleteDialogComponent],
  entryComponents: [RegistryDeleteDialogComponent],
})
export class JhipsterSampleApplicationaaRegistryModule {}
