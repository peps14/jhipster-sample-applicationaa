import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAttributeDefinition } from 'app/shared/model/attribute-definition.model';
import { AttributeDefinitionService } from './attribute-definition.service';
import { AttributeDefinitionDeleteDialogComponent } from './attribute-definition-delete-dialog.component';

@Component({
  selector: 'jhi-attribute-definition',
  templateUrl: './attribute-definition.component.html',
})
export class AttributeDefinitionComponent implements OnInit, OnDestroy {
  attributeDefinitions?: IAttributeDefinition[];
  eventSubscriber?: Subscription;

  constructor(
    protected attributeDefinitionService: AttributeDefinitionService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.attributeDefinitionService
      .query()
      .subscribe((res: HttpResponse<IAttributeDefinition[]>) => (this.attributeDefinitions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAttributeDefinitions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAttributeDefinition): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInAttributeDefinitions(): void {
    this.eventSubscriber = this.eventManager.subscribe('attributeDefinitionListModification', () => this.loadAll());
  }

  delete(attributeDefinition: IAttributeDefinition): void {
    const modalRef = this.modalService.open(AttributeDefinitionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.attributeDefinition = attributeDefinition;
  }
}
