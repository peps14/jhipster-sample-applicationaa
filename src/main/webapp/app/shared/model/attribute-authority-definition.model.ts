import { IAttributeDefinition } from 'app/shared/model/attribute-definition.model';

export interface IAttributeAuthorityDefinition {
  id?: number;
  code?: string;
  name?: string;
  description?: string;
  enabled?: boolean;
  spidLevel?: string;
  attributesUrl?: string;
  consentRequired?: boolean;
  consentUrl?: string;
  checkConsentUrl?: string;
  errorRedirectUrl?: string;
  spidSchemaVersion?: string;
  apiVersion?: string;
  testUrl?: string;
  attributes?: IAttributeDefinition[];
}

export class AttributeAuthorityDefinition implements IAttributeAuthorityDefinition {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public description?: string,
    public enabled?: boolean,
    public spidLevel?: string,
    public attributesUrl?: string,
    public consentRequired?: boolean,
    public consentUrl?: string,
    public checkConsentUrl?: string,
    public errorRedirectUrl?: string,
    public spidSchemaVersion?: string,
    public apiVersion?: string,
    public testUrl?: string,
    public attributes?: IAttributeDefinition[]
  ) {
    this.enabled = this.enabled || false;
    this.consentRequired = this.consentRequired || false;
  }
}
