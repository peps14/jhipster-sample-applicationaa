import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAttributeAuthorityDefinition, AttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';
import { AttributeAuthorityDefinitionService } from './attribute-authority-definition.service';

@Component({
  selector: 'jhi-attribute-authority-definition-update',
  templateUrl: './attribute-authority-definition-update.component.html',
})
export class AttributeAuthorityDefinitionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    description: [],
    enabled: [],
    spidLevel: [],
    attributesUrl: [],
    consentRequired: [],
    consentUrl: [],
    checkConsentUrl: [],
    errorRedirectUrl: [],
    spidSchemaVersion: [],
    apiVersion: [],
    testUrl: [],
  });

  constructor(
    protected attributeAuthorityDefinitionService: AttributeAuthorityDefinitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ attributeAuthorityDefinition }) => {
      this.updateForm(attributeAuthorityDefinition);
    });
  }

  updateForm(attributeAuthorityDefinition: IAttributeAuthorityDefinition): void {
    this.editForm.patchValue({
      id: attributeAuthorityDefinition.id,
      code: attributeAuthorityDefinition.code,
      name: attributeAuthorityDefinition.name,
      description: attributeAuthorityDefinition.description,
      enabled: attributeAuthorityDefinition.enabled,
      spidLevel: attributeAuthorityDefinition.spidLevel,
      attributesUrl: attributeAuthorityDefinition.attributesUrl,
      consentRequired: attributeAuthorityDefinition.consentRequired,
      consentUrl: attributeAuthorityDefinition.consentUrl,
      checkConsentUrl: attributeAuthorityDefinition.checkConsentUrl,
      errorRedirectUrl: attributeAuthorityDefinition.errorRedirectUrl,
      spidSchemaVersion: attributeAuthorityDefinition.spidSchemaVersion,
      apiVersion: attributeAuthorityDefinition.apiVersion,
      testUrl: attributeAuthorityDefinition.testUrl,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const attributeAuthorityDefinition = this.createFromForm();
    if (attributeAuthorityDefinition.id !== undefined) {
      this.subscribeToSaveResponse(this.attributeAuthorityDefinitionService.update(attributeAuthorityDefinition));
    } else {
      this.subscribeToSaveResponse(this.attributeAuthorityDefinitionService.create(attributeAuthorityDefinition));
    }
  }

  private createFromForm(): IAttributeAuthorityDefinition {
    return {
      ...new AttributeAuthorityDefinition(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      enabled: this.editForm.get(['enabled'])!.value,
      spidLevel: this.editForm.get(['spidLevel'])!.value,
      attributesUrl: this.editForm.get(['attributesUrl'])!.value,
      consentRequired: this.editForm.get(['consentRequired'])!.value,
      consentUrl: this.editForm.get(['consentUrl'])!.value,
      checkConsentUrl: this.editForm.get(['checkConsentUrl'])!.value,
      errorRedirectUrl: this.editForm.get(['errorRedirectUrl'])!.value,
      spidSchemaVersion: this.editForm.get(['spidSchemaVersion'])!.value,
      apiVersion: this.editForm.get(['apiVersion'])!.value,
      testUrl: this.editForm.get(['testUrl'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAttributeAuthorityDefinition>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
