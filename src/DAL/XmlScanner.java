/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Poul Nielsen
 */
public class XmlScanner {
    
    Scanner scanner = new Scanner(System.in);
    /**
     * Scans the alarm xml file and returns a list with alarms in the file
     * @return a list with all alarms found in the xml file
     * @throws ParseException
     * @throws FileNotFoundException could not find the alarm xml file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException some I/O error
     */
    public ArrayList<Alarm> scanner() throws ParseException, FileNotFoundException, ParserConfigurationException, SAXException, IOException
    {
      
        String file = "res\\alarm.xml";
        ArrayList<Alarm> alarm = new ArrayList<>();
        Date date = null;
       
            
            InputStream is = new FileInputStream(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            
            doc.getDocumentElement().normalize();
            
            Element docEle = doc.getDocumentElement();
               NodeList nl = docEle.getChildNodes();
               if (nl != null && nl.getLength() > 0) 
               {
                   for (int i = 0; i < nl.getLength(); i++) 
                   {
                       if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) 
                       {
                           Element el = (Element) nl.item(i);
                           int odinNr = Integer.parseInt(el.getElementsByTagName("odinNr").item(0).getTextContent());
                           String destination = el.getElementsByTagName("destination").item(0).getTextContent();
                           String type = el.getElementsByTagName("type").item(0).getTextContent();
                           String s = el.getElementsByTagName("time").item(0).getTextContent();
                           
                           SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
               java.util.Date result = sd.parse(s);
               java.sql.Timestamp sqlDate = new java.sql.Timestamp(result.getTime());
                           
               
                Alarm a = new Alarm(odinNr, destination, type, sqlDate, false);
                alarm.add(a);
                       }
                   }
               }
                
        
        
        return alarm;
                  
    }
}
