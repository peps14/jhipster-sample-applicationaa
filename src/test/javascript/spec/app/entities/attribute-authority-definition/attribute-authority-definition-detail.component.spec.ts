import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationaaTestModule } from '../../../test.module';
import { AttributeAuthorityDefinitionDetailComponent } from 'app/entities/attribute-authority-definition/attribute-authority-definition-detail.component';
import { AttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

describe('Component Tests', () => {
  describe('AttributeAuthorityDefinition Management Detail Component', () => {
    let comp: AttributeAuthorityDefinitionDetailComponent;
    let fixture: ComponentFixture<AttributeAuthorityDefinitionDetailComponent>;
    const route = ({ data: of({ attributeAuthorityDefinition: new AttributeAuthorityDefinition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationaaTestModule],
        declarations: [AttributeAuthorityDefinitionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AttributeAuthorityDefinitionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AttributeAuthorityDefinitionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load attributeAuthorityDefinition on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.attributeAuthorityDefinition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
