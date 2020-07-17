import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';
import { AttributeAuthorityDefinitionService } from './attribute-authority-definition.service';
import { AttributeAuthorityDefinitionDeleteDialogComponent } from './attribute-authority-definition-delete-dialog.component';

@Component({
  selector: 'jhi-attribute-authority-definition',
  templateUrl: './attribute-authority-definition.component.html',
})
export class AttributeAuthorityDefinitionComponent implements OnInit, OnDestroy {
  attributeAuthorityDefinitions?: IAttributeAuthorityDefinition[];
  eventSubscriber?: Subscription;

  constructor(
    protected attributeAuthorityDefinitionService: AttributeAuthorityDefinitionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.attributeAuthorityDefinitionService
      .query()
      .subscribe((res: HttpResponse<IAttributeAuthorityDefinition[]>) => (this.attributeAuthorityDefinitions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAttributeAuthorityDefinitions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAttributeAuthorityDefinition): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAttributeAuthorityDefinitions(): void {
    this.eventSubscriber = this.eventManager.subscribe('attributeAuthorityDefinitionListModification', () => this.loadAll());
  }

  delete(attributeAuthorityDefinition: IAttributeAuthorityDefinition): void {
    const modalRef = this.modalService.open(AttributeAuthorityDefinitionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.attributeAuthorityDefinition = attributeAuthorityDefinition;
  }
}
