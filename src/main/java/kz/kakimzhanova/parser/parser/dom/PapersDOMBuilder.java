package kz.kakimzhanova.parser.parser.dom;

import kz.kakimzhanova.parser.entity.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PapersDOMBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Paper> papers;
    private DocumentBuilder documentBuilder;
    public PapersDOMBuilder(){
        this.papers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.WARN, e);
        }
    }

    public Set<Paper> getPapers(){
        return papers;
    }

    public void buildSetPapers(String fileName){
        Document document = null;
        try{
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList papersList = root.getElementsByTagName("paper");
            for (int i = 0; i < papersList.getLength(); i++){
                Element paperElement = (Element) papersList.item(i);
                Paper paper = buildPaper(paperElement);
                papers.add(paper);
            }
        } catch (SAXException | IOException e) {
            logger.log(Level.WARN, e);
        }
    }

    public Paper buildPaper(Element paperElement){
        Paper paper = new Paper();
        paper.setTitle(paperElement.getAttribute("title"));
        paper.setType(paperElement.getAttribute("type"));
        if (paperElement.hasAttribute("monthly")){
            paper.setMonthly(Boolean.valueOf(paperElement.getAttribute("monthly")));
        }
        Paper.Chars chars = paper.getChars();
        Element charsElement = (Element) paperElement.getElementsByTagName("chars").item(0);
        chars.setColor(Boolean.valueOf(getElementTextContent(charsElement, "color")));
        chars.setGlossy(Boolean.valueOf(getElementTextContent(charsElement,"glossy")));
        chars.setVolume(Integer.parseInt(getElementTextContent(charsElement, "volume")));
        chars.setSubscription(Boolean.valueOf(getElementTextContent(charsElement, "subscription")));
        return paper;
    }

    public static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
