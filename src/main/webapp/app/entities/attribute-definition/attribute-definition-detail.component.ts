import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAttributeDefinition } from 'app/shared/model/attribute-definition.model';

@Component({
  selector: 'jhi-attribute-definition-detail',
  templateUrl: './attribute-definition-detail.component.html',
})
export class AttributeDefinitionDetailComponent implements OnInit {
  attributeDefinition: IAttributeDefinition | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ attributeDefinition }) => (this.attributeDefinition = attributeDefinition));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
