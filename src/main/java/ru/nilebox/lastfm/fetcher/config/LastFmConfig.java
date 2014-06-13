package ru.nilebox.lastfm.fetcher.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author
 * nile
 */
public class LastFmConfig {
	private String searchUrl = ConfigDefaults.DEFAULT_SEARCH_URL;
	private String descriptionUrl = ConfigDefaults.DEFAULT_DESCRIPTION_URL;
	private String keyParameterName = ConfigDefaults.DEFAULT_PARAM_KEY_NAME;
	private String queryParameterName = ConfigDefaults.DEFAULT_PARAM_QUERY_NAME;
	private String idParameterName = ConfigDefaults.DEFAULT_PARAM_ID_NAME;
	private String idXPath = ConfigDefaults.DEFAULT_XPATH_ID;
	private String descriptionXPath = ConfigDefaults.DEFAULT_XPATH_DESCRIPTION;
	private String nameXPath = ConfigDefaults.DEFAULT_XPATH_NAME;
	private String smallImageXPath = ConfigDefaults.DEFAULT_XPATH_IMG_SMALL;
	private String mediumImageXPath = ConfigDefaults.DEFAULT_XPATH_IMG_MEDIUM;
	private String largeImageXPath = ConfigDefaults.DEFAULT_XPATH_IMG_LARGE;
	private final XPath xpath = XPathFactory.newInstance().newXPath();
	private final Map<String, XPathExpression> compiledExpressions = new ConcurrentHashMap<String, XPathExpression>();

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getDescriptionUrl() {
		return descriptionUrl;
	}

	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}

	public String getIdParameterName() {
		return idParameterName;
	}

	public void setIdParameterName(String idParameterName) {
		this.idParameterName = idParameterName;
	}

	public String getKeyParameterName() {
		return keyParameterName;
	}

	public void setKeyParameterName(String keyParameterName) {
		this.keyParameterName = keyParameterName;
	}

	public String getQueryParameterName() {
		return queryParameterName;
	}

	public void setQueryParameterName(String queryParameterName) {
		this.queryParameterName = queryParameterName;
	}

	public String getIdXPath() {
		return idXPath;
	}

	public void setIdXPath(String idXPath) {
		this.idXPath = idXPath;
	}

	public String getDescriptionXPath() {
		return descriptionXPath;
	}

	public void setDescriptionXPath(String descriptionXPath) {
		this.descriptionXPath = descriptionXPath;
	}
	
	public String getNameXPath() {
		return nameXPath;
	}

	public void setNameXPath(String nameXPath) {
		this.nameXPath = nameXPath;
	}

	public String getSmallImageXPath() {
		return smallImageXPath;
	}

	public void setSmallImageXPath(String smallImageXPath) {
		this.smallImageXPath = smallImageXPath;
	}

	public String getMediumImageXPath() {
		return mediumImageXPath;
	}

	public void setMediumImageXPath(String mediumImageXPath) {
		this.mediumImageXPath = mediumImageXPath;
	}

	public String getLargeImageXPath() {
		return largeImageXPath;
	}

	public void setLargeImageXPath(String largeImageXPath) {
		this.largeImageXPath = largeImageXPath;
	}
	
	private XPathExpression getXPathExpression(String path) throws XPathExpressionException {
		XPathExpression expression = compiledExpressions.get(path);
		if (expression == null) {
			expression = xpath.compile(path);
			compiledExpressions.put(path, expression);
		}
		return expression;
	}
	
	public XPathExpression getIdExpression() throws XPathExpressionException {
		return getXPathExpression(idXPath);
	}
	
	public XPathExpression getDescriptionExpression() throws XPathExpressionException {
		return getXPathExpression(descriptionXPath);
	}
	
	public XPathExpression getNameExpression() throws XPathExpressionException {
		return getXPathExpression(nameXPath);
	}
	
	public XPathExpression getSmallImageExpression() throws XPathExpressionException {
		return getXPathExpression(smallImageXPath);
	}
	
	public XPathExpression getMediumImageExpression() throws XPathExpressionException {
		return getXPathExpression(mediumImageXPath);
	}
	
	public XPathExpression getLargeImageExpression() throws XPathExpressionException {
		return getXPathExpression(largeImageXPath);
	}
	
}
