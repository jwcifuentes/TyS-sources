
package co.com.mintrabajo.tys.adapters.integration.client.bpm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.mintrabajo.tys.adapters.integration.client.bpm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EncabezadoSalida_QNAME = new QName("http://soaint.com/Servicios/Base/EncabezadosSOA", "EncabezadoSalida");
    private final static QName _EncabezadoEntrada_QNAME = new QName("http://soaint.com/Servicios/Base/EncabezadosSOA", "EncabezadoEntrada");
    private final static QName _ActualizarDocumentoWebResponse_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", "actualizarDocumentoWebResponse");
    private final static QName _IniciarProcesoTySResponse_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", "IniciarProcesoTySResponse");
    private final static QName _ObtenerDatosSeccional_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", "obtenerDatosSeccional");
    private final static QName _ActualizarDocumentoWeb_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", "actualizarDocumentoWeb");
    private final static QName _IniciarProcesoTyS_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", "IniciarProcesoTyS");
    private final static QName _ObtenerDatosSeccionalResponse_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", "obtenerDatosSeccionalResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.mintrabajo.tys.adapters.integration.client.bpm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActualizarDocumentoWebType }
     * 
     */
    public ActualizarDocumentoWebType createActualizarDocumentoWebType() {
        return new ActualizarDocumentoWebType();
    }

    /**
     * Create an instance of {@link ActualizarDocumentoWebResponseType }
     * 
     */
    public ActualizarDocumentoWebResponseType createActualizarDocumentoWebResponseType() {
        return new ActualizarDocumentoWebResponseType();
    }

    /**
     * Create an instance of {@link ObtenerDatosSeccionalType }
     * 
     */
    public ObtenerDatosSeccionalType createObtenerDatosSeccionalType() {
        return new ObtenerDatosSeccionalType();
    }

    /**
     * Create an instance of {@link EncabezadoSalidaType }
     * 
     */
    public EncabezadoSalidaType createEncabezadoSalidaType() {
        return new EncabezadoSalidaType();
    }

    /**
     * Create an instance of {@link EncabezadoEntradaType }
     * 
     */
    public EncabezadoEntradaType createEncabezadoEntradaType() {
        return new EncabezadoEntradaType();
    }

    /**
     * Create an instance of {@link IniciarProcesoTySType }
     * 
     */
    public IniciarProcesoTySType createIniciarProcesoTySType() {
        return new IniciarProcesoTySType();
    }

    /**
     * Create an instance of {@link ObtenerDatosSeccionalResponseType }
     * 
     */
    public ObtenerDatosSeccionalResponseType createObtenerDatosSeccionalResponseType() {
        return new ObtenerDatosSeccionalResponseType();
    }

    /**
     * Create an instance of {@link IniciarProcesoTySResponseType }
     * 
     */
    public IniciarProcesoTySResponseType createIniciarProcesoTySResponseType() {
        return new IniciarProcesoTySResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncabezadoSalidaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", name = "EncabezadoSalida")
    public JAXBElement<EncabezadoSalidaType> createEncabezadoSalida(EncabezadoSalidaType value) {
        return new JAXBElement<EncabezadoSalidaType>(_EncabezadoSalida_QNAME, EncabezadoSalidaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncabezadoEntradaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", name = "EncabezadoEntrada")
    public JAXBElement<EncabezadoEntradaType> createEncabezadoEntrada(EncabezadoEntradaType value) {
        return new JAXBElement<EncabezadoEntradaType>(_EncabezadoEntrada_QNAME, EncabezadoEntradaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarDocumentoWebResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", name = "actualizarDocumentoWebResponse")
    public JAXBElement<ActualizarDocumentoWebResponseType> createActualizarDocumentoWebResponse(ActualizarDocumentoWebResponseType value) {
        return new JAXBElement<ActualizarDocumentoWebResponseType>(_ActualizarDocumentoWebResponse_QNAME, ActualizarDocumentoWebResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarProcesoTySResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", name = "IniciarProcesoTySResponse")
    public JAXBElement<IniciarProcesoTySResponseType> createIniciarProcesoTySResponse(IniciarProcesoTySResponseType value) {
        return new JAXBElement<IniciarProcesoTySResponseType>(_IniciarProcesoTySResponse_QNAME, IniciarProcesoTySResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDatosSeccionalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", name = "obtenerDatosSeccional")
    public JAXBElement<ObtenerDatosSeccionalType> createObtenerDatosSeccional(ObtenerDatosSeccionalType value) {
        return new JAXBElement<ObtenerDatosSeccionalType>(_ObtenerDatosSeccional_QNAME, ObtenerDatosSeccionalType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarDocumentoWebType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", name = "actualizarDocumentoWeb")
    public JAXBElement<ActualizarDocumentoWebType> createActualizarDocumentoWeb(ActualizarDocumentoWebType value) {
        return new JAXBElement<ActualizarDocumentoWebType>(_ActualizarDocumentoWeb_QNAME, ActualizarDocumentoWebType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarProcesoTySType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", name = "IniciarProcesoTyS")
    public JAXBElement<IniciarProcesoTySType> createIniciarProcesoTyS(IniciarProcesoTySType value) {
        return new JAXBElement<IniciarProcesoTySType>(_IniciarProcesoTyS_QNAME, IniciarProcesoTySType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDatosSeccionalResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/TramitesBPM", name = "obtenerDatosSeccionalResponse")
    public JAXBElement<ObtenerDatosSeccionalResponseType> createObtenerDatosSeccionalResponse(ObtenerDatosSeccionalResponseType value) {
        return new JAXBElement<ObtenerDatosSeccionalResponseType>(_ObtenerDatosSeccionalResponse_QNAME, ObtenerDatosSeccionalResponseType.class, null, value);
    }

}
