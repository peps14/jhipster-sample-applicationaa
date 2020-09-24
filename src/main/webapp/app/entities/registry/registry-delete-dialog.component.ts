import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRegistry } from 'app/shared/model/registry.model';
import { RegistryService } from './registry.service';

@Component({
  templateUrl: './registry-delete-dialog.component.html',
})
export class RegistryDeleteDialogComponent {
  registry?: IRegistry;

  constructor(protected registryService: RegistryService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.registryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('registryListModification');
      this.activeModal.close();
    });
  }
}
