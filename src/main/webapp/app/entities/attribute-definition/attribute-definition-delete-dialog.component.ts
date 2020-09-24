import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttributeDefinition } from 'app/shared/model/attribute-definition.model';
import { AttributeDefinitionService } from './attribute-definition.service';

@Component({
  templateUrl: './attribute-definition-delete-dialog.component.html',
})
export class AttributeDefinitionDeleteDialogComponent {
  attributeDefinition?: IAttributeDefinition;

  constructor(
    protected attributeDefinitionService: AttributeDefinitionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.attributeDefinitionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('attributeDefinitionListModification');
      this.activeModal.close();
    });
  }
}
