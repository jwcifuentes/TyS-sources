import { AdministradorPage } from './app.po';

describe('administrador App', () => {
  let page: AdministradorPage;

  beforeEach(() => {
    page = new AdministradorPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
