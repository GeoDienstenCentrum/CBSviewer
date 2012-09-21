/**
 * Viewer.
 * 
 * @author mprins
 * @return {Viewer} Viewer object
 * @class {Viewer}
 */
Viewer = function() {
	/**
	 * Map object, initially null.
	 * 
	 * @private
	 * @type {OpenLayers.Map}
	 */
	var _map = null;

	/**
	 * update het informatie element met feature info.
	 * 
	 * @param {OpenLayers.Event}
	 *            evt featureinfo event
	 * @private
	 */
	function _showInfo(evt) {
		jQuery('#infoContainer').html(evt.text);
	}

	/**
	 * Publieke interface van deze klasse.
	 * 
	 * @returns {Viewer} publieke methodes
	 */
	return {

		/**
		 * Constructor, attach to the DOM.
		 * 
		 * @param config
		 *            Configratie object
		 * @constructor
		 * 
		 */
		init : function(config) {
			this.config = config;
			// merge any controls met default
			jQuery.extend(true, this.config, {
				map : {
					controls : []
				}
			});
			jQuery(window).unload(function() {
				Viewer.destroy();
			});
			OpenLayers.ImgPath = config.imgPath;
			OpenLayers.IMAGE_RELOAD_ATTEMPTS = 2;
			OpenLayers.Number.decimalSeparator = ",";

			_map = new OpenLayers.Map(this.config.mapDiv, this.config.map);
			this.addBaseMap();
			this.addControls();
			_map.zoomTo(this.config.map.initialZoom);
		},

		/**
		 * Accessor voor de kaart.
		 * 
		 * @return {OpenLayers.Map} object van deze Viewer of null als het
		 *         object niet is geinitialiseerd
		 * @deprecated probeer deze niet te gebruiken
		 */
		getMap : function() {
			return _map;
		},

		/**
		 * Controls aan de kaart hangen.
		 */
		addControls : function() {
			_map.addControl(new OpenLayers.Control.KeyboardDefaults({
				/* alleen actief als de kaart focus heeft */
				observeElement : this.config.mapDiv
			}));
			_map.addControl(new OpenLayers.Control.Zoom());
			_map.addControl(new OpenLayers.Control.Navigation({
				zoomWheelEnabled : false
			}));
			_map.addControl(new OpenLayers.Control.KeyboardClick({
				/* alleen actief als de kaart focus heeft */
				observeElement : this.config.mapDiv
			}));
			_map.addControl(new WMSGetFeatureInfo({
				eventListeners : {
					getfeatureinfo : _showInfo
				}
			}));
		},

		/**
		 * cleanup. Moet aangeroepen voor dat een eventueel DOM element van de
		 * pagina wordt verwijderd.
		 */
		destroy : function() {
			_map.destroy();
			_map = null;
		},

		/**
		 * Voeg WMS toe aan de kaart. Uitgangspunt is dat de WMS transparante
		 * PNG ondersteund.
		 * 
		 * @param {object}
		 *            wmsConfig Een object met WMS parameters. <code>
		 * {
		 * 'name' : 'cbs_inwoners_2010_per_hectare',
		 * 'url' : 'http://geodata.nationaalgeoregister.nl/cbsvierkanten100m2010/ows',
		 * 'layers' : 'cbsvierkanten100m2010:cbs_inwoners_2000_per_hectare',
		 * 'styles' : 'cbsvierkant100m_inwoners_2000'
		 * }
		 * </code>
		 */
		addWMS : function(wmsConfig) {
			var layer = new OpenLayers.Layer.WMS(wmsConfig.name, wmsConfig.url, {
				layers : wmsConfig.layers,
				styles : wmsConfig.styles,
				version : '1.3.0',
				format : 'image/png',
				transparent : true
			}, {
				isBaseLayer : false,
				visibility : true,
				singleTile : true
			});
			_map.addLayer(layer);
		},

		/**
		 * verwijder de WMS uit de kaart.
		 * 
		 * @param {string}
		 *            wmsLyrName naam van de WMS service
		 */
		removeWMS : function(wmsLyrName) {
			var lyrs = _map.getLayersByName(wmsLyrName);
			for ( var lyr = 0; lyr < lyrs.length; lyr++) {
				_map.removeLayer(lyrs[lyr]);
				lyrs[lyr].destroy();
			}
		},

		/**
		 * verwijder alle overlays. Voorlopig alleen type {OpenLayers.Layer.WMS}
		 */
		removeOverlays : function() {
			var lyrs = _map.getLayersByClass('OpenLayers.Layer.WMS');
			for ( var lyr = 0; lyr < lyrs.length; lyr++) {
				_map.removeLayer(lyrs[lyr]);
				lyrs[lyr].destroy();
			}
		},

		/**
		 * set up basemap.
		 */
		addBaseMap : function() {
			var matrixIds = [ 13 ];
			for ( var i = 0; i < 13; ++i) {
				matrixIds[i] = "EPSG:28992:" + i;
			}
			_map.addLayer(new OpenLayers.Layer.WMTS({
				name : "achtergrond",
				url : "http://geodata.nationaalgeoregister.nl/tiles/service/wmts/brtachtergrondkaart/",
				layer : "brtachtergrondkaart",
				matrixSet : 'EPSG:28992',
				matrixIds : matrixIds,
				format : 'image/png8',
				style : '_null'
			}));
		}
	};
}();
