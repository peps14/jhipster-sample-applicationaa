export interface IRegistry {
  id?: number;
  name?: string;
  enabled?: boolean;
  url?: string;
}

export class Registry implements IRegistry {
  constructor(public id?: number, public name?: string, public enabled?: boolean, public url?: string) {
    this.enabled = this.enabled || false;
  }
}
