import { IAttributeAuthorityDefinition } from 'app/shared/model/attribute-authority-definition.model';

export interface IAttributeDefinition {
  id?: number;
  code?: string;
  name?: string;
  description?: string;
  publicAvalable?: string;
  spidLevel?: string;
  multivalue?: boolean;
  defaultValues?: any;
  consentRequired?: boolean;
  aaCode?: string;
  attributeAuthority?: IAttributeAuthorityDefinition;
}

export class AttributeDefinition implements IAttributeDefinition {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public description?: string,
    public publicAvalable?: string,
    public spidLevel?: string,
    public multivalue?: boolean,
    public defaultValues?: any,
    public consentRequired?: boolean,
    public aaCode?: string,
    public attributeAuthority?: IAttributeAuthorityDefinition
  ) {
    this.multivalue = this.multivalue || false;
    this.consentRequired = this.consentRequired || false;
  }
}
