import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'registry',
        loadChildren: () => import('./registry/registry.module').then(m => m.JhipsterSampleApplicationaaRegistryModule),
      },
      {
        path: 'attribute-authority-definition',
        loadChildren: () =>
          import('./attribute-authority-definition/attribute-authority-definition.module').then(
            m => m.JhipsterSampleApplicationaaAttributeAuthorityDefinitionModule
          ),
      },
      {
        path: 'attribute-definition',
        loadChildren: () =>
          import('./attribute-definition/attribute-definition.module').then(m => m.JhipsterSampleApplicationaaAttributeDefinitionModule),
      },
      {
        path: 'cache-configuration',
        loadChildren: () =>
          import('./cache-configuration/cache-configuration.module').then(m => m.JhipsterSampleApplicationaaCacheConfigurationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterSampleApplicationaaEntityModule {}
