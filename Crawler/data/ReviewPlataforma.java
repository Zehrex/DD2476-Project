2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/ReviewPlataforma.java
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
public class ReviewPlataforma
{ 

/**
 *
 * @author Fernando José Schmatz
 */

    public static class Info {
        public int reviews;
        public int totaisTitulos;        
        public int totaisGreat;
        
    }    
    
    public void gerarListaPlataformaContaJogos(){
        String line = "";        

        Map<String, Info> map = new TreeMap<>();
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();

        while (line != null) {
            String[] col = line.split(";");
                        
            String platform = col[1];            
                        
            if (!map.containsKey(platform)) {
                Info i = new Info();
                i.totaisTitulos = 1;                
                map.put(platform, i);
            } else {
                Info i = map.get(platform);
                i.totaisTitulos++;
            }

            line = file.readLine();

        }
        Set<Map.Entry<String, Info>> set = map.entrySet();
        
        
        set.forEach((result) -> {
            System.out.println("Plataforma " + result.getKey() + " | Total de Titulos " + result.getValue().totaisTitulos);
        });
    }
}





