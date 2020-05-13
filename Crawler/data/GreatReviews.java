2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/GreatReviews.java
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
 * @author gustavo
 */
public class GreatReviews
{
    public static class Info {
        public int reviews;
        public int totaisTitulos;        
        public int totaisGreat;        
    }    
    
    public void gerarPorcentagemGreatReviewsPlataforma(){
        String line = "";
        int contaReviews = 0;

        Map<String, Info> map = new TreeMap<>();
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();

        while (line != null) {
            String[] col = line.split(";");
            contaReviews++;
            
            String platform = col[1];
            String score_phrase = col[2];
            
            if (!map.containsKey(platform)) {
                Info i = new Info();
                i.totaisTitulos = 1;
                if(score_phrase.equals("Great")){
                    i.totaisGreat++;
                }
                map.put(platform, i);
            } else {
                Info i = map.get(platform);
                if(score_phrase.equals("Great")){
                    i.totaisGreat++;
                }
                i.totaisTitulos++;
            }

            line = file.readLine();

        }
        
        Set<Map.Entry<String, Info>> set = map.entrySet();
        
        set.forEach((result) -> {
            System.out.println("Plataforma " + result.getKey() + " | Porcentagem Great Reviews " + ((result.getValue().totaisGreat*100)/result.getValue().totaisTitulos)+"%");
        });
    }
}
