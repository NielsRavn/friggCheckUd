/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Poul Nielsen
 */
public class XmlScanner {
    
    
    public Alarm scanner()
    {
      
        String file = "res\\alarm.xml";
        Alarm alarm = null;
        Date date = null;
        try {
            
            InputStream is = new FileInputStream(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            
            int odinNr = Integer.parseInt(xpath.evaluate("/alarm/odinNr", doc));
            String destination = xpath.evaluate("/alarm/destination", doc);
            String type = xpath.evaluate("/alarm/type", doc);
            String s=  xpath.evaluate("/alarm/time", doc);
            
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
               java.util.Date result = sd.parse(s);
               java.sql.Date sqlDate = new java.sql.Date(result.getTime());
                           
               alarm = new Alarm(odinNr, destination, type, date);           
            
        
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException | ParseException ex) {
            
        }
        
        return alarm;
                  
    }
}
