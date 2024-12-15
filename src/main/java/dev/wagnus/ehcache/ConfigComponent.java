package dev.wagnus.ehcache;

import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

public class ConfigComponent {
    private String ehcacheURL;

    public void setEhcacheURL(String ehcacheURL) {
        this.ehcacheURL = ehcacheURL;
    }

    public XmlConfiguration xmlConfiguration() {
        final URL myUrl = getClass().getResource(ehcacheURL);
        return new XmlConfiguration(myUrl);
    }
}
