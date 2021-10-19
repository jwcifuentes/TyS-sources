export interface IDireccionForm {
  isExternalInfo?: boolean;
  pais?: { id: number; codigo?: string, nombre?: string };
  departamento?: { id: number; codigo?: string, nombre?: string };
  municipio?: { id: number; codigo?: string, nombre?: string };
  ciudad?: string;
  codigoPostal?: string;
  direccion?: string | {
    tipoVia?: { id: number; codigo?: string, nombre?: string },
    nroViaPrincipal?: string,
    prefijoCuadrante?: { id: number; codigo?: string, nombre?: string },
    bis?: { id: number; codigo?: string, nombre?: string },
    orientacion?: { id: number; codigo?: string, nombre?: string },
    nroVia?: string,
    prefijoCuadrante_se?: { id: number; codigo?: string, nombre?: string },
    nroPlaca?: string,
    orientacion_se?: { id: number; codigo?: string, nombre?: string },
    complementos?: any[],
  };

  tipoVia?: any;
  nroViaPrincipal?: any;
  prefijoCuadrante?: any;
  bis?: any;
  orientacion?: any;
  nroVia?: any;
  prefijoCuadrante_se?: any;
  nroPlaca?: any;
  orientacion_se?: any;
  complementos?: Array<{ tipoComplemento: any; complementoAdicional: string }>;
}
