
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerDocumentoPorIdFilenetResponse_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerDocumentoPorIdFilenetResponse_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoSalida"/>
 *         &lt;element name="strBase64_out" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strFileName_out" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDocumentoPorIdFilenetResponse_Type", propOrder = {
    "encabezadoSalida",
    "strBase64Out",
    "strFileNameOut"
})
public class ObtenerDocumentoPorIdFilenetResponseType {

    @XmlElement(name = "EncabezadoSalida", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoSalidaType encabezadoSalida;
    @XmlElement(name = "strBase64_out", required = true)
    protected String strBase64Out;
    @XmlElement(name = "strFileName_out", required = true)
    protected String strFileNameOut;

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
     * Gets the value of the strBase64Out property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrBase64Out() {
        return strBase64Out;
    }

    /**
     * Sets the value of the strBase64Out property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrBase64Out(String value) {
        this.strBase64Out = value;
    }

    /**
     * Gets the value of the strFileNameOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFileNameOut() {
        return strFileNameOut;
    }

    /**
     * Sets the value of the strFileNameOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFileNameOut(String value) {
        this.strFileNameOut = value;
    }

}
