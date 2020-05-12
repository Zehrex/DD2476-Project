2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/PiorNotaPlataforma.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.GeradorLista;

import br.univates.tarefareviewv2.SimpleReader;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Fernando José Schmatz
 * @author Gustavo Steinhoefel
 */
public class PiorNotaPlataforma
{
     public static class Info {        
        public String nomeJogoPiorScore;
        public double piorScore;
    }    
    
    public void gerarPiorNotaPlataforma(){
        String line = "";        
        
        
        Map<String, Info> map = new TreeMap<>();
              
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();
              
                
        while (line != null) {
            String[] col = line.split(";");
            
            String nomeJogo = col[0];
            String platform = col[1]; //KEY
            String score = col[3];
                        
            
            if (!map.containsKey(platform)) {
                Info i = new Info();
                i.nomeJogoPiorScore = nomeJogo;                
                i.piorScore = Double.parseDouble(score);
                                               
                map.put(platform, i);                
            }  
            else{            
                if ( Double.parseDouble(score) <= map.get(platform).piorScore ) {
                    
                    Info novoMelhor = new Info();                
                    novoMelhor.nomeJogoPiorScore = nomeJogo;
                    novoMelhor.piorScore = Double.parseDouble(score);
                    
                    map.replace(platform, novoMelhor);                    
                }
            }
            line = file.readLine();
        }
        
        
        
        Set<Map.Entry<String, Info>> set = map.entrySet();
        
        set.forEach((result) -> {                            
            System.out.println("Plataforma " + result.getKey() + 
            " | Pior Jogo -> "+result.getValue().nomeJogoPiorScore +" com nota "+result.getValue().piorScore);
        });
        
        
        
    }
}
