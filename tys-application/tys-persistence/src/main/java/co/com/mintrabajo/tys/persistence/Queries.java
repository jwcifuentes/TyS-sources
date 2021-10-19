package co.com.mintrabajo.tys.persistence;

/**
 * Created by jrodriguez on 06/11/2017.
 */
public interface Queries {

	// TRAMITES Y SERVICIOS
	public static final String LIST_TRAMITES = "SELECT T.* FROM TYS_TRAMITE_SERVICIOS T ORDER BY T.NOMBRE_TRAMITE ASC ";

	public static final String LIST_TRAMITES_URL = "SELECT T.URL FROM TYS_TRAMITE_URL T WHERE T.COD_TIPO_TRAMITE = ? AND ES_VISIBLE = 1";

	//public static final String LIST_TRAMITES_ACTIVOS = "SELECT T.* FROM TYS_TRAMITE_SERVICIOS T WHERE T.ESTADO_REG =? AND T.IDE_TRAMITE NOT IN (21) ORDER BY T.NOMBRE_TRAMITE ASC ";
	public static final String LIST_TRAMITES_ACTIVOS = "SELECT T.* FROM TYS_TRAMITE_SERVICIOS T WHERE T.ESTADO_REG =? ORDER BY T.NOMBRE_TRAMITE ASC ";

	public static final String TRAMITE_SERVICIO_X_ID = "SELECT * FROM TYS_TRAMITE_SERVICIOS WHERE IDE_TRAMITE = ? ";

	public static final String INSERT_TRAMITE = "INSERT INTO TYS_TRAMITE_SERVICIOS ( NOMBRE_TRAMITE,ESTADO_REG,USUARIO_CREA,FEC_CREACION,IND_PER_ACTUALIZA) VALUES(?,?,?,?,?)";

	public static final String INSERT_ORGNIZACION_SINDICAL = "INSERT INTO TYS_ORGANIZACION_SINDICAL ( IDE_REG_TRAMITE, NOMBRE, SIGLA, NUMERO_PERSONERIA, IDE_DIRECCION) " +
			"VALUES(?,?,?,?,?)";
	public static final String UPDATE_TRAMITE = "UPDATE TYS_TRAMITE_SERVICIOS SET NOMBRE_TRAMITE = ?, IND_SUTANCIADORES = ?, REQ_CONCEPTO_SUB_INSP = ?, IDE_REGLA_ASIGNACION =?,"
			+ "IND_DT_PERMITIDAS = ?, TIEMPO_GEST_TRA = ?, IDE_UNIDAD_TIEMPO = ?, IDE_DOC_RPTA_FINAL =?, IND_SOLO_RECEP = ?, ESTADO_REG = ?,"
			+ "USUARIO_CAMBIO = ?,FEC_CAMBIO = ?, IND_PER_ACTUALIZA = ?, DESCRIPCION = ?  WHERE IDE_TRAMITE  = ?";

	public static final String UPDATE_PARAMETRIZACION_TRAMITE = "UPDATE TYS_TRAMITE_SERVICIOS SET IND_PER_ACTUALIZA = ? WHERE IDE_TRAMITE  = ?";

	public static final String INSERT_TRAMITE_TPG_DOC = "INSERT INTO TYS_TRAMITE_TPG_DOC ( IDE_TPG_DOC, IDE_TRAMITE, VAL_REQUERIDO, FEC_CREACION, IDE_USUARIO_CREA, ESTADO, DESCRIPCION, IND_TIENE_PACT_COV_COLEC, IND_TIENE_REGL_TRAB, IND_TIENE_SOC_SAS, IDE_SOLIC_POR, IDE_JUST_SOLICITUD,IDE_GRADO_ASOCI, IDE_TIP_GESTION, IDE_TIP_ENTIDAD, IDE_TIP_PARENTESCO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

	public static final String UPDATE_TRAMITE_TPG_DOC = "UPDATE TYS_TRAMITE_TPG_DOC SET VAL_REQUERIDO = ?,ESTADO =?,DESCRIPCION = ?, IDE_USUARIO_CAMBIO = ?,FEC_CAMBIO = ?, IND_TIENE_PACT_COV_COLEC = ?, IND_TIENE_REGL_TRAB = ?, IND_TIENE_SOC_SAS = ?, IDE_SOLIC_POR = ?, IDE_JUST_SOLICITUD = ?, IDE_GRADO_ASOCI = ?, IDE_TIP_GESTION = ?, IDE_TIP_ENTIDAD = ?, IDE_TIP_PARENTESCO = ?  WHERE IDE_TRA_TPG =?";

	public static final String LIST_TRAMITE_TPG_DOC_X_ID = "SELECT T.IDE_TRA_TPG, T.IDE_TPG_DOC, T.IDE_TRAMITE, T.VAL_REQUERIDO, T.FEC_CREACION, T.FEC_CAMBIO, T.IDE_USUARIO_CREA, T.IDE_USUARIO_CAMBIO ,T.ESTADO, D.NOM_TPG_DOC,  T.DESCRIPCION  FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON (T.IDE_TPG_DOC =D.IDE_TPG_DOC)WHERE T.IDE_TRAMITE = ? ORDER BY D.NOM_TPG_DOC ASC  ";

	public static final String LIST_TRAMITE_TPG_DOC_X_ID_RULES = "SELECT T.IDE_TRA_TPG, T.IDE_TPG_DOC, D.NOM_TPG_DOC, T.IDE_TRAMITE, T.VAL_REQUERIDO, T.FEC_CREACION, T.FEC_CAMBIO, "
			+ "T.IDE_USUARIO_CREA,T.IDE_USUARIO_CAMBIO,T.ESTADO,T.DESCRIPCION, T.IND_TIENE_PACT_COV_COLEC,T.IND_TIENE_REGL_TRAB, "
			+ "T.IND_TIENE_SOC_SAS,T.IDE_SOLIC_POR,C.NOMBRE AS SOLICITADO_POR,T.IDE_JUST_SOLICITUD, J.NOMBRE AS JUST_SOLICITUD, "
			+ "T.IDE_GRADO_ASOCI, G.NOMBRE AS GRADO_ASOC, T.IDE_TIP_GESTION , X.NOMBRE AS TIPO_GESTION, T.IDE_TIP_ENTIDAD, T.IDE_TIP_PARENTESCO "
			+ "FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON ( T.IDE_TPG_DOC = D.IDE_TPG_DOC ) "
			+ "LEFT JOIN CON_CONSTANTE C ON ( C.IDE_CON_CONSTANTE = T.IDE_SOLIC_POR )  "
			+ "LEFT JOIN CON_CONSTANTE J ON ( J.IDE_CON_CONSTANTE = T.IDE_JUST_SOLICITUD) "
			+ "LEFT JOIN CON_CONSTANTE G ON ( G.IDE_CON_CONSTANTE = T.IDE_GRADO_ASOCI) "
			+ "LEFT JOIN CON_CONSTANTE X ON ( X.IDE_CON_CONSTANTE = T.IDE_TIP_GESTION) "
			+ "WHERE T.IDE_TRAMITE = ?  ORDER BY D.NOM_TPG_DOC ASC ";

	public static final String SEARCH_TPG_DOC_X_TRAMITE = "SELECT COUNT(*) FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON (T.IDE_TPG_DOC =D.IDE_TPG_DOC)WHERE T.IDE_TPG_DOC =? AND T.IDE_TRAMITE =? ";

	public static final String LIST_TRAMITE_TPG_DOC_X_ID_ESTADO = "SELECT T.*, D.NOM_TPG_DOC FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON (T.IDE_TPG_DOC =D.IDE_TPG_DOC)WHERE T.IDE_TRAMITE =? AND ESTADO =? ";

	// CONSTANTES
	public static final String LISTA_CONSTANTES_X_CODPADRE_ESTADO = "SELECT H.* FROM CON_CONSTANTE P INNER JOIN CON_CONSTANTE H ON (P.IDE_CON_CONSTANTE = H.IDE_CON_CONS_ID) WHERE P.CODIGO = :COD_PADRE AND H.IDE_ESTADO_REG =: ESTADO ORDER BY H.NOMBRE ";

	// JUSTA CAUSA
	public static final String LISTA_JUSTA_CAUSA = "SELECT * FROM TYS_JUSTAS_CAUSAS";

	// LISTA CAUSA DESPIDO
	public static final String LISTA_CAUSAL_DESPIDO = "SELECT * FROM TYS_CAUSAL_DESPIDO";

	//LISTA JUSTA CAUSA POR ID
	public static final String LISTA_ID_JUSTA_CAUSA = "SELECT * FROM TYS_JUSTAS_CAUSAS WHERE ID_CAUSAL_DESPIDO = ?";

	String UPDATE_PERSONA_NNNA_BY_IDEREGTRAMITE = "UPDATE TYS_PERSONA SET NOMBRE1 = ?, NOMBRE2 = ?, APELLIDO1 = ?, APELLIDO2 = ?, FECHA_NACIMIENTO = ?,\n" +
			" NOMBRE_COLEGIO = ?, JORNADA_ESCOLAR = ?, NRO_HIJOS = ?, ID_GENERO = ?, VAL_MAIL = ?\n" +
			" WHERE IDE_REG_TRAMITE = ? AND IDE_TIP_PERSONA = 'NNA'";
	String UPDATE_PERSONA_NOMBRE_APELLIDOS_BY_TIPO_AND_IDTRAMITE = "UPDATE TYS_PERSONA SET NOMBRE1 = ?, NOMBRE2 = ?, APELLIDO1 = ?, APELLIDO2 = ?"+
			" WHERE IDE_REG_TRAMITE = ? AND IDE_TIP_PERSONA = ?";

	String UPDATE_PERSONA_ADOL_BY_TIPO_AND_IDTRAMITE = "UPDATE TYS_PERSONA SET NOMBRE1 = ?, NOMBRE2 = ?, APELLIDO1 = ?, APELLIDO2 = ?"+
			" , ID_TIPO_REGIMEN = ?, NOMBRE_REGIMEN = ? WHERE IDE_REG_TRAMITE = ? AND IDE_TIP_PERSONA = 'ADOL'";

	String UPDATE_COR_DIRECCION_NNA = "UPDATE cor_direccion set ID_TIPOUBICACION = ? , NOMBREUBICACION = ?, ID_TIPOZONA = ?\n" +
			" WHERE IDE_DIRECCION = (SELECT IDE_DIRECCION FROM TYS_PERSONA P\n" +
			"              WHERE P.IDE_REG_TRAMITE = ?\n" +
			"              AND P.IDE_TIP_PERSONA = 'NNA' )";
	String INSERT_EMPLEADOR = "INSERT INTO TYS_EMPLEADOR ( IDE_DIRECCION, EMAIL, NOMBRE_RAZON_SOCIAL, NOMBRRE_REPRESENTANTE_LEGAL, NRO_NIT, TELEFONO, IDE_TIPO_EMPLEADOR, CELULAR, IDE_TRAMITE)\n" +
			" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	String INSERT_PERSONA_DISCAPACIDAD = "INSERT INTO TYS_DISCAPACIDAD_PERSONA ( IDE_PERSONA, IDE_DISCAPACIDAD)\n" +
			" VALUES ( (SELECT  P.IDE_PERSONA FROM TYS_PERSONA P\n" +
			" WHERE P.IDE_REG_TRAMITE = ? AND  P.IDE_TIP_PERSONA = 'NNA'), ?)";
	String SP_INSERT_COR_DIRECCION_RETURNING_PK = "SP_TYS_INSERT_COR_DIRECCION";
	// TIPOS DOCUMENTALES
	public static final String LISTA_TIPOS_DOCUMENTALES = "SELECT T.IDE_TPG_DOC, T.NOM_TPG_DOC,T.EST_TPG_DOC FROM ADM_TPG_DOC T WHERE T.EST_TPG_DOC =: ESTADO ORDER BY T.NOM_TPG_DOC ASC";

	public static final String TIPO_DOCUMENTAL_X_ID_TRA_TPG = "SELECT D.* FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON (T.IDE_TPG_DOC =D.IDE_TPG_DOC) WHERE T.IDE_TRA_TPG =? ";

	public static final String TIPO_DOCUMENTAL_X_ID = "SELECT * FROM ADM_TPG_DOC WHERE IDE_TPG_DOC = ? ";

	// SEQUENCE
	public static final String GENERAR_ID_TRAMITE = "SELECT SEQUENCE_TRAMITE_SEVICIO.NEXTVAL FROM DUAL";
	public static final String GENERAR_ID_TRAMITE_TPG_DOC = "SELECT SEQUENCE_TRAMITE_TPG_DOC.NEXTVAL FROM DUAL";

	// PAIS
	public static final String LISTA_PAISES = "SELECT * FROM TVS_PAIS ORDER BY NOM_PAIS ASC ";

	// DEPARTAMENTOS
	public static final String LISTA_DEPARTAMENTOS_POR_PAIS = "SELECT D.IDE_DEPARTAMENTO, D.NOM_DEPARTAMENTO, D.COD_DEPARTAMENTO, D.IDE_PAIS FROM TVS_PAIS P INNER JOIN TVS_DEPARTAMENTO D ON (P.IDE_PAIS = D.IDE_PAIS) WHERE D.IDE_PAIS = ? ORDER BY D.NOM_DEPARTAMENTO ASC ";

	// MUNICIPIOS
	public static final String LISTA_MUNICIPIOS_POR_DEPART = "SELECT M.IDE_MUNICIPIO, M.NOM_MUNICIPIO, M.COD_MUNICIPIO, M.IDE_DEPARTAMENTO FROM TVS_MUNICIPIO M INNER JOIN TVS_DEPARTAMENTO D ON (M.IDE_DEPARTAMENTO = D.IDE_DEPARTAMENTO) AND M.IDE_DEPARTAMENTO =? ORDER BY M.NOM_MUNICIPIO ASC ";

	// NOTARIAS
	public static final String LISTA_NOTARIAS = "SELECT N.IDE_NOTARIA, N.NOMBRE, N.IDE_MUNICIPIO FROM TVS_NOTARIA N INNER JOIN TVS_MUNICIPIO M ON N.IDE_MUNICIPIO =M.IDE_MUNICIPIO WHERE M.IDE_MUNICIPIO =? ORDER BY N.NOMBRE ASC ";

	// TRATAMIENTO DE CORTESIA
	public static final String LISTA_TRATAMIENTOS_CORTESIA = "SELECT * FROM COR_TRA_CORTESIA";

	// DIRECCIONES TERRITORIALES
	 public static final String LISTA_DIRECCIONES_TERRITORIALES ="SELECT * FROM ADM_SUBFONDO WHERE IDE_SUBFONDO NOT IN (136) ORDER BY NOM_SUBFONDO";
	// SELECT * FROM ADM_SUBFONDO WHERE IDE_SUBFONDO = 104
	// SELECT * FROM vw_direccion_territorial
	// ";
	//SELECT * FROM ADM_SUBFONDO WHERE IDE_SUBFONDO = 104
	//SELECT * FROM vw_direccion_territorial

	//SE COMENTA ESTE QUERY PARA PODER HACER PRUEBAS QA
	//public static final String LISTA_DIRECCIONES_TERRITORIALES = "SELECT * FROM vw_direccion_territorial";

	public static final String DIRECCION_TERRITORIAL_X_ID = "SELECT * FROM ADM_SUBFONDO WHERE IDE_SUBFONDO =? ";

	// TIPOS DE DOCUMENTO DE IDENTIDAD
	public static final String LISTA_TIPOS_IDENTIFICACION = "SELECT * FROM TVS_TIP_DOC_IDENTIDAD ";

	// TIPOS DE DOCUMENTO DE NIÑOS Y NIÑAS
	public static final String LISTA_TIPOS_IDENTIFICACION_NNA = "SELECT * FROM TVS_TIP_DOC_IDENTIDAD WHERE COD_TIP_DOC_IDENT IN ('RC','TI','CE') ";
	public static final String LISTA_TIPOS_IDENTIFICACION_NNA_REPRESENTANTE = "SELECT * FROM TVS_TIP_DOC_IDENTIDAD WHERE COD_TIP_DOC_IDENT IN ('CC','CE') ";

	// CONSULTAR TRAMITE POR REDICADO Y CODIGO;
	public static final String CONSULTAR_TRAMITE_X_NUMERORAD_X_CODSEG = "SELECT REG.IDE_REG_TRAMITE, REG.NRO_RADICADO,TS.IDE_TRAMITE,TS.NOMBRE_TRAMITE,REG.FEC_CREACION, AIN.IDE_SEDE_AMT    AS ID_DIRTERRITORIAL, "
			+ "SUB.DES_SUBFONDO    AS NOM_DIRTERRITORIAL, AIN.IDE_DEPENDENCIA AS ID_DEPENDENCIA, DEP.DES_SECCION     AS NOM_DEPENDENCIA, "
			+ "REG.IDE_ESTADO_TRAMITE ,CON.NOMBRE AS ESTADO, EMP.IND_PACT_CONV_COL,EMP.IND_OBLI_REGL_TRAB,EMP.IND_EMP_SAS,REG.IDE_RADICA_TRAMITE AS SOLICITADO_POR, "
			+ "REG.IDE_JUSTIFICACION AS JUST_SOLICITUD, REG.IDE_GRADO_ASOCI AS GRADO_ASOC,DEP.COD_SECCION,P.VAL_FILENET,REG.IND_REQ_ACTUALIZACION, ET.IND_APROBACION_ENTR, ET.IND_APROBACION_VISI "
			+ "FROM COR_DOC_CORR_AGENTE CDA INNER JOIN COR_AGENTE AGE ON CDA.IDE_AGENTE = AGE.IDE_AGENTE "
			+ "LEFT JOIN COR_AGENTE_ITR AIN ON AGE.IDE_AGENTE = AIN.IDE_AGENTE "
			+ "LEFT JOIN ADM_SUBFONDO SUB ON AIN.IDE_SEDE_AMT = SUB.IDE_SUBFONDO LEFT JOIN ADM_SECCION DEP ON AIN.IDE_DEPENDENCIA = DEP.IDE_SECCION "
			+ "INNER JOIN COR_DOC_CORRESPONDENCIA COR ON CDA.IDE_DOCUMENTO = COR.IDE_DOCUMENTO "
			+ "LEFT JOIN TYS_REGISTRO_TRAMITE REG ON COR.NRO_RADICADO = REG.NRO_RADICADO "
			+ "LEFT JOIN PPD_DOCUMENTO P ON REG.IDE_PPD_RESP =P.IDE_PPD_DOCUMENTO "
			+ "INNER JOIN TYS_EMPRESA EMP ON EMP.IDE_REG_TRAMITE = REG.IDE_REG_TRAMITE "
			+ "LEFT JOIN TYS_TRAMITE_SERVICIOS TS ON TS.IDE_TRAMITE = REG.IDE_TRAMITE "
			+ "INNER JOIN CON_CONSTANTE CON ON REG.IDE_ESTADO_TRAMITE =CON.IDE_CON_CONSTANTE "
			+ "LEFT JOIN TYS_ENTREVISTA_NNA ET ON ET.IDE_REG_TRAMITE = REG.IDE_REG_TRAMITE "
			+ "WHERE AIN.IND_ORIGINAL = 2 AND COR.NRO_RADICADO   = ? AND REG.VAL_COD_SEGURIDAD =? ";

	// consulta numero de radicado por numero de radicado

	//consulta numero de radicado por numero de radicado
	// CONSULTAR TRAMITE POR REDICADO a relacionar
	public static final String CONSULTAR_NUMERO_RADICADO_A_RELACIONAR = "SELECT COUNT(COR.NRO_RADICADO) A "
			+ "FROM COR_DOC_CORRESPONDENCIA COR " + "WHERE COR.NRO_RADICADO = ?";

	public static final String LISTA_TIPO_PERSONA = "SELECT * FROM COR_PERSONA_REMITENTE WHERE IDE_PER_REMITENTE NOT IN 4 ORDER BY DES_PERSONA_REMITENT DESC ";

	public static final String LISTA_ACTUA_CALIDAD = "SELECT * FROM COR_EN_CALIDAD ORDER BY NOM_CALIDAD ASC ";

	public static final String LISTA_TIPO_TELEFONO = "SELECT * FROM COR_TIPO_TELEFONO ORDER BY NOM_TIPO_TELEFONO ASC ";

	// NOTIFICAR ACTUALIZACION DE TRAMITE

	public static final String INF_NOTIFICACION_ACTUALIZACION_TRAMITE = "SELECT REG.NRO_RADICADO, REG.VAL_COD_SEGURIDAD, TS.IDE_TRAMITE, TS.NOMBRE_TRAMITE,AIN.IDE_SEDE_AMT AS ID_DIRTERRITORIAL, SUB.DES_SUBFONDO AS NOM_DIRTERRITORIAL "
			+ "FROM COR_DOC_CORR_AGENTE CDA INNER JOIN COR_AGENTE AGE ON CDA.IDE_AGENTE = AGE.IDE_AGENTE "
			+ "LEFT JOIN COR_AGENTE_ITR AIN ON AGE.IDE_AGENTE = AIN.IDE_AGENTE "
			+ "LEFT JOIN ADM_SUBFONDO SUB ON AIN.IDE_SEDE_AMT = SUB.IDE_SUBFONDO "
			+ "INNER JOIN COR_DOC_CORRESPONDENCIA COR ON CDA.IDE_DOCUMENTO = COR.IDE_DOCUMENTO "
			+ "LEFT JOIN TYS_REGISTRO_TRAMITE REG ON COR.NRO_RADICADO = REG.NRO_RADICADO "
			+ "LEFT JOIN TYS_TRAMITE_SERVICIOS TS ON TS.IDE_TRAMITE = REG.IDE_TRAMITE "
			+ "INNER JOIN CON_CONSTANTE CON ON REG.IDE_ESTADO_TRAMITE = CON.IDE_CON_CONSTANTE "
			+ "WHERE AIN.IND_ORIGINAL = 2  AND   COR.NRO_RADICADO = ? ";

	public static final String CORREO_NOTIFICACION_ACTUALIZACION = "SELECT AEX.VAL_CORREO AS CORREO "
			+ "FROM COR_DOC_CORR_AGENTE CDA INNER JOIN COR_AGENTE AGE ON CDA.IDE_AGENTE = AGE.IDE_AGENTE "
			+ "LEFT JOIN COR_AGENTE_EXTERNO AEX ON AGE.IDE_AGENTE = AEX.IDE_AGENTE "
			+ "INNER JOIN COR_DOC_CORRESPONDENCIA COR ON CDA.IDE_DOCUMENTO = COR.IDE_DOCUMENTO "
			+ "LEFT JOIN COR_TIPO_AGENTE TPA ON AGE.IDE_TIPO_AGENTE = TPA.IDE_TIPO_AGENTE "
			+ "WHERE TPA.IDE_TIPO_AGENTE = 2 AND   COR.NRO_RADICADO = ? ";

	public static final String CORREO_NOTIFICACION_ACTUALIZACION_FUNCIONARIO = "SELECT P.NOM_FUNCIONARIO, P.VAL_CORR_ELECTRONICO "
			+ "FROM TYS_RESP_TRAMITE T " + "INNER JOIN TYS_FUNCION_ASIG FA ON T.IDE_RESP_TRAMITE = FA.IDE_RESP_TRAMITE "
			+ "INNER JOIN ADM_PLANTA P ON P.IDE_PLANTA = FA.IDE_PLANTA "
			+ "WHERE T.IDE_REG_TRAMITE = ?  AND FA.IDE_FUNCION_FUNC = ?";

	public static final String DOCUMENTO_PRODUCIDO = "SELECT  P.VAL_FILENET FROM  TYS_REGISTRO_TRAMITE t INNER JOIN PPD_DOCUMENTO p on T.IDE_PPD_RESP =P.IDE_PPD_DOCUMENTO WHERE T.NRO_RADICADO = ? ";

	public static final String INSERT_DOCUMENTO_ADICIONAL = "INSERT INTO TYS_DOC_ADICIONALES (IDE_REG_TRAMITE,IDE_PPD_DOCUMENTO,FEC_CREACION,IDE_USUARIO_CREA,VAL_IP) VALUES (?,?,?,?,?)";

	public static final String DOCUMENTOS_ADCIONALES_POR_TRAMITE = "SELECT  P.VAL_ASUNTO ,P.VAL_FILENET, R.IDE_REG_TRAMITE "
			+ "FROM TYS_DOC_ADICIONALES T INNER JOIN PPD_DOCUMENTO P ON T.IDE_PPD_DOCUMENTO = P.IDE_PPD_DOCUMENTO "
			+ "INNER JOIN TYS_REGISTRO_TRAMITE R ON  T.IDE_REG_TRAMITE = R.IDE_REG_TRAMITE "
			+ "WHERE R.NRO_RADICADO = ? ";

	// IMFORMACION DE LA EMPRESA POR NIT
	public static final String INFORMACION_EMPRESA = "SELECT E.NIT, E.NOM_RAZON_SOCIAL,E.NUM_ESCRITURA, E.FEC_EXP_ESCRITURA , E.IDE_DPTO_EXPE, E.IDE_MPIO_EXP, "
			+ "E.IDE_NOTARIA,E.NUM_POLIZA, E.VAL_POLIZA, E.NOM_ASEGURADORA, E.FEC_IINI_POLIZA, E.FEC_FIN_POLIZA, E.ID_ACTIVIDAD_ECONOMICA "
			+ "FROM EMP_EMPRESA E  WHERE E.NIT = ? ";

	public static final String INFORMACION_REPRESENTANTE_EMPRESA = "SELECT E.IDE_TIP_IDENT_REP, E.NUM_IDENTIDAD_REP, E.NOMBRE_REP , E.VAL_MAIL_REP, E.VAL_INDICATIVO_REP, "
			+ "E.VAL_TELEFONO_REP, E.VAL_EXTENSION_REP, E.VAL_CELULAR_REP " + "FROM EMP_EMPRESA E WHERE E.NIT = ? ";

	public static final String INFORMACION_SOCIOS_EMPRESA = "SELECT S.NOMBRE, S.IDE_TIP_IDENT, S.NUM_IDENTIDAD "
			+ "FROM EMP_EMPRESA E INNER JOIN EMP_SOCIO S ON E.IDE_EMPRESA =S.IDE_EMPRESA " + "WHERE E.NIT = ? ";

	public static final String DIRECCION_EMPRESA = "SELECT D.IDE_PAIS, P.NOM_PAIS, D.IDE_DEPARTAMENTO,DP.NOM_DEPARTAMENTO, D.IDE_MUNICIPIO,M.NOM_MUNICIPIO,D.COD_POSTAL , D.VAL_DIRECCION, D.VAL_CIUDAD "
			+ "FROM EMP_EMPRESA E INNER JOIN COR_DIRECCION D ON E.IDE_DIRECCION =D.IDE_DIRECCION "
			+ "INNER JOIN TVS_PAIS P ON P.IDE_PAIS = D.IDE_PAIS "
			+ "LEFT JOIN  TVS_DEPARTAMENTO DP ON DP.IDE_DEPARTAMENTO =D.IDE_DEPARTAMENTO "
			+ "LEFT  JOIN TVS_MUNICIPIO M ON M.IDE_MUNICIPIO =D.IDE_MUNICIPIO " + "WHERE E.NIT = ? ";

	public static final String DIRECCION_SUCURSALES_EMPRESA = "SELECT D.IDE_PAIS, P.NOM_PAIS, D.IDE_DEPARTAMENTO,DP.NOM_DEPARTAMENTO, D.IDE_MUNICIPIO,M.NOM_MUNICIPIO, D.COD_POSTAL , D.VAL_DIRECCION, D.VAL_CIUDAD "
			+ "FROM EMP_EMPRESA E INNER JOIN EMP_SUCURSAL S ON E.IDE_EMPRESA = S.IDE_EMPRESA "
			+ "INNER JOIN COR_DIRECCION D ON S.IDE_DIRECCION = D.IDE_DIRECCION "
			+ "INNER JOIN TVS_PAIS P ON P.IDE_PAIS = D.IDE_PAIS "
			+ "LEFT JOIN  TVS_DEPARTAMENTO DP ON DP.IDE_DEPARTAMENTO =D.IDE_DEPARTAMENTO "
			+ "LEFT  JOIN TVS_MUNICIPIO M ON M.IDE_MUNICIPIO =D.IDE_MUNICIPIO " + "WHERE E.NIT = ? ";

	public static final String INFORMACION_REMITENTE_EMPRESA = "SELECT E.NIT, E.NOM_RAZON_SOCIAL, E.NOMBRE_REP , E.VAL_INDICATIVO_REP, "
			+ "E.VAL_TELEFONO_REP, E.VAL_EXTENSION_REP, E.VAL_MAIL_REP " + "FROM EMP_EMPRESA E WHERE E.NIT = ? ";

	public static final String DIRECCION_TERRITORIAL_PAIS_DEPART = " SELECT DIR.IDE_PAIS , DIR.IDE_DEPARTAMENTO "
			+ "FROM ADM_SUBFONDO SUB "
			+ "INNER JOIN TVS_ORGANIGRAMA_ADMINISTRATIVO ORG ON SUB.IDE_ORGA_ADMIN = ORG.IDE_ORGA_ADMIN "
			+ "LEFT JOIN COR_DIRECCION DIR ON DIR.IDE_DIRECCION = ORG.IDE_DIRECCION " + "WHERE SUB.IDE_SUBFONDO = ? ";

	public static final String ACTUALIZAR_IND_REQ_ACTUALIZACION_DOC = "UPDATE TYS_REGISTRO_TRAMITE T SET T.IND_REQ_ACTUALIZACION = 0 WHERE T.NRO_RADICADO =? ";

	public static final String TIEMPO_GESTION_TRAMITE = "SELECT T.TIEMPO_GEST_TRA ||' '|| C.NOMBRE FROM CON_CONSTANTE C INNER JOIN TYS_TRAMITE_SERVICIOS T "
			+ " ON C.IDE_CON_CONSTANTE = T.IDE_UNIDAD_TIEMPO WHERE T.IDE_TRAMITE = ? ";

	public static final String INSERT_PROCESO_MENSAJE_TYS = "INSERT INTO BRK.PROCESO_MENSAJE_TYS (COD_DEPENDENCIA, NUM_RADICADO, BASE64_DOC, IDE_TIPOLOGIA, COD_PLANTILLA, "
			+ "NOMBRE_DOC, ID_DOCUMENTO, ID_FILENET, ORIGEN, ESTADO, FEC_CREACION, IDE_USUARIO_CREA) "
			+ "VALUES ( ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String INSERT_JUSTA_CAUSA = "INSERT INTO TYS_JUSTA_CAUSA (DESCRIPTION_NAME, IDE_REG_TRAMITE, IDE_CON_CONSTANTE) VALUES (?, ?, ?)";

	public static final String INSERT_TYS_CAUSALES = "INSERT INTO TYS_CAUSALES (IDE_REG_TRAMITE,ID_JUSTA_CAUSA,ID_CAUSAL_DESPIDO, OBSERVACIONES) VALUES (?, ?, ?, ?)";

	String ESTADO_PPD_DOCUMENTO_BY_IDE = "SELECT CON.* FROM  PPD_DOCUMENTO PD INNER JOIN CON_CONSTANTE CON ON PD.IDE_ESTADO = CON.IDE_CON_CONSTANTE \n" +
			"WHERE PD.IDE_PPD_DOCUMENTO = ? AND CON.IDE_ESTADO_REG = 1";
	String SUBCLASIFICACION_PPD_DOCUMENTO = "SELECT SUBCLASIFICACION FROM PPD_DOCUMENTO WHERE PPD_DOCUMENTO.IDE_PPD_DOCUMENTO = ?";
	String UPDATE_SUBCLASIFICACION_PPD_DOCUMENTO_BY_IDE = "UPDATE  PPD_DOCUMENTO SET SUBCLASIFICACION = ? WHERE IDE_PPD_DOCUMENTO = ? ";
	String UPDATE_SUBCLASIFICACION_PPD_DOCUMENTO_NO_CARGADO_BY_IDE = "UPDATE  PPD_DOCUMENTO_NO_CARGADOS set SUBCLASIFICACION = ? WHERE NUMRADICADO = ?";
    String INSERT_ADDRESS = "INSERT INTO COR_DIRECCION(COD_POSTAL, IDE_DEPARTAMENTO, IDE_MUNICIPIO, IDE_PAIS, VAL_DIRECCION, VAL_CIUDAD) " +
			"(?,?,?,?,?,?)";
    String LIST_ACTIVIDAD_ECONOMICA_DIVISION = "SELECT * FROM TYS_ACTIVIDAD_ECONOMICA WHERE ID_ACTIVIDAD_ECONOMICA_P IS NULL order by SECCION,DIVISION_GRUPO";
	String LIST_ACTIVIDAD_ECONOMICA_GRUPOS_DIVISION = "SELECT * FROM TYS_ACTIVIDAD_ECONOMICA WHERE ID_ACTIVIDAD_ECONOMICA_P = ?";
	String UPDATE_ACTIVIDAD_ECONOMICA_EMPRESA = "update TYS_EMPRESA set ID_ACTIVIDAD_ECONOMICA = ? where IDE_REG_TRAMITE = ?";

	String UPDATE_REMITENTE_NOMBRE_APELLIDOS_BY_NRORADICADO = "UPDATE COR_AGENTE_EXTERNO SET NOMBRE1 = ?, NOMBRE2 = ?, APELLIDO1 = ?, APELIDO2 = ?\n" +
			"WHERE IDE_AGENTE = (SELECT\n" +
			"                        sgd.cor_agente_externo.ide_agente\n" +
			"                    FROM\n" +
			"                        sgd.cor_agente_externo\n" +
			"                            INNER JOIN sgd.cor_doc_corr_agente ON sgd.cor_doc_corr_agente.ide_agente = sgd.cor_agente_externo.ide_agente\n" +
			"                            INNER JOIN sgd.cor_doc_correspondencia ON sgd.cor_doc_correspondencia.ide_documento = sgd.cor_doc_corr_agente.ide_documento\n" +
			"                    WHERE\n" +
			"                            sgd.cor_doc_correspondencia.nro_radicado = ? )";

	String FIND_ALL_PPD_DOCUMENTOS_NO_CARGADOS = "SELECT PPD_DOCUMENTO_NO_CARGADOS.*," +
			"CON_CONSTANTE.NOMBRE, CON_CONSTANTE.CODIGO, CON_CONSTANTE.IDE_CON_CONSTANTE\n" +
			" FROM PPD_DOCUMENTO_NO_CARGADOS,CON_CONSTANTE\n" +
			" WHERE PPD_DOCUMENTO_NO_CARGADOS.NUMRADICADO = ? AND CON_CONSTANTE.IDE_CON_CONSTANTE = PPD_DOCUMENTO_NO_CARGADOS.ID_ESTADO" ;
	String GET_ACTIVIDAD_ECONOMICA_BY_ID_ACTIVIDAD_ECONOMICA = "SELECT * FROM TYS_ACTIVIDAD_ECONOMICA WHERE ID_ACTIVIDAD_ECONOMICA = ?";

	String GET_LINKS_TRAMITE_BY_ID = "SELECT * FROM TYS_TRAMITE_URL WHERE COD_TIPO_TRAMITE = ?";
	String UPDATE_URL_TRAMITE = "UPDATE TYS_TRAMITE_URL SET URL = ?, ES_VISIBLE = ? WHERE ID_TRAMITE_URL = ? AND COD_TIPO_TRAMITE = ?";
	String CREATE_URL_TRAMITE = "INSERT INTO TYS_TRAMITE_URL (ID_TRAMITE_URL, COD_TIPO_TRAMITE, URL, ES_VISIBLE) VALUES ((SELECT (MAX(TYS_TRAMITE_URL.ID_TRAMITE_URL) + 1) FROM TYS_TRAMITE_URL), ?, ?, ?)";

	String LISTA_TIPOS_IDENTIFICACION_IN = "SELECT * FROM TVS_TIP_DOC_IDENTIDAD WHERE COD_TIP_DOC_IDENT IN (?) ";
}