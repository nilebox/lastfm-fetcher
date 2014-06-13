package ru.nilebox.lastfm.fetcher;

import ru.nilebox.lastfm.fetcher.config.LastFmConfig;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.nilebox.lastfm.fetcher.data.ArtistInfo;
import ru.nilebox.lastfm.fetcher.exceptions.LastFmInvalidConfigException;

/**
 *
 * @author nile
 */
public class LastFmClient {
	private static final Logger logger = LoggerFactory.getLogger(LastFmClient.class);
	
	private LastFmConfig config = new LastFmConfig();
	private String key;	
	
	public void setConfig(LastFmConfig config) {
		this.config = config;
	}

	public void setKey(String key) {
		this.key = key;
	}	
	
	private String getSearchUrl(String query) throws URIException {
		StringBuilder b = new StringBuilder();
		b.append(config.getSearchUrl())
				.append("&").append(config.getQueryParameterName())
				.append("=").append(URIUtil.encodeQuery(query))
				.append("&").append(config.getKeyParameterName())
				.append("=").append(key);
		return b.toString();
	}
	
	private String getDescriptionUrl(String id) throws URIException {
		StringBuilder b = new StringBuilder();
		b.append(config.getDescriptionUrl())
				.append("&").append(config.getIdParameterName())
				.append("=").append(id)
				.append("&").append(config.getKeyParameterName())
				.append("=").append(key);
		return b.toString();
	}
	
	private Document fetch(String url) throws IOException, ParserConfigurationException, SAXException {
		logger.info("Fetching last.fm url: " + url);
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		client.executeMethod(method);
		InputStream responseStream = method.getResponseBodyAsStream();

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setByteStream(responseStream);

		Document doc = db.parse(is);
		return doc;
	}

	public ArtistInfo findArtist(String query) throws URIException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, LastFmInvalidConfigException {
		if (key == null)
			throw new LastFmInvalidConfigException("last.fm API key is not specified");
		
		String url = getSearchUrl(query);
		Document doc = fetch(url);
		
		String id = (String) config.getIdExpression().evaluate(doc, XPathConstants.STRING);
		if (id == null || id.isEmpty())
			return null;
		String name = (String) config.getNameExpression().evaluate(doc, XPathConstants.STRING);
		if (name == null || name.isEmpty())
			return null;
		String description = retrieveDescription(id);
		String smallImageUrl = (String) config.getSmallImageExpression().evaluate(doc, XPathConstants.STRING);
		String mediumImageUrl = (String) config.getMediumImageExpression().evaluate(doc, XPathConstants.STRING);
		String largeImageUrl = (String) config.getLargeImageExpression().evaluate(doc, XPathConstants.STRING);
		
		if (smallImageUrl != null)
			smallImageUrl = smallImageUrl.trim();
		if (mediumImageUrl != null)
			mediumImageUrl = mediumImageUrl.trim();
		if (largeImageUrl != null)
			largeImageUrl = largeImageUrl.trim();
		
		ArtistInfo info = new ArtistInfo();
		info.setLastFmId(id);
		info.setName(name);
		info.setDescription(description);
		info.setSmallImageUrl(smallImageUrl);
		info.setMediumImageUrl(mediumImageUrl);
		info.setLargeImageUrl(largeImageUrl);
		return info;
	}	
	
	private String retrieveDescription(String lastfmId) throws URIException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		String url = getDescriptionUrl(lastfmId);
		Document doc = fetch(url);

		String description = (String) config.getDescriptionExpression().evaluate(doc, XPathConstants.STRING);
		
		if (description == null)
			return null;
		
		description = StringEscapeUtils.unescapeXml(description);
		String tail = "<a";
		int tailStart = description.indexOf(tail);
		if (tailStart >= 0) {
			description = description.substring(0, tailStart).trim();
		}
		
		if (description.length() == 0)
			return null;
		
		return description;
	}
	
}
