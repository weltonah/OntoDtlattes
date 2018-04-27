package br.com.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ConverterFile {
	
	public static Document ConverterFileToDocument(File file) {
		Document xmlfile = null;
		String nome = file.getName();
        InputStream stream;
        ZipFile zf = null;
        if(nome.contains(".zip")){
            try {
				zf = new ZipFile(file);
				ZipEntry ze = zf.entries().nextElement();
				stream = zf.getInputStream(ze);
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				xmlfile = docBuilder.parse(stream);
				ze.clone();
				zf.close();
				stream.close();
			} catch (IOException | ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        else{
			try {
				stream = new FileInputStream(file);
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				xmlfile = docBuilder.parse(stream);
				stream.close();
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        return xmlfile;
	}
		
	
	
}
