import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';
import { AttributeAuthorityDefinitionService } from './attribute-authority-definition.service';

@Component({
  templateUrl: './attribute-authority-definition-delete-dialog.component.html',
})
export class AttributeAuthorityDefinitionDeleteDialogComponent {
  attributeAuthorityDefinition?: IAttributeAuthorityDefinition;

  constructor(
    protected attributeAuthorityDefinitionService: AttributeAuthorityDefinitionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.attributeAuthorityDefinitionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('attributeAuthorityDefinitionListModification');
      this.activeModal.close();
    });
  }
}
