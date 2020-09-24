import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IAttributeDefinition, AttributeDefinition } from 'app/shared/model/attribute-definition.model';
import { AttributeDefinitionService } from './attribute-definition.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IAttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';
import { AttributeAuthorityDefinitionService } from 'app/entities/attribute-authority-definition/attribute-authority-definition.service';

@Component({
  selector: 'jhi-attribute-definition-update',
  templateUrl: './attribute-definition-update.component.html',
})
export class AttributeDefinitionUpdateComponent implements OnInit {
  isSaving = false;
  attributeauthoritydefinitions: IAttributeAuthorityDefinition[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    description: [],
    publicAvalable: [],
    spidLevel: [],
    multivalue: [],
    defaultValues: [],
    consentRequired: [],
    aaCode: [],
    attributeAuthority: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected attributeDefinitionService: AttributeDefinitionService,
    protected attributeAuthorityDefinitionService: AttributeAuthorityDefinitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ attributeDefinition }) => {
      this.updateForm(attributeDefinition);

      this.attributeAuthorityDefinitionService
        .query()
        .subscribe((res: HttpResponse<IAttributeAuthorityDefinition[]>) => (this.attributeauthoritydefinitions = res.body || []));
    });
  }

  updateForm(attributeDefinition: IAttributeDefinition): void {
    this.editForm.patchValue({
      id: attributeDefinition.id,
      code: attributeDefinition.code,
      name: attributeDefinition.name,
      description: attributeDefinition.description,
      publicAvalable: attributeDefinition.publicAvalable,
      spidLevel: attributeDefinition.spidLevel,
      multivalue: attributeDefinition.multivalue,
      defaultValues: attributeDefinition.defaultValues,
      consentRequired: attributeDefinition.consentRequired,
      aaCode: attributeDefinition.aaCode,
      attributeAuthority: attributeDefinition.attributeAuthority,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('jhipsterSampleApplicationaaApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const attributeDefinition = this.createFromForm();
    if (attributeDefinition.id !== undefined) {
      this.subscribeToSaveResponse(this.attributeDefinitionService.update(attributeDefinition));
    } else {
      this.subscribeToSaveResponse(this.attributeDefinitionService.create(attributeDefinition));
    }
  }

  private createFromForm(): IAttributeDefinition {
    return {
      ...new AttributeDefinition(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      publicAvalable: this.editForm.get(['publicAvalable'])!.value,
      spidLevel: this.editForm.get(['spidLevel'])!.value,
      multivalue: this.editForm.get(['multivalue'])!.value,
      defaultValues: this.editForm.get(['defaultValues'])!.value,
      consentRequired: this.editForm.get(['consentRequired'])!.value,
      aaCode: this.editForm.get(['aaCode'])!.value,
      attributeAuthority: this.editForm.get(['attributeAuthority'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAttributeDefinition>>): void {
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

  trackById(index: number, item: IAttributeAuthorityDefinition): any {
    return item.id;
  }
}
