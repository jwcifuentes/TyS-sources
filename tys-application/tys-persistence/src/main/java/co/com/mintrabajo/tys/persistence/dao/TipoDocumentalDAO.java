package co.com.mintrabajo.tys.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import co.com.mintrabajo.tys.commons.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.TipoDocumentalMapper;
import co.com.mintrabajo.tys.persistence.mapper.TipoDocumentalTramiteMapper;
import co.com.mintrabajo.tys.persistence.mapper.TipoDocumentalTramiteReglasMapper;

/**
 * Created by jrodriguez on 08/11/2017.
 */

@Component
public class TipoDocumentalDAO {

	private static final Logger LOGGER = LogManager.getLogger(TipoDocumentalDAO.class);

	@Autowired
	private JdbcTemplate template;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<TipoDocumental> listarTiposDocumentalesActivos(int estado) throws SystemException {
		try {
			Object[] arguments = { estado };
			return template.query(Queries.LISTA_TIPOS_DOCUMENTALES, arguments, TipoDocumentalMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado lista de tipos documentales: " + e.getMessage(), e);
		}
	}

	public void crear(final TipoDocumentalTramite tipoDocumentalTramite) throws SystemException {
		try {

			Object[] arguments = { tipoDocumentalTramite.getIdTipoDocumental(), tipoDocumentalTramite.getIdTramite(),
					tipoDocumentalTramite.getEsRequerido() ? 1 : 0, tipoDocumentalTramite.getFechaCreacion(),
					tipoDocumentalTramite.getUsuarioCreacion(), tipoDocumentalTramite.isEstado() ? 1 : 0,
					tipoDocumentalTramite.getDescripcion(), tipoDocumentalTramite.isIndTienePactosColectivos() ? 1 : 0,
					tipoDocumentalTramite.isIndTieneReglamentoTrabajo() ? 1 : 0,
					tipoDocumentalTramite.isIndTieneAsociacionSAS() ? 1 : 0, tipoDocumentalTramite.getIdSolicitadoPor(),
					tipoDocumentalTramite.getIdJustSolicitud(), tipoDocumentalTramite.getIdGradoAsociacion(),
					tipoDocumentalTramite.getIdtipoGestion(), tipoDocumentalTramite.getIdTipoEntidad(),
					tipoDocumentalTramite.getIdTipoParentesco()};
			//LOGGER.info(" ---- arguments --- : {}", arguments);
			template.update(Queries.INSERT_TRAMITE_TPG_DOC, arguments);
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion creando tipo documental Tramite: " + e.getMessage(), e);
		}
	}

	public void actualizar(final TipoDocumentalTramite tipoDocumentalTramite) throws SystemException {

		try {
			Object[] arguments = { tipoDocumentalTramite.getEsRequerido(), tipoDocumentalTramite.isEstado(),
					tipoDocumentalTramite.getDescripcion(), tipoDocumentalTramite.getUsuariaCambio(),
					tipoDocumentalTramite.getFechaCambio(), tipoDocumentalTramite.isIndTienePactosColectivos(),
					tipoDocumentalTramite.isIndTieneReglamentoTrabajo(),
					tipoDocumentalTramite.isIndTieneAsociacionSAS(), tipoDocumentalTramite.getIdSolicitadoPor(),
					tipoDocumentalTramite.getIdJustSolicitud(), tipoDocumentalTramite.getIdGradoAsociacion(),
					tipoDocumentalTramite.getIdtipoGestion(), tipoDocumentalTramite.getIdTipoEntidad(),
					tipoDocumentalTramite.getIdTipoParentesco(), tipoDocumentalTramite.getId() };

			//LOGGER.info(" ---- arguments --- : {}", arguments);
			template.update(Queries.UPDATE_TRAMITE_TPG_DOC, arguments);
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion actualizando tipo documental por Tramite: " + e.getMessage(), e);
		}

	}

	public List<TipoDocumentalTramite> getListTipoDocumentalTramite(final Long id) throws SystemException {
		try {
			Object[] arguments = { id };
			return template.query(Queries.LIST_TRAMITE_TPG_DOC_X_ID, arguments,
					TipoDocumentalTramiteMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	public List<TipoDocumentalTramite> getListTiposDocumentalesTramiteReglas(final Long id) throws SystemException {
		try {
			Object[] arguments = { id };
			return template.query(Queries.LIST_TRAMITE_TPG_DOC_X_ID_RULES, arguments,
					TipoDocumentalTramiteReglasMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	public List<TipoDocumentalTramite> getListTipoDocumentalTramitesByFilter(FiltrosTiposDocumentales filtros)
			throws SystemException {

		List<TipoDocumentalTramite> tiposDocumentales = new ArrayList<TipoDocumentalTramite>();
		Map<String, Object> parameters = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT T.IDE_TRA_TPG, T.IDE_TPG_DOC, D.NOM_TPG_DOC, T.IDE_TRAMITE, T.VAL_REQUERIDO, T.FEC_CREACION, T.FEC_CAMBIO, T.IDE_USUARIO_CREA, ")
				.append("T.IDE_USUARIO_CAMBIO,T.ESTADO, T.DESCRIPCION, T.IND_TIENE_PACT_COV_COLEC,T.IND_TIENE_REGL_TRAB, T.IND_TIENE_SOC_SAS, T.IDE_SOLIC_POR, ")
				.append("C.NOMBRE AS SOLICITADO_POR, T.IDE_JUST_SOLICITUD, J.NOMBRE AS JUST_SOLICITUD,T.IDE_GRADO_ASOCI, G.NOMBRE AS GRADO_ASOC, T.IDE_TIP_GESTION , X.NOMBRE AS TIPO_GESTION, T.IDE_TIP_ENTIDAD, T.IDE_TIP_PARENTESCO ")
				.append("FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON ( T.IDE_TPG_DOC = D.IDE_TPG_DOC ) ")
				.append("LEFT JOIN CON_CONSTANTE C ON ( C.IDE_CON_CONSTANTE = T.IDE_SOLIC_POR ) ")
				.append("LEFT JOIN CON_CONSTANTE J ON ( J.IDE_CON_CONSTANTE = T.IDE_JUST_SOLICITUD) ")
				.append("LEFT JOIN CON_CONSTANTE G ON ( G.IDE_CON_CONSTANTE = T.IDE_GRADO_ASOCI) ")
				.append("LEFT JOIN CON_CONSTANTE X ON ( X.IDE_CON_CONSTANTE = T.IDE_TIP_GESTION) ")
				.append("WHERE  T.IDE_TRAMITE = :ID_TRAMITE ")
				.append("AND (T.IND_TIENE_PACT_COV_COLEC = 0 AND T.IND_TIENE_REGL_TRAB = 0  AND  T.IND_TIENE_SOC_SAS = 0  AND T.IDE_SOLIC_POR IS NULL AND T.IDE_JUST_SOLICITUD IS NULL AND T.IDE_GRADO_ASOCI IS NULL AND T.IDE_TIP_GESTION IS NULL AND T.IDE_TIP_ENTIDAD IS NULL AND T.IDE_TIP_PARENTESCO IS NULL ) AND T.ESTADO = 1 ");

		parameters.put("ID_TRAMITE", filtros.getIdTramite());
		tiposDocumentales.addAll(
				jdbcTemplate.query(sql.toString(), parameters, TipoDocumentalTramiteReglasMapper.newInstance()));

		StringBuilder sqlFiltros = new StringBuilder();
		sqlFiltros
				.append("SELECT T.IDE_TRA_TPG, T.IDE_TPG_DOC, D.NOM_TPG_DOC, T.IDE_TRAMITE, T.VAL_REQUERIDO, T.FEC_CREACION, T.FEC_CAMBIO, T.IDE_USUARIO_CREA, ")
				.append("T.IDE_USUARIO_CAMBIO,T.ESTADO, T.DESCRIPCION, T.IND_TIENE_PACT_COV_COLEC,T.IND_TIENE_REGL_TRAB, T.IND_TIENE_SOC_SAS, T.IDE_SOLIC_POR, ")
				.append("C.NOMBRE AS SOLICITADO_POR, T.IDE_JUST_SOLICITUD, J.NOMBRE AS JUST_SOLICITUD,T.IDE_GRADO_ASOCI, G.NOMBRE AS GRADO_ASOC, T.IDE_TIP_GESTION , X.NOMBRE AS TIPO_GESTION, T.IDE_TIP_ENTIDAD,  T.IDE_TIP_PARENTESCO  ")
				.append("FROM TYS_TRAMITE_TPG_DOC T INNER JOIN ADM_TPG_DOC D ON ( T.IDE_TPG_DOC = D.IDE_TPG_DOC ) ")
				.append("LEFT JOIN CON_CONSTANTE C ON ( C.IDE_CON_CONSTANTE = T.IDE_SOLIC_POR ) ")
				.append("LEFT JOIN CON_CONSTANTE J ON ( J.IDE_CON_CONSTANTE = T.IDE_JUST_SOLICITUD) ")
				.append("LEFT JOIN CON_CONSTANTE G ON ( G.IDE_CON_CONSTANTE = T.IDE_GRADO_ASOCI) ")
				.append("LEFT JOIN CON_CONSTANTE X ON ( X.IDE_CON_CONSTANTE = T.IDE_TIP_GESTION) ")
				.append("WHERE  T.IDE_TRAMITE = :ID_TRAMITE AND T.ESTADO = 1 ");

		// PRIMER CASO
		if (filtros.isTienePactosColectivos() && filtros.isTieneReglamentoTrabajo() && filtros.isTieneAsociacionSAS()) {
			sqlFiltros.append(
					"AND (T.IND_TIENE_PACT_COV_COLEC = :ID_PACT_COLEC OR T.IND_TIENE_REGL_TRAB = :ID_REGLA_TRAB OR T.IND_TIENE_SOC_SAS = :ID_SOC_SAS ) ");
			parameters.put("ID_PACT_COLEC", filtros.isTienePactosColectivos() ? 1 : 0);
			parameters.put("ID_REGLA_TRAB", filtros.isTieneReglamentoTrabajo() ? 1 : 0);
			parameters.put("ID_SOC_SAS", filtros.isTieneAsociacionSAS() ? 1 : 0);
		}

		if (filtros.isTienePactosColectivos() && filtros.isTieneReglamentoTrabajo()
				&& !filtros.isTieneAsociacionSAS()) {
			sqlFiltros.append(
					"AND (T.IND_TIENE_PACT_COV_COLEC = :ID_PACT_COLEC OR T.IND_TIENE_REGL_TRAB = :ID_REGLA_TRAB ) ");
			parameters.put("ID_PACT_COLEC", filtros.isTienePactosColectivos() ? 1 : 0);
			parameters.put("ID_REGLA_TRAB", filtros.isTieneReglamentoTrabajo() ? 1 : 0);
		}

		if (filtros.isTienePactosColectivos() && !filtros.isTieneReglamentoTrabajo()
				&& !filtros.isTieneAsociacionSAS()) {
			sqlFiltros.append("AND (T.IND_TIENE_PACT_COV_COLEC = :ID_PACT_COLEC ) ");
			parameters.put("ID_PACT_COLEC", filtros.isTienePactosColectivos() ? 1 : 0);
		}

		// SEGUNDO CASO

		if (!filtros.isTienePactosColectivos() && filtros.isTieneReglamentoTrabajo()
				&& filtros.isTieneAsociacionSAS()) {
			sqlFiltros.append("AND (T.IND_TIENE_REGL_TRAB = :ID_REGLA_TRAB OR T.IND_TIENE_SOC_SAS = :ID_SOC_SAS ) ");
			parameters.put("ID_REGLA_TRAB", filtros.isTieneReglamentoTrabajo() ? 1 : 0);
			parameters.put("ID_SOC_SAS", filtros.isTieneAsociacionSAS() ? 1 : 0);
		}

		if (!filtros.isTienePactosColectivos() && filtros.isTieneReglamentoTrabajo()
				&& !filtros.isTieneAsociacionSAS()) {
			sqlFiltros.append("AND (T.IND_TIENE_REGL_TRAB = :ID_REGLA_TRAB ) ");
			parameters.put("ID_REGLA_TRAB", filtros.isTieneReglamentoTrabajo() ? 1 : 0);
		}

		if (!filtros.isTienePactosColectivos() && !filtros.isTieneReglamentoTrabajo()
				&& filtros.isTieneAsociacionSAS()) {
			sqlFiltros.append("AND (T.IND_TIENE_SOC_SAS = :ID_SOC_SAS ) ");
			parameters.put("ID_SOC_SAS", filtros.isTieneAsociacionSAS() ? 1 : 0);

		}

		// TERCER CASO

		if (filtros.isTienePactosColectivos() && !filtros.isTieneReglamentoTrabajo()
				&& filtros.isTieneAsociacionSAS()) {
			sqlFiltros
					.append("AND (T.IND_TIENE_PACT_COV_COLEC = :ID_PACT_COLEC OR T.IND_TIENE_SOC_SAS = :ID_SOC_SAS ) ");
			parameters.put("ID_PACT_COLEC", filtros.isTienePactosColectivos() ? 1 : 0);
			parameters.put("ID_SOC_SAS", filtros.isTieneAsociacionSAS() ? 1 : 0);
		}

		if (filtros.getIdSolicitadoPor() != null) {
			sqlFiltros.append("AND T.IDE_SOLIC_POR = :ID_SOLIC_POR ");
			parameters.put("ID_SOLIC_POR", filtros.getIdSolicitadoPor());
		}

		if (filtros.getIdJustSolicitud() != null) {
			sqlFiltros.append("AND T.IDE_JUST_SOLICITUD = :ID_JUST_SOLICITUD ");
			parameters.put("ID_JUST_SOLICITUD", filtros.getIdJustSolicitud());
		}

		if (filtros.getIdGradoAsociacion() != null) {
			sqlFiltros.append("AND T.IDE_GRADO_ASOCI = :ID_GRADO_ASO ");
			parameters.put("ID_GRADO_ASO", filtros.getIdGradoAsociacion());
		}

		if (filtros.getIdTipoGestion() != null) {
			sqlFiltros.append("AND T.IDE_TIP_GESTION = :IDE_TIP_GESTION ");
			parameters.put("IDE_TIP_GESTION", filtros.getIdTipoGestion());

		}
		if (filtros.getIdTipoEntidad() != null && filtros.getIdTipoParentesco()!=null){
			sqlFiltros.append(" AND ( T.IDE_TIP_ENTIDAD = :V_IDE_TIP_ENTIDAD OR T.IDE_TIP_PARENTESCO = :V_IDE_TIP_PARENTESCO ) ");
			parameters.put("V_IDE_TIP_PARENTESCO",filtros.getIdTipoParentesco());
			parameters.put("V_IDE_TIP_ENTIDAD",filtros.getIdTipoEntidad());
		}
		else if (filtros.getIdTipoEntidad() != null){
			sqlFiltros.append(" AND T.IDE_TIP_ENTIDAD = :V_IDE_TIP_ENTIDAD ");
			parameters.put("V_IDE_TIP_ENTIDAD",filtros.getIdTipoEntidad());
		}
		else if (filtros.getIdTipoParentesco() != null){
			sqlFiltros.append(" AND T.IDE_TIP_PARENTESCO = :V_IDE_TIP_PARENTESCO ");
			parameters.put("V_IDE_TIP_PARENTESCO",filtros.getIdTipoParentesco());
		}

		if (!(!filtros.isTieneAsociacionSAS() && !filtros.isTienePactosColectivos()
				&& !filtros.isTieneReglamentoTrabajo() && filtros.getIdSolicitadoPor() == null
				&& filtros.getIdJustSolicitud() == null && filtros.getIdGradoAsociacion() == null
				&& filtros.getIdTipoGestion() == null && filtros.getIdTipoEntidad() == null
				&& filtros.getIdTipoParentesco() == null))
			tiposDocumentales.addAll(jdbcTemplate.query(sqlFiltros.toString(), parameters,
					TipoDocumentalTramiteReglasMapper.newInstance()));

		tiposDocumentales.sort(Comparator.comparing(TipoDocumentalTramite::getDescripcion));
		return tiposDocumentales;
	}

	public List<TipoDocumentalTramite> getListTipoDocumentalTramiteActivos(final Long id, int estado)
			throws SystemException {
		try {
			Object[] arguments = { id, estado };
			return template.query(Queries.LIST_TRAMITE_TPG_DOC_X_ID_ESTADO, arguments,
					TipoDocumentalTramiteMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	public int getTiposDocumentalesPorTipoDocYTramite(Long idTipoDocumental, Long idTramite)
			throws BusinessException, SystemException {
		try {
			Object[] arguments = { idTipoDocumental, idTramite };
			int result = template.queryForObject(Queries.SEARCH_TPG_DOC_X_TRAMITE, arguments, Integer.class);
			if (result > 0)
				throw new BusinessException("El tipo documental ya se encuentra asociado el tramite.");
			return result;
		} catch (DataAccessException e) {
			throw new SystemException(
					"Excepcion buscanso tipos documental  por id tipo documental y tramite: " + e.getMessage(), e);
		}
	}

	public TipoDocumental getTipoDocumentalByIdTramTipologia(final Long id) throws BusinessException, SystemException {
		try {
			Object[] arguments = { id };
			return template.queryForObject(Queries.TIPO_DOCUMENTAL_X_ID_TRA_TPG, arguments,
					TipoDocumentalMapper.newInstance());

		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("El Tipo Documental no ha sido encontrado ", e);
			throw new BusinessException("El Tipo Documental solicitado no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando Tipo Documental por id: " + e.getMessage(), e);
		}
	}

	public TipoDocumental getTipoDocumentalById(final Long id) throws BusinessException, SystemException {
		try {
			Object[] arguments = { id };
			return template.queryForObject(Queries.TIPO_DOCUMENTAL_X_ID, arguments, TipoDocumentalMapper.newInstance());
		} catch (EmptyResultDataAccessException e) {
			return TipoDocumental.newInstance().build();
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando Tipo Documental por id: " + e.getMessage(), e);
		}
	}

	public void setEstadoDocAndSubclasificacionPpdDocumento(DocumentosTramiteYServicio documentosTramiteYServicio) throws SystemException {
		Object[] args = { documentosTramiteYServicio.getIdDocumento() };
		try {
			Map<String,Object> result = template.queryForMap(Queries.ESTADO_PPD_DOCUMENTO_BY_IDE,args);
			Parametro parametro = Parametro.newInstance()
					.id(((BigDecimal) result.get("IDE_CON_CONSTANTE")).longValue())
					.codigo((String) result.get("CODIGO"))
					.nombre((String) result.get("NOMBRE"))
					.build();
			documentosTramiteYServicio.setEstadoDoc(parametro);
		}//if the query does not return exactly one row
		catch (IncorrectResultSizeDataAccessException i){

		}
		catch (DataAccessException e) {
			throw new SystemException("Excepcion obteniendo stado de un documento de PDD_DOCUMENTO " + e.getMessage(), e);
		}
		try {
			String subclasificacion = template.queryForObject(Queries.SUBCLASIFICACION_PPD_DOCUMENTO,String.class,args);
			documentosTramiteYServicio.setSubclasificacion(subclasificacion);
		}
		catch (IncorrectResultSizeDataAccessException i){

		}
		catch (DataAccessException e) {
			throw new SystemException("Excepcion obteniendo subclasificacion de un documento de PDD_DOCUMENTO " + e.getMessage(), e);
		}
	}

	public List<DocumentosTramiteYServicio> getDocumentosOtros(String numeroRadicado) throws SystemException {
		List<DocumentosTramiteYServicio> otrosDocumentos = new ArrayList<>();
		try {
			Object []args = {numeroRadicado};
			otrosDocumentos = template.query(Queries.FIND_ALL_PPD_DOCUMENTOS_NO_CARGADOS,
					args,
					(rs, rowNum) -> DocumentosTramiteYServicio
							.newInstance()
							.idDocumento(rs.getLong("ID_PDD_DOCUMENTO_AUX"))
							.nombre(rs.getString("STRNOMBREDOCUMENTO"))
							.descripcion(rs.getString("STRANOTACIONESADICIONALES"))
							.subclasificacion(rs.getString("SUBCLASIFICACION"))
							.tramiteTipologia(rs.getLong("IDE_TRA_TPG"))
							.nroRadicado("NUMRADICADO")
							.estadoDoc(
									Parametro.newInstance()
											.nombre(rs.getString("NOMBRE"))
											.codigo(rs.getString("CODIGO"))
											.id(rs.getLong("IDE_CON_CONSTANTE"))
											.build()
							)
							.other(true)
							.build());
		}
		catch (DataAccessException e){
			throw  new SystemException("Error obteniendo los documentos de PPD_DOCUMENTO_NO_CARGAOS",e);
		}
		return otrosDocumentos;
	}
}

