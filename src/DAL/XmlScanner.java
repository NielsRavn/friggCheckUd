/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Poul Nielsen
 */
public class XmlScanner {
    
    
    public XmlScanner()
    {
        
    }
    
    private void scanner()
    {
      
        String xmlUrl = "https://www.dropbox.com/s/hf45d70vn7mtteh/alarm_1.xml";
        
        try {
            URL url = new URL(xmlUrl);
            InputStream is = url.openStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            
            NodeList location = doc.getElementsByTagName("currency");
        
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.out.println("Nope, not going there!");
        }
        
        
    }
    
}
