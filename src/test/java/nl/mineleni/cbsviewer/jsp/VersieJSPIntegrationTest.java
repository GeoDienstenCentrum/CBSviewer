/*
 * Copyright (c) 2013, Dienst Landelijk Gebied - Ministerie van Economische Zaken
 * 
 * Gepubliceerd onder de BSD 2-clause licentie, zie https://github.com/MinELenI/CBSviewer/blob/master/LICENSE.md voor de volledige licentie.
 */
package nl.mineleni.cbsviewer.jsp;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.http.client.methods.HttpGet;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testcases voor versie.jsp.
 * 
 * @author mprins
 */
public class VersieJSPIntegrationTest extends JSPIntegrationTest {

	/**
	 * testcase voor versie.jsp.
	 * 
	 * @todo kijken of we de integratie tests tegen de echte war kunnen laten
	 *       draaien
	 * @throws Exception
	 */
	@Override
	@Test
	@Ignore("Mislukt vooralsnog omdat het bestand met versie informatie (/META-INF/MANIFEST.MF) niet gevonden kan worden.")
	public void testIfValidResponse() throws Exception {
		response = client.execute(new HttpGet(BASE_TEST_URL + "versie.jsp"));
		assertThat("Response status is OK.", response.getStatusLine()
				.getStatusCode(), equalTo(SC_OK));

		boilerplateValidationTests(response);
	}
}
