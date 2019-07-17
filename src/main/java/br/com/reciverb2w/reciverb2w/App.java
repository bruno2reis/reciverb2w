package br.com.reciverb2w.reciverb2w;

import java.util.Date;

import br.com.reciverb2w.reciverb2w.converteminuto.ConvertMinut;

public class App 
{
    public static void main( String[] args ){
    	
    	
    	
    	//long tempoInicio = System.currentTimeMillis();
    	
    	ConvertMinut covert = new ConvertMinut();
    	
    	int minutoRetornoInicio = covert.minutConv();
    	
    	ConsultaUrl consulta = new ConsultaUrl(); 
    	
    	//600000 vezes
		/* for(int minutoRetornoInicio; ) */
    	
    	for(int i = 0; i < 30; i++) {
    		
    		try {
    			consulta.consultaServer();
    			Thread.sleep(2000);
    		}catch (InterruptedException e)
			
    		{	//Erro na execução.
				System.out.println("ERRO");
			}
			
			//Mostra a contagem.
			System.out.println("Contando:" + i);
		}
    	
    	//Date minutoRetornoFinal = covert.minutConv();
    	
    	
    	    
    }
    
   
}
