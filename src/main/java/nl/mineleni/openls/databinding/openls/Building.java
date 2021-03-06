package nl.mineleni.openls.databinding.openls;

import nl.mineleni.openls.XmlNamespaceConstants;

/**
 * OLS Building.
 * 
 * http://schemas.opengis.net/ols/1.2.0/ADT.xsd
 * 
 * <pre>
 * 
 * &lt;element name="Building" type="xls:BuildingLocatorType" 
 * 		substitutionGroup="xls:_StreetLocation"&gt;
 *   &lt;annotation&gt;
 *     &lt;documentation&gt;An addressable place; normally a location on a street: number, 
 *     subdivision name and/or building name.&lt;/documentation&gt;
 *   &lt;/annotation&gt;
 * &lt;/element&gt;
 * &lt;complexType name="BuildingLocatorType"&gt;
 *   &lt;annotation&gt;
 *     &lt;documentation&gt;A type of AbstractStreetLocatorType&lt;/documentation&gt;
 *   &lt;/annotation&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="xls:AbstractStreetLocatorType"&gt;
 *       &lt;attribute name="number" type="string" use="optional" /&gt;
 *       &lt;attribute name="subdivision" type="string" use="optional" /&gt;
 *       &lt;attribute name="buildingName" type="string" use="optional" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * 
 * </pre>
 * 
 * @author mprins
 */
public class Building implements XmlNamespaceConstants {
	/**
	 * serialization id.
	 */
	private static final long serialVersionUID = -6760538060544745111L;
	/**
	 * building number.
	 */
	private String number;

	/** number subdivision. */
	private String subdivision;
	/**
	 * name of the building.
	 */
	private String buildingName;

	/** The has number. */
	private boolean hasNumber;

	/** The has subdivision. */
	private boolean hasSubdivision;

	/** The has building name. */
	private boolean hasBuildingName;

	/**
	 * Instantiates a new building.
	 */
	public Building() {
		this.hasNumber = false;
		this.hasSubdivision = false;
		this.hasBuildingName = false;
	}

	/**
	 * Sets the number.
	 * 
	 * @param number
	 *            the new number
	 */
	public void setNumber(final String number) {
		this.hasNumber = true;
		this.number = number;
	}

	/**
	 * Gets the number.
	 * 
	 * @return the number
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * Checks for number.
	 * 
	 * @return true, if successful
	 */
	public boolean hasNumber() {
		return this.hasNumber;
	}

	/**
	 * Sets the subdivision.
	 * 
	 * @param subdivision
	 *            the new subdivision
	 */
	public void setSubdivision(final String subdivision) {
		this.hasSubdivision = true;
		this.subdivision = subdivision;
	}

	/**
	 * Gets the subdivision.
	 * 
	 * @return the subdivision
	 */
	public String getSubdivision() {
		return this.subdivision;
	}

	/**
	 * Checks for subdivision.
	 * 
	 * @return true, if successful
	 */
	public boolean hasSubdivision() {
		return this.hasSubdivision;
	}

	/**
	 * Sets the building name.
	 * 
	 * @param buildingName
	 *            the new building name
	 */
	public void setBuildingName(final String buildingName) {
		this.hasBuildingName = true;
		this.buildingName = buildingName;
	}

	/**
	 * Gets the building name.
	 * 
	 * @return the building name
	 */
	public String getBuildingName() {
		return this.buildingName;
	}

	/**
	 * Checks for building name.
	 * 
	 * @return true, if successful
	 */
	public boolean hasBuildingName() {
		return this.hasBuildingName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.mineleni.openls.databinding.common.XmlNamespaceConstants#toXML()
	 */
	@Override
	public String toXML() {
		String xml = "<" + XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX
				+ ":Building";
		if (this.hasNumber()) {
			xml += " number=\"" + this.getNumber() + "\"";
		}
		xml += ">";
		xml += "</" + XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX
				+ ":Building>";
		return xml;
	}
}
