package br.com.reciverb2w.reciverb2w;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.reciverb2w.model.QtdSolicitacao;

public class ConsultaUrl {
	
	private static final String CSV_FILE_PATH = "dadosserv/dados_url.csv"; 

	static Logger logger = Logger.getLogger(Currency.class.getName());
	
	public void consultaServer() {
		Integer count200 = 0;
		Integer count400 = 0;
		Integer count500 = 0;
	    	
			try {
			
	    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		
	    		Client client = Client.create();
	    		
	    		WebResource webUrl = client.resource("https://desafioperformance.b2w.io/bairros");
	    		
	    		ClientResponse response = webUrl.accept("application/json")
	                    .get(ClientResponse.class); 
	    		
	    		Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
				List<QtdSolicitacao> solicitacao = new ArrayList<QtdSolicitacao>();
				
	    		 for(int z = 0; z < 3; z++) {
	    			 
	    		
		    		switch (response.getStatus()) {
					
		    		case 200:						
						 	count200++;
						 	Date date = new Date();
							String dataInfo = dateFormat.format(date);
							solicitacao.add(new QtdSolicitacao(dataInfo, count200, count400, count500));
							break;
		    		case 400:
						 	count400++;
						 	Date date400 = new Date();
							String data400 = dateFormat.format(date400);
							solicitacao.add(new QtdSolicitacao(data400, count200, count400, count500));
							break;
					case 500:
						 	count500++;
						 	Date date500 = new Date();
							String data500 = dateFormat.format(date500);
							solicitacao.add(new QtdSolicitacao(data500, count200, count400, count500));
						
						
						if(count500 > count200) {
							WebResource webUrl_reiniciar = client.resource("https://desafioperformance.b2w.io/reinicia");
							ClientResponse responseReinicia = webUrl_reiniciar.accept("application/json")
						            .get(ClientResponse.class);  
							
							if(responseReinicia.getStatus() == 200) {
								
								logger.info("Serviço reiniciado com sucesso !!!");	
							}
						}
						
						break;
					}
		    		Thread.sleep(2000);
	    	}
	    		StatefulBeanToCsv<QtdSolicitacao> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
				beanToCsv.write(solicitacao);
				
				writer.flush();
				writer.close();	

	    	}catch (Exception e) {
	    		e.printStackTrace();
			}
			
		
	}
}
