package co.com.mintrabajo.tys.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.tys.boundary.CrearTiposDocumentalesTramites;
import co.com.mintrabajo.tys.commons.constants.FlujoAdminTramite;
import co.com.mintrabajo.tys.commons.domain.FiltrosTiposDocumentales;
import co.com.mintrabajo.tys.commons.domain.TipoDocumental;
import co.com.mintrabajo.tys.commons.domain.TipoDocumentalTramite;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.TipoDocumentalDAO;

/**
 * Created by jrodriguez on 08/11/2017.
 */

@RestController
@RequestMapping(value = {URLSecuritySchema.TIPOS_DOCUMENTALES_ADMIN, URLSecuritySchema.TIPOS_DOCUMENTALES_PUBLIC}, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
public class TipoDocumentalWebController {

    private static final Logger LOGGER = LogManager.getLogger(TipoDocumentalWebController.class);

    @Autowired
    private TipoDocumentalDAO tipoDocumentalDAO;

    @Autowired
    private CrearTiposDocumentalesTramites crearTiposDocumentalesTramites;

    public TipoDocumentalWebController() {
        super();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listarTiposDocumentales() throws SystemException {
        LOGGER.info("listar tipos Documentales ");
        int estado = 1;
        List<TipoDocumental> tiposDocumentales = tipoDocumentalDAO.listarTiposDocumentalesActivos(estado);
        return ResponseEntity.ok().body(tiposDocumentales);
    }

    @RequestMapping(value = "/{flujo}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@PathVariable("flujo") final FlujoAdminTramite flujo,
                                   @RequestBody final TipoDocumentalTramite tipoDocumentalTramite) throws SystemException, BusinessException {
        LOGGER.info("crear tipo documental tramite con flujo {} : {}", flujo, tipoDocumentalTramite);
        crearTiposDocumentalesTramites.procesar(tipoDocumentalTramite, flujo);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getListTiposDocumentalesTramite(@PathVariable("id") final Long id) throws SystemException {
        LOGGER.info("consultando tipos documentales tramite por id {}", id);
        return ResponseEntity.ok().body(tipoDocumentalDAO.getListTipoDocumentalTramite(id));
    }
    
    @RequestMapping(value = "/tramite/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getListTiposDocumentalesTramiteReglas(@PathVariable("id")final Long id)throws SystemException{
    	 LOGGER.info("consultando tipos documentales tramite por id tramite con reglas {}", id);
         return ResponseEntity.ok().body(tipoDocumentalDAO.getListTiposDocumentalesTramiteReglas(id));
    }

    @RequestMapping(value = "/{idTd}/{idT}", method = RequestMethod.GET)
    public ResponseEntity<?> getTiposDocumentalesPorTipoDocYTramite(@PathVariable("idTd") final Long idTipoDocumental,
                                                                    @PathVariable("idT") final Long idTramite) throws BusinessException, SystemException {
        LOGGER.info("consultando tipos documentales tramite por idTipoDocumental : ",
                idTipoDocumental + " idTramite :" + idTramite);
        return ResponseEntity.ok(tipoDocumentalDAO.getTiposDocumentalesPorTipoDocYTramite(idTipoDocumental, idTramite));
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListTipoDocumentalTramitesByFilter(@RequestBody final FiltrosTiposDocumentales filtros)throws SystemException{
    	 LOGGER.info("consultar tipo documental tramite con  filtros : {}", filtros);
    	return ResponseEntity.ok(tipoDocumentalDAO.getListTipoDocumentalTramitesByFilter(filtros));
    }

}
