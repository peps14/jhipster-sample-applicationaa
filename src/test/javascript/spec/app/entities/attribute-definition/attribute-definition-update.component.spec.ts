import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { AttributeDefinitionUpdateComponent } from 'app/entities/attribute-definition/attribute-definition-update.component';
import { AttributeDefinitionService } from 'app/entities/attribute-definition/attribute-definition.service';
import { AttributeDefinition } from 'app/shared/model/attribute-definition.model';

describe('Component Tests', () => {
  describe('AttributeDefinition Management Update Component', () => {
    let comp: AttributeDefinitionUpdateComponent;
    let fixture: ComponentFixture<AttributeDefinitionUpdateComponent>;
    let service: AttributeDefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [AttributeDefinitionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AttributeDefinitionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AttributeDefinitionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AttributeDefinitionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AttributeDefinition(123);
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
        const entity = new AttributeDefinition();
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
