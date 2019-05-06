package fr.ekinci.l3pcontrolesitemap.services;

import fr.ekinci.l3pcontrolesitemap.models.Urlset;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class SitemapService {

	public Urlset deserialize(String xml) {
		try (StringReader sr = new StringReader(xml)) {
			return JAXB.unmarshal(sr, Urlset.class);
		}
	}

	public String serialize(Urlset sitemap) {
		try(StringWriter sw = new StringWriter()) {
			JAXB.marshal(sitemap, sw);
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
