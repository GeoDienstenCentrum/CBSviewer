package nl.mineleni.cbsviewer.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import nl.mineleni.cbsviewer.util.xml.LayerDescriptor;
import nl.mineleni.cbsviewer.util.xml.LayersList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flexjson.JSONSerializer;

/**
 * AvailableLayersBean maakt de beschikbare kaarten bekend in de applicatie op
 * basis van het xml configuratie bestand {@code AvailableLayers.xml}.
 * 
 * @author mprins
 * @since 1.6
 */
public class AvailableLayersBean {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AvailableLayersBean.class);

	/**
	 * lijst met beschikbare layers.
	 */
	private List<LayerDescriptor> layers = null;

	/**
	 * default constructor.
	 */
	public AvailableLayersBean() {
		try {
			final JAXBContext jc = JAXBContext
					.newInstance("nl.mineleni.cbsviewer.util.xml");

			final Unmarshaller u = jc.createUnmarshaller();
			final File f = new File(this.getClass().getClassLoader()
					.getResource("AvailableLayers.xml").getFile());
			@SuppressWarnings("unchecked")
			final JAXBElement<LayersList> element = (JAXBElement<LayersList>) u
					.unmarshal(f);
			final LayersList layerslist = element.getValue();
			layers = layerslist.getLayerdescriptor();
		} catch (final JAXBException e) {
			LOGGER.error(
					"Er is een fout opgetreden bij het inlezen van de layers.",
					e);
		}
	}

	/**
	 * Geeft de kaarten als javascript variabele.
	 * 
	 * @return layers als json object
	 * @see #asJSON(boolean)
	 */
	public String asJSON() {
		return this.asJSON(true);
	}

	/**
	 * Geeft de kaarten als json object ({@code asVar == false}) of javascript
	 * variabele.
	 * 
	 * @param asVar
	 *            {@code true} als er een javascript variabele moet worden
	 *            gegeven.
	 * @return layers als json object
	 * 
	 * @see #asJSON()
	 */
	public String asJSON(final boolean asVar) {
		final JSONSerializer serializer = new JSONSerializer();
		final String json = serializer.exclude("class")
				.prettyPrint(LOGGER.isDebugEnabled()).serialize(this.layers);
		if (asVar) {
			return "//<![CDATA[\nvar _layers=" + json + ";\n//]]>";
		}
		return json;
	}

	/**
	 * geeft de eerste layerdescriptor met de gevraagde id.
	 * 
	 * @param name
	 *            de ID van de laag
	 * @return the layer by id
	 */
	public LayerDescriptor getLayerByID(final String name) {
		for (final LayerDescriptor desc : layers) {
			if (desc.getId().equalsIgnoreCase(name)) {
				return desc;
			}
		}
		return null;
	}

	/**
	 * geeft de eerste layerdescriptor met de gevraagde naam.
	 * 
	 * @param name
	 *            de naam van de laag
	 * @return the layer by name
	 */
	public LayerDescriptor getLayerByName(final String name) {
		for (final LayerDescriptor desc : layers) {
			if (desc.getName().equalsIgnoreCase(name)) {
				return desc;
			}
		}
		return null;
	}
}