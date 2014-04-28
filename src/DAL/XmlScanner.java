/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
      
        String file = "alarm_1.xml";
        
        try {
            
            InputStream is = new FileInputStream(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            
            NodeList odinNr = doc.getElementsByTagName("odinNr");
            NodeList destination = doc.getElementsByTagName("destination");
            NodeList type = doc.getElementsByTagName("type");
            NodeList time = doc.getElementsByTagName("time");
            
            
        
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.out.println("Nope, not going there!");
        }
        
        
    }
    
}
