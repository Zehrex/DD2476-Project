2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/MelhorNotaPlataforma.java
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
public class MelhorNotaPlataforma
{
    public static class Info {        
        public String nomeJogoMelhorScore;
        public double melhorScore;
    }    
    
    public void gerarMelhorNotaPlataforma(){
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
                i.nomeJogoMelhorScore = nomeJogo;                
                i.melhorScore = Double.parseDouble(score);
                
                               
                map.put(platform, i);                
            }  
            else{            
                if ( Double.parseDouble(score) >= map.get(platform).melhorScore ) {
                    
                    Info novoMelhor = new Info();                
                    novoMelhor.nomeJogoMelhorScore = nomeJogo;
                    novoMelhor.melhorScore = Double.parseDouble(score);
                    
                    map.replace(platform, novoMelhor);                    
                }
            }
            line = file.readLine();
        }
        
        
        
        Set<Map.Entry<String, Info>> set = map.entrySet();
        
        set.forEach((result) -> {                          
            System.out.println("Plataforma " + result.getKey() + 
            " | Melhor Jogo -> "+result.getValue().nomeJogoMelhorScore +" com nota "+result.getValue().melhorScore);
        });
        
        
        
    }
 }

