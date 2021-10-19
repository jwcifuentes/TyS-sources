package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.Direccion;
import co.com.mintrabajo.tys.commons.domain.OrganizacionSindical;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class OrganizacionSindicalDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrganizacionSindicalDAO.class);

    @Autowired
    private JdbcTemplate template;

    public void crear(OrganizacionSindical organizacionSindical) throws SystemException {
        try{
            Object[] arguments = {
                    organizacionSindical.getIdRegistroTramite(),
                    organizacionSindical.getNombreOrganizacion(),
                    organizacionSindical.getSigla(),
                    organizacionSindical.getNumeroPersoneria(),
                    organizacionSindical.getAddress().getIdDireccion()
            };
            template.update(Queries.INSERT_ORGNIZACION_SINDICAL,arguments);
        }
        catch (DataAccessException d){
            throw new SystemException("Excepcion creando granizacion sindical: " + d.getMessage(), d);
        }
    }
    public void crearTodos(List<OrganizacionSindical> organizacionesSindicales) throws SystemException{
        LOGGER.info("Creando " + organizacionesSindicales.size() + " Organizaciones sindicales");
        for (OrganizacionSindical organizacionSindical: organizacionesSindicales){
            final Direccion direccion = organizacionSindical.getAddress();
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(template)
                    .withProcedureName("SP_TYS_INSERT_COR_DIRECCION");
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("COD_POSTAL", direccion.getCodigoPostal())
                    .addValue("IDE_DEPARTAMENTO",direccion.getIdDepartamento())
                    .addValue("IDE_MUNICIPIO",direccion.getIdMunicipio())
                    .addValue("IDE_PAIS",direccion.getIdPais())
                    .addValue("VAL_DIRECCION",direccion.getValDireccion())
                    .addValue("VAL_CIUDAD",direccion.getValCiudad());
            Map<String,Object> out = simpleJdbcCall.execute(in);
            Object id = out.get("RETURN_ID_DIRECCION");
            if(id instanceof BigDecimal){
                organizacionSindical.getAddress().setIdDireccion(((BigDecimal)id).longValue());
            }
            else if(id instanceof Long){
                organizacionSindical.getAddress().setIdDireccion(((Long)id));
            }
            crear(organizacionSindical);
        }
    }
}
