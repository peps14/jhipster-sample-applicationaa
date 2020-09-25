import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CacheConfigurationService } from 'app/entities/cache-configuration/cache-configuration.service';
import { ICacheConfiguration, CacheConfiguration } from 'app/shared/model/cache-configuration.model';
import { CacheType } from 'app/shared/model/enumerations/cache-type.model';

describe('Service Tests', () => {
  describe('CacheConfiguration Service', () => {
    let injector: TestBed;
    let service: CacheConfigurationService;
    let httpMock: HttpTestingController;
    let elemDefault: ICacheConfiguration;
    let expectedResult: ICacheConfiguration | ICacheConfiguration[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CacheConfigurationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CacheConfiguration(0, false, false, false, 'AAAAAAA', 0, 0, false, 'AAAAAAA', 0, CacheType.ATTRIBUTE_GLOBAL);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CacheConfiguration', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CacheConfiguration()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CacheConfiguration', () => {
        const returnedFromService = Object.assign(
          {
            enabled: true,
            forceDefault: true,
            autoRefresh: true,
            authoRefreshCronExpression: 'BBBBBB',
            authoRefreshBatchSize: 1,
            authoRefreshBatchInterval: 1,
            autoClean: true,
            autoCleanCronExpression: 'BBBBBB',
            duration: 1,
            cacheType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CacheConfiguration', () => {
        const returnedFromService = Object.assign(
          {
            enabled: true,
            forceDefault: true,
            autoRefresh: true,
            authoRefreshCronExpression: 'BBBBBB',
            authoRefreshBatchSize: 1,
            authoRefreshBatchInterval: 1,
            autoClean: true,
            autoCleanCronExpression: 'BBBBBB',
            duration: 1,
            cacheType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CacheConfiguration', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
