package br.com.reciverb2w.reciverb2w;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.reciverb2w.model.QtdSolicitacao;

public class ConsultaUrl {
	
	private static final String CSV_FILE_PATH = "dadosserv/dados_url.csv"; 
	 
	@SuppressWarnings("resource")
	public void consultaServer() {
		for(int i = 0; i < 30; i++) {
	    	
			try {
				
				
	    		Integer count200 = 0;
	    	 	Integer count400 = 0;
	    	 	Integer count500 = 0;
	    		

	    		
	    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		
	    		Client client = Client.create();
	    		
	    		WebResource webUrl = client.resource("https://desafioperformance.b2w.io/bairros");
	    		
	    		ClientResponse response = webUrl.accept("application/json")
	                    .get(ClientResponse.class);   
	    		
				
	    		switch (response.getStatus()) {
				
	    		case 200:
					//JSONObject objectJson = new JSONObject();
					try {
						
						File file = new File(CSV_FILE_PATH);
						//Scanner sc = new Scanner(System.in);
						
						FileWriter outputfile = new FileWriter(file); 
						  CSVWriter writer = new CSVWriter(outputfile, ';', 
                                  CSVWriter.NO_QUOTE_CHARACTER, 
                                  CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                                  CSVWriter.DEFAULT_LINE_END); 
						  
							List<QtdSolicitacao> solicitacao = new ArrayList<QtdSolicitacao>();
							List<String[]> data = new ArrayList<String[]>(); 
							Date date = new Date();
							
							String dataInfo = dateFormat.format(date);
							
							data.add(new String[] {dataInfo});
							
							solicitacao.add(new QtdSolicitacao(dataInfo, count200, count400, count500));
							
							System.out.println("Enter no of rows"); 
							
							//int noOfRow = Integer.parseInt(); 
							System.out.println("Enter Data");
							
			
						

						writer.flush();
						writer.close();
						//System.out.println("oioio");
					} catch (IOException e) { 
			            // TODO Auto-generated catch block 
			            e.printStackTrace(); 
			        } 
					break;
				case 201:
					

				default:
					break;
				}
	    		/*
				 * if(response.getStatus() == 200) { System.out.println("ok"+ " 200"); }else {
				 * throw new RuntimeException("Failed : HTTP erro code: " +
				 * response.getStatus()); }
				 */
	    		
	    		//String output = response.getEntity(String.class);
	    		//System.out.println("Output from Server ....\n");
	    		//System.out.println(output);
	    		Thread.sleep(2000);
	    	}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
		}
	}
}
