
package co.com.mintrabajo.tys.adapters.integration.client.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EncabezadoEntradaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncabezadoEntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PeticionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PeticionFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncabezadoEntradaType", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", propOrder = {
    "peticionId",
    "canal",
    "peticionFecha",
    "usuario"
})
public class EncabezadoEntradaType {

    @XmlElement(name = "PeticionId", required = true)
    protected String peticionId;
    @XmlElement(name = "Canal", required = true)
    protected String canal;
    @XmlElement(name = "PeticionFecha", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar peticionFecha;
    @XmlElement(name = "Usuario", required = true)
    protected String usuario;

    /**
     * Gets the value of the peticionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeticionId() {
        return peticionId;
    }

    /**
     * Sets the value of the peticionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeticionId(String value) {
        this.peticionId = value;
    }

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Gets the value of the peticionFecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPeticionFecha() {
        return peticionFecha;
    }

    /**
     * Sets the value of the peticionFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPeticionFecha(XMLGregorianCalendar value) {
        this.peticionFecha = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

}
