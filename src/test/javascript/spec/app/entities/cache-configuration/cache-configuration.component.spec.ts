import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { CacheConfigurationComponent } from 'app/entities/cache-configuration/cache-configuration.component';
import { CacheConfigurationService } from 'app/entities/cache-configuration/cache-configuration.service';
import { CacheConfiguration } from 'app/shared/model/cache-configuration.model';

describe('Component Tests', () => {
  describe('CacheConfiguration Management Component', () => {
    let comp: CacheConfigurationComponent;
    let fixture: ComponentFixture<CacheConfigurationComponent>;
    let service: CacheConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [CacheConfigurationComponent],
      })
        .overrideTemplate(CacheConfigurationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CacheConfigurationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CacheConfigurationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CacheConfiguration(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cacheConfigurations && comp.cacheConfigurations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
