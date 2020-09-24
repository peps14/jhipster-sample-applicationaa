import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationaaSharedModule } from 'app/shared/shared.module';
import { CacheConfigurationComponent } from './cache-configuration.component';
import { CacheConfigurationDetailComponent } from './cache-configuration-detail.component';
import { CacheConfigurationUpdateComponent } from './cache-configuration-update.component';
import { CacheConfigurationDeleteDialogComponent } from './cache-configuration-delete-dialog.component';
import { cacheConfigurationRoute } from './cache-configuration.route';

@NgModule({
  imports: [JhipsterSampleApplicationaaSharedModule, RouterModule.forChild(cacheConfigurationRoute)],
  declarations: [
    CacheConfigurationComponent,
    CacheConfigurationDetailComponent,
    CacheConfigurationUpdateComponent,
    CacheConfigurationDeleteDialogComponent,
  ],
  entryComponents: [CacheConfigurationDeleteDialogComponent],
})
export class JhipsterSampleApplicationaaCacheConfigurationModule {}
