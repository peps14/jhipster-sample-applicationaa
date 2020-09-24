import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICacheConfiguration } from 'app/shared/model/cache-configuration.model';

@Component({
  selector: 'jhi-cache-configuration-detail',
  templateUrl: './cache-configuration-detail.component.html',
})
export class CacheConfigurationDetailComponent implements OnInit {
  cacheConfiguration: ICacheConfiguration | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cacheConfiguration }) => (this.cacheConfiguration = cacheConfiguration));
  }

  previousState(): void {
    window.history.back();
  }
}
