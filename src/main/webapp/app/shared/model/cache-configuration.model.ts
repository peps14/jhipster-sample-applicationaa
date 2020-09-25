import { CacheType } from 'app/shared/model/enumerations/cache-type.model';

export interface ICacheConfiguration {
  id?: number;
  enabled?: boolean;
  forceDefault?: boolean;
  autoRefresh?: boolean;
  authoRefreshCronExpression?: string;
  authoRefreshBatchSize?: number;
  authoRefreshBatchInterval?: number;
  autoClean?: boolean;
  autoCleanCronExpression?: string;
  duration?: number;
  cacheType?: CacheType;
}

export class CacheConfiguration implements ICacheConfiguration {
  constructor(
    public id?: number,
    public enabled?: boolean,
    public forceDefault?: boolean,
    public autoRefresh?: boolean,
    public authoRefreshCronExpression?: string,
    public authoRefreshBatchSize?: number,
    public authoRefreshBatchInterval?: number,
    public autoClean?: boolean,
    public autoCleanCronExpression?: string,
    public duration?: number,
    public cacheType?: CacheType
  ) {
    this.enabled = this.enabled || false;
    this.forceDefault = this.forceDefault || false;
    this.autoRefresh = this.autoRefresh || false;
    this.autoClean = this.autoClean || false;
  }
}
