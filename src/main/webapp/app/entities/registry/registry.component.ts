import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegistry } from 'app/shared/model/registry.model';
import { RegistryService } from './registry.service';
import { RegistryDeleteDialogComponent } from './registry-delete-dialog.component';

@Component({
  selector: 'jhi-registry',
  templateUrl: './registry.component.html',
})
export class RegistryComponent implements OnInit, OnDestroy {
  registries?: IRegistry[];
  eventSubscriber?: Subscription;

  constructor(protected registryService: RegistryService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.registryService.query().subscribe((res: HttpResponse<IRegistry[]>) => (this.registries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRegistries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRegistry): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRegistries(): void {
    this.eventSubscriber = this.eventManager.subscribe('registryListModification', () => this.loadAll());
  }

  delete(registry: IRegistry): void {
    const modalRef = this.modalService.open(RegistryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.registry = registry;
  }
}
