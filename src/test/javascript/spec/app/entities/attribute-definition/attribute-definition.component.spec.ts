import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { AttributeDefinitionComponent } from 'app/entities/attribute-definition/attribute-definition.component';
import { AttributeDefinitionService } from 'app/entities/attribute-definition/attribute-definition.service';
import { AttributeDefinition } from 'app/shared/model/attribute-definition.model';

describe('Component Tests', () => {
  describe('AttributeDefinition Management Component', () => {
    let comp: AttributeDefinitionComponent;
    let fixture: ComponentFixture<AttributeDefinitionComponent>;
    let service: AttributeDefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [AttributeDefinitionComponent],
      })
        .overrideTemplate(AttributeDefinitionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AttributeDefinitionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AttributeDefinitionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AttributeDefinition(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.attributeDefinitions && comp.attributeDefinitions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
