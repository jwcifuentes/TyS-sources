package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.DocumentoTramite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.ProcesoMensajeTyS;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;

@Component
public class ProcesoMensajesDAO {

	private static final Logger LOGGER = LogManager.getLogger(ProcesoMensajesDAO.class);

	@Autowired
	private JdbcTemplate template;

	public void crear(final ProcesoMensajeTyS procesoMensajeTyS) throws SystemException {
		try {

			LOGGER.info("procesar insert proceso mensaje tys {} :", procesoMensajeTyS);
			Object[] arguments = { procesoMensajeTyS.getCodDependencia(), procesoMensajeTyS.getNumeroRadicado(),
					procesoMensajeTyS.getDocumento(), procesoMensajeTyS.getIdTipoDocumental(),
					procesoMensajeTyS.getCodPlantilla(), procesoMensajeTyS.getNombreDocumento(),
					procesoMensajeTyS.getIdDocumento(), procesoMensajeTyS.getIdFilenet(), procesoMensajeTyS.getOrigen(),
					procesoMensajeTyS.getEstado(), procesoMensajeTyS.getFechaCreacion(),
					procesoMensajeTyS.getIdUsuarioCrea() };

			//LOGGER.info(" ---- arguments --- : {}", arguments);
			template.update(Queries.INSERT_PROCESO_MENSAJE_TYS, arguments);
		} catch (DataAccessException e) {
			System.out.println("Error insertando, usando SP INSERT_PROCESO_MENSAJE_TYS");
			throw new SystemException("Excepcion creando registo documentos proceso mensaje tys: " + e.getMessage(), e);
		}
	}

	public void actualizarSubclasificaccionDocumento(DocumentoTramite documento) throws SystemException {
		LOGGER.info("Actualizando subclasificacion de documento");
		try {
			Object [] args = { documento.getSubclasificacion(), documento.getIdDocumento()};
			template.update(Queries.UPDATE_SUBCLASIFICACION_PPD_DOCUMENTO_BY_IDE,args);
		}
		catch (DataAccessException e){
			throw new SystemException("Error actualizando la subclasificacion",e.getCause());
		}
	}

	public void actualizarSubclasificaccionDocumentoNoCargado(DocumentoTramite documento) throws SystemException {
		LOGGER.info("Actualizando subclasificacion de documento no cargado");
		try {
			Object [] args = { documento.getSubclasificacion(), documento.getNroRadicado()};
			template.update(Queries.UPDATE_SUBCLASIFICACION_PPD_DOCUMENTO_NO_CARGADO_BY_IDE,args);
		}
		catch (DataAccessException e){
			throw new SystemException("Error actualizando la subclasificacion de documento no cargado",e.getCause());
		}
	}
}
