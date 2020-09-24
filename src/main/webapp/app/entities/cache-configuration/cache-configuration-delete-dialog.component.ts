import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICacheConfiguration } from 'app/shared/model/cache-configuration.model';
import { CacheConfigurationService } from './cache-configuration.service';

@Component({
  templateUrl: './cache-configuration-delete-dialog.component.html',
})
export class CacheConfigurationDeleteDialogComponent {
  cacheConfiguration?: ICacheConfiguration;

  constructor(
    protected cacheConfigurationService: CacheConfigurationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cacheConfigurationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cacheConfigurationListModification');
      this.activeModal.close();
    });
  }
}
