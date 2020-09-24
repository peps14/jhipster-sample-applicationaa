import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { CacheConfigurationDetailComponent } from 'app/entities/cache-configuration/cache-configuration-detail.component';
import { CacheConfiguration } from 'app/shared/model/cache-configuration.model';

describe('Component Tests', () => {
  describe('CacheConfiguration Management Detail Component', () => {
    let comp: CacheConfigurationDetailComponent;
    let fixture: ComponentFixture<CacheConfigurationDetailComponent>;
    const route = ({ data: of({ cacheConfiguration: new CacheConfiguration(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [CacheConfigurationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CacheConfigurationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CacheConfigurationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cacheConfiguration on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cacheConfiguration).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
