import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { CacheConfigurationUpdateComponent } from 'app/entities/cache-configuration/cache-configuration-update.component';
import { CacheConfigurationService } from 'app/entities/cache-configuration/cache-configuration.service';
import { CacheConfiguration } from 'app/shared/model/cache-configuration.model';

describe('Component Tests', () => {
  describe('CacheConfiguration Management Update Component', () => {
    let comp: CacheConfigurationUpdateComponent;
    let fixture: ComponentFixture<CacheConfigurationUpdateComponent>;
    let service: CacheConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [CacheConfigurationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CacheConfigurationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CacheConfigurationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CacheConfigurationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CacheConfiguration(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CacheConfiguration();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
