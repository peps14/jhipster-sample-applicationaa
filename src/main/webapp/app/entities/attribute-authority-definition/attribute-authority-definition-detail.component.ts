import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

@Component({
  selector: 'jhi-attribute-authority-definition-detail',
  templateUrl: './attribute-authority-definition-detail.component.html',
})
export class AttributeAuthorityDefinitionDetailComponent implements OnInit {
  attributeAuthorityDefinition: IAttributeAuthorityDefinition | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ attributeAuthorityDefinition }) => (this.attributeAuthorityDefinition = attributeAuthorityDefinition)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
