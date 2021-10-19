package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.DatosDocumentoAdicional;
import co.com.mintrabajo.tys.commons.domain.DocumentoAdicional;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.DocumentosAdicionalesMapper;

@Component
public class DocumentosAdicionalesDAO {

	private static final Logger LOGGER = LogManager.getLogger(MunicipiosDAO.class);

	@Autowired
	private JdbcTemplate template;

	public void crear(final DocumentoAdicional documentoAdicional) throws SystemException {
		try {
			LOGGER.info("crear documentos adicionales asociados al tramite", documentoAdicional);
			Object[] arguments = { documentoAdicional.getIdResgistroTramite(),
					documentoAdicional.getIdDocumentosProducido(), documentoAdicional.getFechaCreacion(),
					documentoAdicional.getUsuarioCrea(), documentoAdicional.getIp() };
			template.update(Queries.INSERT_DOCUMENTO_ADICIONAL, arguments);
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion creando tramites: " + e.getMessage(), e);
		}
	}

	public List<DatosDocumentoAdicional> listarDocumentosAdicioanesTramite(final String numeroRadicado)
			throws SystemException {
		try {
			LOGGER.info("consultar documentos adicionales asociados al tramite  {}", numeroRadicado);
			Object[] arguments = { numeroRadicado };
			return template.query(Queries.DOCUMENTOS_ADCIONALES_POR_TRAMITE, arguments,
					DocumentosAdicionalesMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultar documentos adicionales asociados al tramite  {} " + e.getMessage(), e);
		}
	}

}
