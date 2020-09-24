import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRegistry, Registry } from 'app/shared/model/registry.model';
import { RegistryService } from './registry.service';

@Component({
  selector: 'jhi-registry-update',
  templateUrl: './registry-update.component.html',
})
export class RegistryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    enabled: [],
    url: [],
  });

  constructor(protected registryService: RegistryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ registry }) => {
      this.updateForm(registry);
    });
  }

  updateForm(registry: IRegistry): void {
    this.editForm.patchValue({
      id: registry.id,
      name: registry.name,
      enabled: registry.enabled,
      url: registry.url,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const registry = this.createFromForm();
    if (registry.id !== undefined) {
      this.subscribeToSaveResponse(this.registryService.update(registry));
    } else {
      this.subscribeToSaveResponse(this.registryService.create(registry));
    }
  }

  private createFromForm(): IRegistry {
    return {
      ...new Registry(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      enabled: this.editForm.get(['enabled'])!.value,
      url: this.editForm.get(['url'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegistry>>): void {
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
