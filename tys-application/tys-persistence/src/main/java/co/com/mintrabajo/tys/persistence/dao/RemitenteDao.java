package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.Remitente;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RemitenteDao {
    private static final Logger LOGGER = LogManager.getLogger(RemitenteDao.class);
    @Autowired
    private JdbcTemplate template;


    public void actualizarNombresApellidos(String numRadicado, Remitente remitente) throws SystemException {
    	try {
        LOGGER.info("Actualizando datos remitente");
        Object [] args = {
                remitente.getPrimerNombre(), remitente.getSegundoNombre(),
                remitente.getPrimerApellido(), remitente.getSegundoApellido(),
                numRadicado
        };
        template.update(Queries.UPDATE_REMITENTE_NOMBRE_APELLIDOS_BY_NRORADICADO,args);
    	} catch (DataAccessException e) {
			System.out.println("Error update, usando SP UPDATE_REMITENTE_NOMBRE_APELLIDOS_BY_NRORADICADO");
			throw new SystemException("Excepcion creando registo documentos proceso mensaje tys: " + e.getMessage(), e);
		}
    }
}
