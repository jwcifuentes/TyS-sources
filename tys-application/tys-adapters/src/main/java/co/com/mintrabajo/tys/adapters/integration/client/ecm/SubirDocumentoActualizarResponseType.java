
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subirDocumentoActualizarResponse_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subirDocumentoActualizarResponse_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoSalida"/>
 *         &lt;element name="strCodigo_out" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strMensaje_out" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subirDocumentoActualizarResponse_Type", propOrder = {
    "encabezadoSalida",
    "strCodigoOut",
        "strMensajeOut"
})
public class SubirDocumentoActualizarResponseType {

    @XmlElement(name = "EncabezadoSalida", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoSalidaType encabezadoSalida;
    @XmlElement(name = "strCodigo_out")
    protected String strCodigoOut;
    @XmlElement(name = "strMensaje_out")
    protected String strMensajeOut;

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
     * Gets the value of the strCodigoOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCodigoOut() {
        return strCodigoOut;
    }

    /**
     * Sets the value of the strCodigoOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCodigoOut(String value) {
        this.strCodigoOut = value;
    }

    /**
     * Gets the value of the strMensajeOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrMensajeOut() {
        return strMensajeOut;
    }

    /**
     * Sets the value of the strMensajeOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrMensajeOut(String value) {
        this.strMensajeOut = value;
    }

}
