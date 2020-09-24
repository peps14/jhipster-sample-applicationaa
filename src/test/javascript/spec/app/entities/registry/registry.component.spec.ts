import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { RegistryComponent } from 'app/entities/registry/registry.component';
import { RegistryService } from 'app/entities/registry/registry.service';
import { Registry } from 'app/shared/model/registry.model';

describe('Component Tests', () => {
  describe('Registry Management Component', () => {
    let comp: RegistryComponent;
    let fixture: ComponentFixture<RegistryComponent>;
    let service: RegistryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [RegistryComponent],
      })
        .overrideTemplate(RegistryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RegistryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RegistryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Registry(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.registries && comp.registries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
