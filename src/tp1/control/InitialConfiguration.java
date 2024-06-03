package tp1.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.logic.Level;

public class InitialConfiguration {
		
	public static final InitialConfiguration NONE = new InitialConfiguration(); 
	private static InitialConfiguration CUSTOM;
	private List<String> descriptions;
	
	private InitialConfiguration() {}
	
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}
	
	public static InitialConfiguration readFromFile(String fileName) throws FileNotFoundException, IOException {
		List<String> initialConfiguration = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                initialConfiguration.add(line);
            }
            CUSTOM = new InitialConfiguration(initialConfiguration);
        } catch (IOException  e ) {
            System.out.println("File not found: \"" + fileName + "\"");//e.printStackTrace();
        } 
        return CUSTOM;
	}
	public boolean equals(InitialConfiguration vonfiguration) {
		return this == vonfiguration;
	}
}
