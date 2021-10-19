
package co.com.mintrabajo.tys.adapters.integration.client.esb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.mintrabajo.tys.adapters.integration.client.esb package. 
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
    private final static QName _MensajeSalida_QNAME = new QName("http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico", "MensajeSalida");
    private final static QName _EncabezadoEntrada_QNAME = new QName("http://soaint.com/Servicios/Base/EncabezadosSOA", "EncabezadoEntrada");
    private final static QName _MensajeEntrada_QNAME = new QName("http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico", "MensajeEntrada");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.mintrabajo.tys.adapters.integration.client.esb
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link MensajeEntradaType }
     * 
     */
    public MensajeEntradaType createMensajeEntradaType() {
        return new MensajeEntradaType();
    }

    /**
     * Create an instance of {@link MensajeSalidaType }
     * 
     */
    public MensajeSalidaType createMensajeSalidaType() {
        return new MensajeSalidaType();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link MensajeSalidaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico", name = "MensajeSalida")
    public JAXBElement<MensajeSalidaType> createMensajeSalida(MensajeSalidaType value) {
        return new JAXBElement<MensajeSalidaType>(_MensajeSalida_QNAME, MensajeSalidaType.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link MensajeEntradaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico", name = "MensajeEntrada")
    public JAXBElement<MensajeEntradaType> createMensajeEntrada(MensajeEntradaType value) {
        return new JAXBElement<MensajeEntradaType>(_MensajeEntrada_QNAME, MensajeEntradaType.class, null, value);
    }

}
