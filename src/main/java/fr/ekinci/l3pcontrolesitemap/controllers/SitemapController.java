package fr.ekinci.l3pcontrolesitemap.controllers;

import fr.ekinci.l3pcontrolesitemap.models.Urlset;
import fr.ekinci.l3pcontrolesitemap.services.SitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class SitemapController {

	private final SitemapService sitemapService;

	@Autowired
	public SitemapController(SitemapService sitemapService) {
		this.sitemapService = sitemapService;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/sitemap", produces = MediaType.APPLICATION_JSON_VALUE)
	public Urlset getSitemap() throws IOException {

		try (InputStream in = getClass().getResourceAsStream("/sitemap.xml") ) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];

			for(int size = -1; (size = in.read(buffer)) != -1; ){
				baos.write(buffer, 0, size);
			}

			return sitemapService.deserialize(baos.toString(StandardCharsets.UTF_8.name()));
		}
	}
}
