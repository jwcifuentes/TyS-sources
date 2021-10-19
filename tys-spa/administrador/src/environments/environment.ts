// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  
  
  //hostname produccion = MINTRABGD08 = 172.20.43.67
  //hostname desarrollo = MINTRABWASAPP = 172.20.22.212
  
  //PRODUCCION
  //mvcEndpoint : 'http://MINTRABGD08:9082/tys-web/web-controllers',
  mvcEndpoint: 'https://tramites.mintrabajo.gov.co/tys-web/web-controllers',
  //PRUEBAS
  //mvcEndpoint : 'http://mintrabwasapp.mintrabajo.loc:9082/tys-webDev/web-controllers',
  //mvcEndpoint : 'http://MINTRABWASAPP:9082/tys-web/web-controllers',

  securityKey: '18404DD6DD466C3F6FF083E36F9CEBC07165E198CB82EFD60935779085FF76E6',
  securityUser: 'U2FsdGVkX1/UmM9fNyFkT4AHU2+pmKpFh+0vV/xmAx8=',
  securityPass: 'U2FsdGVkX19QldX+LGi3hM0A4ZbhxqkGL7l62Pt2+0GUQNFzbV882kYVHbsQ7X3R'
};
