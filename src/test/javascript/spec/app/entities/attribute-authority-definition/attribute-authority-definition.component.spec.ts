import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { AttributeAuthorityDefinitionComponent } from 'app/entities/attribute-authority-definition/attribute-authority-definition.component';
import { AttributeAuthorityDefinitionService } from 'app/entities/attribute-authority-definition/attribute-authority-definition.service';
import { AttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

describe('Component Tests', () => {
  describe('AttributeAuthorityDefinition Management Component', () => {
    let comp: AttributeAuthorityDefinitionComponent;
    let fixture: ComponentFixture<AttributeAuthorityDefinitionComponent>;
    let service: AttributeAuthorityDefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [AttributeAuthorityDefinitionComponent],
      })
        .overrideTemplate(AttributeAuthorityDefinitionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AttributeAuthorityDefinitionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AttributeAuthorityDefinitionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AttributeAuthorityDefinition(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.attributeAuthorityDefinitions && comp.attributeAuthorityDefinitions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
