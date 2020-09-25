import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICacheConfiguration, CacheConfiguration } from 'app/shared/model/cache-configuration.model';
import { CacheConfigurationService } from './cache-configuration.service';

@Component({
  selector: 'jhi-cache-configuration-update',
  templateUrl: './cache-configuration-update.component.html',
})
export class CacheConfigurationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    enabled: [],
    forceDefault: [],
    autoRefresh: [],
    authoRefreshCronExpression: [],
    authoRefreshBatchSize: [],
    authoRefreshBatchInterval: [],
    autoClean: [],
    duration: [],
    cacheType: [],
  });

  constructor(
    protected cacheConfigurationService: CacheConfigurationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cacheConfiguration }) => {
      this.updateForm(cacheConfiguration);
    });
  }

  updateForm(cacheConfiguration: ICacheConfiguration): void {
    this.editForm.patchValue({
      id: cacheConfiguration.id,
      enabled: cacheConfiguration.enabled,
      forceDefault: cacheConfiguration.forceDefault,
      autoRefresh: cacheConfiguration.autoRefresh,
      authoRefreshCronExpression: cacheConfiguration.authoRefreshCronExpression,
      authoRefreshBatchSize: cacheConfiguration.authoRefreshBatchSize,
      authoRefreshBatchInterval: cacheConfiguration.authoRefreshBatchInterval,
      autoClean: cacheConfiguration.autoClean,
      duration: cacheConfiguration.duration,
      cacheType: cacheConfiguration.cacheType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cacheConfiguration = this.createFromForm();
    if (cacheConfiguration.id !== undefined) {
      this.subscribeToSaveResponse(this.cacheConfigurationService.update(cacheConfiguration));
    } else {
      this.subscribeToSaveResponse(this.cacheConfigurationService.create(cacheConfiguration));
    }
  }

  private createFromForm(): ICacheConfiguration {
    return {
      ...new CacheConfiguration(),
      id: this.editForm.get(['id'])!.value,
      enabled: this.editForm.get(['enabled'])!.value,
      forceDefault: this.editForm.get(['forceDefault'])!.value,
      autoRefresh: this.editForm.get(['autoRefresh'])!.value,
      authoRefreshCronExpression: this.editForm.get(['authoRefreshCronExpression'])!.value,
      authoRefreshBatchSize: this.editForm.get(['authoRefreshBatchSize'])!.value,
      authoRefreshBatchInterval: this.editForm.get(['authoRefreshBatchInterval'])!.value,
      autoClean: this.editForm.get(['autoClean'])!.value,
      duration: this.editForm.get(['duration'])!.value,
      cacheType: this.editForm.get(['cacheType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICacheConfiguration>>): void {
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
