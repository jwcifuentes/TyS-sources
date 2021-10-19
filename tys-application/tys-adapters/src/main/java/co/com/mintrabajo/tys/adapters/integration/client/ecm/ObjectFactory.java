
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.mintrabajo.tys.adapters.integration.client.ecm package. 
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

    private final static QName _ObtenerDocumentoPorIdFilenet_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", "obtenerDocumentoPorIdFilenet");
    private final static QName _EncabezadoSalida_QNAME = new QName("http://soaint.com/Servicios/Base/EncabezadosSOA", "EncabezadoSalida");
    private final static QName _SubirDocumentoActualizarResponse_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", "subirDocumentoActualizarResponse");
    private final static QName _EncabezadoEntrada_QNAME = new QName("http://soaint.com/Servicios/Base/EncabezadosSOA", "EncabezadoEntrada");
    private final static QName _ObtenerDocumentoPorIdFilenetResponse_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", "obtenerDocumentoPorIdFilenetResponse");
    private final static QName _ObtenerDatosDocumento_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", "obtenerDatosDocumento");
    private final static QName _SubirDocumentoActualizar_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", "subirDocumentoActualizar");
    private final static QName _ObtenerDatosDocumentoResponse_QNAME = new QName("http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", "obtenerDatosDocumentoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.mintrabajo.tys.adapters.integration.client.ecm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EncabezadoEntradaType }
     * 
     */
    public EncabezadoEntradaType createEncabezadoEntradaType() {
        return new EncabezadoEntradaType();
    }

    /**
     * Create an instance of {@link ObtenerDatosDocumentoType }
     * 
     */
    public ObtenerDatosDocumentoType createObtenerDatosDocumentoType() {
        return new ObtenerDatosDocumentoType();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoPorIdFilenetResponseType }
     * 
     */
    public ObtenerDocumentoPorIdFilenetResponseType createObtenerDocumentoPorIdFilenetResponseType() {
        return new ObtenerDocumentoPorIdFilenetResponseType();
    }

    /**
     * Create an instance of {@link EncabezadoSalidaType }
     * 
     */
    public EncabezadoSalidaType createEncabezadoSalidaType() {
        return new EncabezadoSalidaType();
    }

    /**
     * Create an instance of {@link SubirDocumentoActualizarResponseType }
     * 
     */
    public SubirDocumentoActualizarResponseType createSubirDocumentoActualizarResponseType() {
        return new SubirDocumentoActualizarResponseType();
    }

    /**
     * Create an instance of {@link ActualizarDocumentoDTO }
     * 
     */
    public ActualizarDocumentoDTO createActualizarDocumentoDTO() {
        return new ActualizarDocumentoDTO();
    }

    /**
     * Create an instance of {@link ObtenerDatosDocumentoResponseType }
     * 
     */
    public ObtenerDatosDocumentoResponseType createObtenerDatosDocumentoResponseType() {
        return new ObtenerDatosDocumentoResponseType();
    }

    /**
     * Create an instance of {@link SubirDocumentoActualizarType }
     * 
     */
    public SubirDocumentoActualizarType createSubirDocumentoActualizarType() {
        return new SubirDocumentoActualizarType();
    }

    /**
     * Create an instance of {@link ArrayOfActualizarDocumentoDTO }
     * 
     */
    public ArrayOfActualizarDocumentoDTO createArrayOfActualizarDocumentoDTO() {
        return new ArrayOfActualizarDocumentoDTO();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoPorIdFilenetType }
     * 
     */
    public ObtenerDocumentoPorIdFilenetType createObtenerDocumentoPorIdFilenetType() {
        return new ObtenerDocumentoPorIdFilenetType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoPorIdFilenetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", name = "obtenerDocumentoPorIdFilenet")
    public JAXBElement<ObtenerDocumentoPorIdFilenetType> createObtenerDocumentoPorIdFilenet(ObtenerDocumentoPorIdFilenetType value) {
        return new JAXBElement<ObtenerDocumentoPorIdFilenetType>(_ObtenerDocumentoPorIdFilenet_QNAME, ObtenerDocumentoPorIdFilenetType.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SubirDocumentoActualizarResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", name = "subirDocumentoActualizarResponse")
    public JAXBElement<SubirDocumentoActualizarResponseType> createSubirDocumentoActualizarResponse(SubirDocumentoActualizarResponseType value) {
        return new JAXBElement<SubirDocumentoActualizarResponseType>(_SubirDocumentoActualizarResponse_QNAME, SubirDocumentoActualizarResponseType.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoPorIdFilenetResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", name = "obtenerDocumentoPorIdFilenetResponse")
    public JAXBElement<ObtenerDocumentoPorIdFilenetResponseType> createObtenerDocumentoPorIdFilenetResponse(ObtenerDocumentoPorIdFilenetResponseType value) {
        return new JAXBElement<ObtenerDocumentoPorIdFilenetResponseType>(_ObtenerDocumentoPorIdFilenetResponse_QNAME, ObtenerDocumentoPorIdFilenetResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDatosDocumentoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", name = "obtenerDatosDocumento")
    public JAXBElement<ObtenerDatosDocumentoType> createObtenerDatosDocumento(ObtenerDatosDocumentoType value) {
        return new JAXBElement<ObtenerDatosDocumentoType>(_ObtenerDatosDocumento_QNAME, ObtenerDatosDocumentoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubirDocumentoActualizarType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", name = "subirDocumentoActualizar")
    public JAXBElement<SubirDocumentoActualizarType> createSubirDocumentoActualizar(SubirDocumentoActualizarType value) {
        return new JAXBElement<SubirDocumentoActualizarType>(_SubirDocumentoActualizar_QNAME, SubirDocumentoActualizarType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDatosDocumentoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM", name = "obtenerDatosDocumentoResponse")
    public JAXBElement<ObtenerDatosDocumentoResponseType> createObtenerDatosDocumentoResponse(ObtenerDatosDocumentoResponseType value) {
        return new JAXBElement<ObtenerDatosDocumentoResponseType>(_ObtenerDatosDocumentoResponse_QNAME, ObtenerDatosDocumentoResponseType.class, null, value);
    }

}
