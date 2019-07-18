package br.com.reciverb2w.reciverb2w;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.reciverb2w.model.QtdSolicitacao;

public class ConsultaUrl {
	
	private static final String CSV_FILE_PATH = "dadosserv/dados_url.csv"; 
	 
	public void consultaServer() {
	/*	for(int i = 0; i < 3; i++) {*/
	    	
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
					try {

							Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
							List<QtdSolicitacao> solicitacao = new ArrayList<QtdSolicitacao>();
							
							 for(int z = 0; z < 3; z++) {
								 	count200++;
								 	Date date = new Date();
									String dataInfo = dateFormat.format(date);
									solicitacao.add(new QtdSolicitacao(dataInfo, count200, count400, count500));
									Thread.sleep(2000);
									System.out.println("Contando:" + z);
							 }
							 StatefulBeanToCsv<QtdSolicitacao> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
							 beanToCsv.write(solicitacao);
							
							writer.flush();
							writer.close();
						
					} catch (IOException e) { 
			            // TODO Auto-generated catch block 
			            e.printStackTrace(); 
			        } 
					break;
				case 400:
					
				break;
				
				
				case 500:
					
				default:
					break;
				}
	   
	    		
	    	}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
			
		
	}
}
