import { TestBed } from '@angular/core/testing';

import { TokenUtenteService } from './token-utente.service';

describe('TokenUtenteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TokenUtenteService = TestBed.get(TokenUtenteService);
    expect(service).toBeTruthy();
  });
});
