import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AttributeAuthorityDefinitionService } from 'app/entities/attribute-authority-definition/attribute-authority-definition.service';
import { IAttributeAuthorityDefinition, AttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

describe('Service Tests', () => {
  describe('AttributeAuthorityDefinition Service', () => {
    let injector: TestBed;
    let service: AttributeAuthorityDefinitionService;
    let httpMock: HttpTestingController;
    let elemDefault: IAttributeAuthorityDefinition;
    let expectedResult: IAttributeAuthorityDefinition | IAttributeAuthorityDefinition[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AttributeAuthorityDefinitionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AttributeAuthorityDefinition(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AttributeAuthorityDefinition', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AttributeAuthorityDefinition()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AttributeAuthorityDefinition', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            name: 'BBBBBB',
            description: 'BBBBBB',
            enabled: true,
            spidLevel: 'BBBBBB',
            attributesUrl: 'BBBBBB',
            consentRequired: true,
            consentUrl: 'BBBBBB',
            checkConsentUrl: 'BBBBBB',
            errorRedirectUrl: 'BBBBBB',
            spidSchemaVersion: 'BBBBBB',
            apiVersion: 'BBBBBB',
            testUrl: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AttributeAuthorityDefinition', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            name: 'BBBBBB',
            description: 'BBBBBB',
            enabled: true,
            spidLevel: 'BBBBBB',
            attributesUrl: 'BBBBBB',
            consentRequired: true,
            consentUrl: 'BBBBBB',
            checkConsentUrl: 'BBBBBB',
            errorRedirectUrl: 'BBBBBB',
            spidSchemaVersion: 'BBBBBB',
            apiVersion: 'BBBBBB',
            testUrl: 'BBBBBB',
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

      it('should delete a AttributeAuthorityDefinition', () => {
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
