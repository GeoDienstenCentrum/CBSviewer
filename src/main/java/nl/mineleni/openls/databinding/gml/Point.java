package nl.mineleni.openls.databinding.gml;

import java.util.Vector;

import nl.mineleni.openls.XmlNamespaceConstants;

/**
 * GML Point.
 * 
 * http://schemas.opengis.net/gml/3.2.1/geometryBasic0d1d.xsd
 * 
 * <pre>
 * 
 * &lt;complexType name="PointType">
 *   &lt;complexContent>
 *     &lt;extension base="gml:AbstractGeometricPrimitiveType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="gml:pos" />
 *           &lt;element ref="gml:coordinates" />
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * 
 * </pre>
 * 
 * @author mprins
 */
public class Point implements XmlNamespaceConstants {

	/**
	 * serialisation id.
	 */
	private static final long serialVersionUID = -163863783181316506L;
	private final Vector<Pos> pos = new Vector<Pos>();;
	private String srsName;

	private boolean hasSrsName;

	public Point() {
		this.hasSrsName = false;
	}

	public void addPos(Pos p) {
		this.pos.add(p);
	}

	public Pos getPosAt(int i) {
		return this.pos.get(i);
	}

	public int getPosSize() {
		return this.pos.size();
	}

	public void setSrsName(String srsName) {
		this.hasSrsName = true;
		this.srsName = srsName;
	}

	public String getSrsName() {
		return this.srsName;
	}

	public boolean hasSrsName() {
		return this.hasSrsName;
	}

	@Override
	public String toXML() {
		final StringBuilder sb = new StringBuilder("<"
				+ XmlNamespaceConstants.OGC_GML_NAMESPACE_PREFIX + ":Point");

		if (this.hasSrsName()) {
			sb.append(" srsName=\"").append(this.getSrsName()).append("\"");
		}
		sb.append(">");
		for (final Pos p : this.pos) {
			sb.append(p.toXML());
		}
		sb.append("</" + XmlNamespaceConstants.OGC_GML_NAMESPACE_PREFIX
				+ ":Point>");
		return sb.toString();
	}
}