package br.com.DAO;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

public class ReadFile {

	// public static File PegarFile() {
	// try {
	// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// File file = new
	// File(classLoader.getResource("static/OWL/datalattes.owl").getFile());
	// // File file = new ClassPathResource("static/OWL/datalattes.owl").getFile();
	// File ont = new File(System.getProperty("user.dir") + "/datalattesFull.owl");
	// FileUtils.copyFile(file, ont);
	//
	// return ont;
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }

	public static File PegarFile(String NomeArq) {
		try {
			// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			// System.out.println(System.getProperty("user.dir") +
			// "/src/main/resources/static/OWL/datalattes.owl");
			
			
			// File file = new
			//File(classLoader.getResource("static/OWL/datalattes.owl").getFile());
			File file = new ClassPathResource("/static/OWL/datalattes.owl").getFile();
			// System.out.println(file.getAbsolutePath());
			// File file = new File("/home/welton/exeOntoDt/OWL/datalattes.owl");
			File ont = new File(System.getProperty("user.dir") + "/" + NomeArq);
			// if (!ont.exists())
			// ont.mkdirs();
			// File ont = new File("/home/welton" + "/" + NomeArq);
			FileUtils.copyFile(file, ont);
			return ont;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
