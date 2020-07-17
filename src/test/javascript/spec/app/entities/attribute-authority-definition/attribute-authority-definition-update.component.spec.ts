import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { AttributeAuthorityDefinitionUpdateComponent } from 'app/entities/attribute-authority-definition/attribute-authority-definition-update.component';
import { AttributeAuthorityDefinitionService } from 'app/entities/attribute-authority-definition/attribute-authority-definition.service';
import { AttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

describe('Component Tests', () => {
  describe('AttributeAuthorityDefinition Management Update Component', () => {
    let comp: AttributeAuthorityDefinitionUpdateComponent;
    let fixture: ComponentFixture<AttributeAuthorityDefinitionUpdateComponent>;
    let service: AttributeAuthorityDefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [AttributeAuthorityDefinitionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AttributeAuthorityDefinitionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AttributeAuthorityDefinitionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AttributeAuthorityDefinitionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AttributeAuthorityDefinition(123);
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
        const entity = new AttributeAuthorityDefinition();
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
