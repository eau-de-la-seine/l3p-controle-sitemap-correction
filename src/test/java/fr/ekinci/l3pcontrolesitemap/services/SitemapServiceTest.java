package fr.ekinci.l3pcontrolesitemap.services;


import fr.ekinci.l3pcontrolesitemap.models.Url;
import fr.ekinci.l3pcontrolesitemap.models.Urlset;

import org.junit.Assert;
import org.junit.Test;

public class SitemapServiceTest {
	private final SitemapService sitemapService = new SitemapService();

	private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
			"<urlset>\n" +
			"    <url>\n" +
			"        <loc>A</loc>\n" +
			"        <lastmod>B</lastmod>\n" +
			"    </url>\n" +
			"    <url>\n" +
			"        <loc>C</loc>\n" +
			"        <lastmod>D</lastmod>\n" +
			"    </url>\n" +
			"</urlset>\n";

	@Test
	public void serialize_nominal() {
		// GIVEN
		Urlset sitemap = new Urlset();
		Url url1 = new Url();
		url1.setLoc("A");
		url1.setLastmod("B");

		Url url2 = new Url();
		url2.setLoc("C");
		url2.setLastmod("D");

		sitemap.getUrl().add(url1);
		sitemap.getUrl().add(url2);

		// WHEN
		String actual = sitemapService.serialize(sitemap);

		// THEN
		Assert.assertEquals(XML, actual);
	}

	@Test
	public void deserialize_nominal() {
		// GIVEN
		Urlset sitemap = new Urlset();
		Url url1 = new Url();
		url1.setLoc("A");
		url1.setLastmod("B");

		Url url2 = new Url();
		url2.setLoc("C");
		url2.setLastmod("D");

		sitemap.getUrl().add(url1);
		sitemap.getUrl().add(url2);

		// WHEN
		Urlset actual = sitemapService.deserialize(XML);

		// THEN
		Urlset expected = sitemap;
		Assert.assertEquals(expected, actual);
	}
}
