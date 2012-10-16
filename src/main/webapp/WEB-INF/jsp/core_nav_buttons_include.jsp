<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" version="2.1">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false"
		import="nl.mineleni.cbsviewer.util.LabelsBundle, nl.mineleni.cbsviewer.util.StringConstants"
		trimDirectiveWhitespaces="true" language="java" isThreadSafe="false"
		isErrorPage="false" />

	<jsp:scriptlet>LabelsBundle RESOURCES = new LabelsBundle();</jsp:scriptlet>

	<fmt:parseNumber var="straal" value="${param.straal}" type="number"
		integerOnly="true" parseLocale="en-US" />
	<fmt:parseNumber var="xcoord" value="${param.xcoord}" type="number"
		integerOnly="true" parseLocale="en-US" />
	<fmt:parseNumber var="ycoord" value="${param.ycoord}" type="number"
		integerOnly="true" parseLocale="en-US" />

	<fmt:formatNumber var="zoomin" maxFractionDigits="0"
		value="${straal/2}" minFractionDigits="0" type="number" pattern="###" />

	<c:set var="naarLinks" value="${xcoord-zoomin}" />
	<c:set var="naarRechts" value="${xcoord+zoomin}" />
	<c:set var="naarBoven" value="${ycoord+zoomin}" />
	<c:set var="naarBeneden" value="${xcoord-zoomin}" />

	<!-- 
	include bestand met navigatie knoppen voor de core versie, 
	let op dat dit bestand vanuit de root van de webapplicatie wordt ingevoegd  
	 -->

	<div id="kaartnavi" class="kaartnavi">
		<jsp:expression>RESOURCES.getString("KEY_NAVIGATIE_TITEL")</jsp:expression>

		<form id="zoomFormulier" action="#" method="get"
			title="in- en uitzoomen van de kaart en verschuiven van de zoeklocatie"
			name="zoomFormulier">

			<!-- knoppen -->
			<fieldset>
				<legend><jsp:expression>RESOURCES.getString("KEY_NAVIGATIE_ZOOM_LEGEND")</jsp:expression></legend>
				<label for="zoomUitBtn">zoom uit</label>
				<button id="zoomUitBtn" type="submit" name="straal"
					value="${straal*2}" title="zoom uit">−</button>

				<label for="zoomInBtn">zoom in</label>
				<button id="zoomInBtn" type="submit" name="straal" value="${zoomin}"
					title="zoom in">+</button>
			</fieldset>
			<fieldset>
				<legend><jsp:expression>RESOURCES.getString("KEY_NAVIGATIE_SCHUIF_LEGEND")</jsp:expression></legend>
				<label for="schuifLinksBtn">schuif naar links</label>
				<button id="schuifLinksBtn" type="submit" name="xcoord"
					value="${naarLinks}" title="naar links">←</button>

				<label for="schuifRechtsBtn">schuif naar rechts</label>
				<button id="schuifRechtsBtn" type="submit" name="xcoord"
					value="${naarRechts}" title="naar rechts">→</button>

				<label for="schuifOmhoogBtn">schuif naar boven</label>
				<button id="schuifOmhoogBtn" type="submit" name="ycoord"
					value="${naarBoven}" title="omhoog">↑</button>

				<label for="schuifOmlaagBtn">schuif naar beneden</label>
				<button id="schuifOmlaagBtn" type="submit" name="ycoord"
					value="${naarBeneden}" title="omlaag">↓</button>
			</fieldset>
			<input type="hidden" name="coreonly" value="true" />

			<!-- defaults -->
			<input type="hidden" name="straal" value="${straal}" /> <input
				type="hidden" name="xcoord" value="${xcoord}" /> <input
				type="hidden" name="ycoord" value="${ycoord}" />

		</form>

	</div>
</jsp:root>