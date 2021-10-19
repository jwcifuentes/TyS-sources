
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerDatosDocumentoResponse_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerDatosDocumentoResponse_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoSalida"/>
 *         &lt;element name="documentos_out" type="{http://soaint.com/Servicios/Negocio/xsd/DocumentosBPM}ArrayOf_ActualizarDocumentoDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDatosDocumentoResponse_Type", propOrder = {
    "encabezadoSalida",
    "documentosOut"
})
public class ObtenerDatosDocumentoResponseType {

    @XmlElement(name = "EncabezadoSalida", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoSalidaType encabezadoSalida;
    @XmlElement(name = "documentos_out")
    protected ArrayOfActualizarDocumentoDTO documentosOut;

    /**
     * Gets the value of the encabezadoSalida property.
     * 
     * @return
     *     possible object is
     *     {@link EncabezadoSalidaType }
     *     
     */
    public EncabezadoSalidaType getEncabezadoSalida() {
        return encabezadoSalida;
    }

    /**
     * Sets the value of the encabezadoSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncabezadoSalidaType }
     *     
     */
    public void setEncabezadoSalida(EncabezadoSalidaType value) {
        this.encabezadoSalida = value;
    }

    /**
     * Gets the value of the documentosOut property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfActualizarDocumentoDTO }
     *     
     */
    public ArrayOfActualizarDocumentoDTO getDocumentosOut() {
        return documentosOut;
    }

    /**
     * Sets the value of the documentosOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfActualizarDocumentoDTO }
     *     
     */
    public void setDocumentosOut(ArrayOfActualizarDocumentoDTO value) {
        this.documentosOut = value;
    }

}
