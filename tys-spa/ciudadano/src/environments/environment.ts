// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.


//hostname produccion = MINTRABGD08 = 172.20.43.67
//hostname desarrollo = MINTRABWASAPP = 172.20.22.212

//PRODUCCION
//const base_endpoint = 'http://MINTRABGD08:9082/tys-web/web-controllers/public';
//const base_endpoint = 'https://tramites.mintrabajo.gov.co/tys-web/web-controllers/public';
//const pdf_dataBase = 'http://MINTRABGD08:9083/rotulador/ReportePracticaNNA'
//const urlServicePdf = 'http://MINTRABGD08:9083/rotulador/ReportePracticaNNA';

//PRUEBAS
const pdf_dataBase = 'http://MINTRABWASAPP:9083/rotulador/ReportePracticaNNA';
const urlServicePdf = 'http://MINTRABWASAPP:9083/rotulador/ReportePracticaNNA';
const base_endpoint = 'http://MINTRABWASAPP:9082/tys-web/web-controllers/public';






export const TIPO_TRAMITE = {
  '1': 1,   // APROBACIÓN DEL REGLAMENTO DE TRABAJO DE LAS EMPRESAS DE SERVICIOS TEMPORALES.
  '2': 2,   // COMPROBACIÓN DE TRABAJO SIN SOLUCIÓN DE CONTINUIDAD.
  '3': 3,   // AUTORIZACIÓN PARA LABORAR HORAS EXTRAS.,
  '4': 4,   // CERTIFICACIÓN DE TRABAJADORES EN SITUACIÓN DE DISCAPACIDAD CONTRATADOS POR UN EMPLEADOR.
  '5': 5,   // AUTORIZACIÓN PARA EL FUNCIONAMIENTO DE EMPRESAS DE SERVICIOS TEMPORALES (EST) Y DE SUS SUCURSALES.
  '6': 6,   // AUTORIZACIÓN PARA TERMINACIÓN DE CONTRATOS DE TRABAJADORAS EN ESTADO DE EMBARAZO O LACTANCIA
  '7': 7,   // AUTORIZACIÓN PARA LA TERMINACIÓN DEL VÍNCULO LABORAL O DE TRABAJO ASOCIATIVO A TRABAJADORES EN SITUACIÓN DE DISCAPACIDAD.
  '8': 8,   // AUTORIZACIÓN AL EMPLEADOR PARA DESPIDO COLECTIVO DE TRABAJADORES POR CLAUSURA
            // DE LABORES TOTAL O PARCIAL EN FORMA DEFINITIVA O TEMPORAL.
  '9': 9,   // AUTORIZACIÓN PARA TRABAJAR A NIÑOS, NIÑAS O ADOLESCENTES.
  '10': 10, // DECLARATORIA DE UNIDAD DE EMPRESA.
  '11': 11, // AUTORIZACIÓN A EMPRESA PARA DISMINUCIÓN DE CAPITAL.
  '12': 12, // AUTORIZACIÓN AL EMPLEADOR PARA LA SUSPENSIÓN TEMPORAL DE ACTIVIDADES HASTA POR 120 DÍAS.
  '13': 13, // CONSTATACIÓN DE CESE DE ACTIVIDADES.
  '14': 14, // AUTORIZACIÓN PARA EL PAGO PARCIAL DE CESANTÍAS, PARA LA REALIZACIÓN DE PLANES DE VIVIENDA.
  '15': 15, // CONVOCATORIA DE INTEGRACIÓN DEL TRIBUNAL DE ARBITRAMENTO PARA LA SOLUCIÓN DE CONFLICTOS COLECTIVOS LABORALES.
  '16': 16, // EXPEDICIÓN DEL CERTIFICADO DE EXISTENCIA Y REPRESENTACIÓN LEGAL DE LAS ASOCIACIONES DE PENSIONADOS.
  '17': 17, // CERTIFICACIONES Y/O COPIAS DE LOS REGISTROS DE LAS ORGANIZACIONES SINDICALES ANTE EL MINISTERIO DEL TRABAJO.
  '18': 18, // APROBACIÓN DE LOS ESTATUTOS O REFORMAS DE UNA ASOCIACIÓN DE PENSIONADOS.
  '19': 19, // RECONOCIMIENTO DE LA PERSONERÍA JURÍDICA DE LAS ASOCIACIONES DE PENSIONADOS.
  '20': 20, // CANCELACIÓN DE LA PERSONERÍA JURÍDICA DE ASOCIACIONES DE PENSIONADOS.
  '21': 21, // OTRO
  '22': 22, // Autorización de prácticas laborales para adolescentes
};

export const TIPO_PERSONA = {
  PERSONA_JURIDICA: 2,
  PERSONA_NATURAL: 1,
  ENTIDAD_OFICIAL: 5,
  ESTABLECIMIENTO_COMERCIAL: 3
};

export const TIME_RANGE  = {
  MIN_DAY_TIME: 0,
  MAX_DAY_TIME: 11,
  MINUTE_END: 45,
  MIN_LATE_TIME: 12,
  MAX_LATE_TIME: 23,
  TIME_INTERVAL: 15
};

export const TIPODOC_NIT = 8;

export const environment = {
  production: false,
  base_endpoint: base_endpoint,
  securityKey: '18404DD6DD466C3F6FF083E36F9CEBC07165E198CB82EFD60935779085FF76E6',
  securityUser: 'U2FsdGVkX1/UmM9fNyFkT4AHU2+pmKpFh+0vV/xmAx8=',
  securityPass: 'U2FsdGVkX19QldX+LGi3hM0A4ZbhxqkGL7l62Pt2+0GUQNFzbV882kYVHbsQ7X3R',
  pdf_dataBase: `${pdf_dataBase}`,
  //insertar url del servicio pdf
  urlServicePdf: urlServicePdf,
  doc_carta_intencion : './assets//FORMATO_CARTA_DE_INTENCIÓN_PARA_LA_REALIZACIÓN_DE_PRACTICA_LABORAL.docx',
  doc_carta_autorizacion : './assets//FORMATO_CARTA_DE_AUTORIZACIÓN_DE_LA_INSTITUCIÓN_DE_EDUCACIÓN_O_FORMACIÓN_PARA_PRÁCTICAS_LABORALES.docx'
};