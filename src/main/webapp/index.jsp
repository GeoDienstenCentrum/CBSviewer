<?xml version="1.0" encoding="UTF-8"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" 
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" version="2.1">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false"
		trimDirectiveWhitespaces="false" language="java" isThreadSafe="false"
		isErrorPage="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
		omit-xml-declaration="no" />

	<fmt:setBundle basename="LabelsBundle" />

	<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="nl" lang="nl">
<head>
<jsp:include page="WEB-INF/jsp/head_include.jsp" />

<c:if test="${not empty xcoord}">
	<c:set value="${xcoord}" var="xcoord" />
</c:if>

<c:if test="${not empty ycoord}">
	<c:set value="${ycoord}" var="ycoord" />
</c:if>
<c:if test="!${not empty straal}">
	<c:set value="${straal}" var="straal" />
</c:if>
<!-- meer adressen -->
<c:if test="${not empty param.xcoord}">
	<c:set value="${param.xcoord}" var="xcoord" />
</c:if>
<c:if test="${not empty param.ycoord}">
	<c:set value="${param.ycoord}" var="ycoord" />
</c:if>
<c:if test="${not empty param.straal}">
	<c:set value="${param.straal}" var="straal" />
</c:if>

<c:if test="${empty param.mapid}">
	<!-- default thema kaartlaag -->
	<c:set value="wijkenbuurten2011_thema_gemeenten2011_bevolkingsdichtheid_inwoners_per_km2" var="mapid" />
</c:if>
 <c:if test="${not empty param.mapid}">
	<c:set value="${param.mapid}" var="mapid" />
</c:if>

<jsp:include page="kaart">
	<jsp:param value="${mapid}" name="mapid" />
	<jsp:param value="${xcoord}" name="xcoord" />
	<jsp:param value="${ycoord}" name="ycoord" />
	<jsp:param value="${straal}" name="straal" />
</jsp:include>

<c:if test="${param.coreonly!=true}">
	<script type="text/javascript" charset="utf-8">
		document.documentElement.className += ' js';
	</script>
</c:if>

	<title><fmt:message key="KEY_PAG_TITEL" />: <fmt:message key="KEY_KAART_TITEL"><fmt:param value="${mapname}" /></fmt:message></title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<div id="headercenter" class="column">
				<h1 id="pagSubTitle" class="subtitel">
					<fmt:message key="KEY_KAART_TITEL"><fmt:param value="${mapname}" /></fmt:message>
				</h1>
			</div>
			<div id="headerleft" class="column">
				<img src="img/template/cbslogo-2.gif" alt="Centraal Bureau voor de Statistiek" class="logo"/>
			</div>
			<div id="headerright" class="column">
				<jsp:include page="WEB-INF/jsp/zoekformulier.jsp"/>
			</div>
		</div>

		<div id="inhoud">	
			<div id="coreContainer" class="kaartContainer">
				<!-- hier komt de statische kaart -->
				<c:if test="${not empty kaart}">
					<!-- StringConstants.MAP_CACHE_DIR -->
					<img id="coreMapImage" src="${dir}/${kaart.name}"
						alt="kaart voor thema: ${mapname}" width="512px" height="512px"
						longdesc="#featureinfo" />
					<!-- navigatie knoppen zonder javascript -->
					<jsp:include page="WEB-INF/jsp/core_nav_buttons_include.jsp" />
				</c:if>
			</div>

			<div id="kaartContainer" class="kaartContainer">
				<div id="cbsKaart" class="kaart">
					<!-- hier wordt de dynamische kaart ingehangen -->
				</div>
			</div>
			
			<jsp:include page="WEB-INF/jsp/main_menu_include.jsp" />

			<ul class="settingsPanel">
				<li id="keylegend" class="legendPanel">
					<a href="#keylegend"><fmt:message key="KEY_LEGENDA_TITEL" /></a>
					<div id="legenda" class="settingsContent">
						<p>
							<!-- plaats voor de legenda, dynamisch en statisch -->
							<c:if test="${param.coreonly==true}">
								<c:if test="${not empty legendas}">
									<c:forEach items="${legendas}" varStatus="legenda">
										<img src="${dir}/${legendas[legenda.index].name}"
											alt="legenda item" />
									</c:forEach>
								</c:if>
							</c:if>
						</p>
					</div>
				</li>
				<li id="keyfeatureinfo" class="featureinfoPanel">
					<a href="#keyfeatureinfo"><fmt:message key="KEY_INFO_TITEL" /></a>
					<div id="featureinfo" class="settingsContent">
						<c:if test="${param.coreonly==true}">
							<c:if test="${not empty featureinfo}">
								<c:out value="${featureinfo}" escapeXml="false" />
							</c:if>
						</c:if>
					</div>
				</li>
			</ul>				
		</div>
		<div id="footerWrapper">		
			<div id="copyright" class="copy">
				<fmt:message key="KEY_COPYRIGHT" />
			</div>
					
			<div id="downloadLink">
				<!-- StringConstants.REQ_PARAM_DOWNLOADLINK -->
				<c:if test="${not empty downloadLink}">
					<fmt:message var="linkText" key="KEY_LINK_DOWNLOAD"><fmt:param value="${mapname}" /></fmt:message>
					<a href="${fn:escapeXml(downloadLink)}"><c:out value="${linkText}" /></a>
				</c:if>
			</div>		

			<jsp:include page="WEB-INF/jsp/footer_include.jsp" />
		</div>
	</div>	

	<c:if test="${param.coreonly!=true}">
		<!-- scripts als laatste laden -->
		<jsp:include page="WEB-INF/jsp/javascript_include.jsp" />
	</c:if>
	<c:if test="${param.coreonly==true}">
		<!-- scripts als laatste laden -->
		<jsp:include page="WEB-INF/jsp/javascript_coreonly_include.jsp" />
	</c:if>
</body>
	</html>
</jsp:root>