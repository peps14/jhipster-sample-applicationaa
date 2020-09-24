import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICacheConfiguration } from 'app/shared/model/cache-configuration.model';
import { CacheConfigurationService } from './cache-configuration.service';
import { CacheConfigurationDeleteDialogComponent } from './cache-configuration-delete-dialog.component';

@Component({
  selector: 'jhi-cache-configuration',
  templateUrl: './cache-configuration.component.html',
})
export class CacheConfigurationComponent implements OnInit, OnDestroy {
  cacheConfigurations?: ICacheConfiguration[];
  eventSubscriber?: Subscription;

  constructor(
    protected cacheConfigurationService: CacheConfigurationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.cacheConfigurationService
      .query()
      .subscribe((res: HttpResponse<ICacheConfiguration[]>) => (this.cacheConfigurations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCacheConfigurations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICacheConfiguration): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCacheConfigurations(): void {
    this.eventSubscriber = this.eventManager.subscribe('cacheConfigurationListModification', () => this.loadAll());
  }

  delete(cacheConfiguration: ICacheConfiguration): void {
    const modalRef = this.modalService.open(CacheConfigurationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cacheConfiguration = cacheConfiguration;
  }
}
