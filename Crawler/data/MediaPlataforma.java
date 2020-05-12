2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/MediaPlataforma.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.GeradorLista;

import br.univates.tarefareviewv2.SimpleReader;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 *
 * @author Fernando José Schmatz
 * @author gustavo
 */
public class MediaPlataforma
{
    public static class Info {
        public int reviews;
        public int totaisTitulos;
        public double score;
    }    
    
    public void gerarMediaAritmeticaPlataforma(){
        String line = "";
        int contaReviews = 0;
        DecimalFormat df = new DecimalFormat("#.##");

        Map<String, Info> map = new TreeMap<>();
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();

        while (line != null) {
            String[] col = line.split(";");
            contaReviews++;
            
            String platform = col[1];
            String score = col[3];
            
            if (!map.containsKey(platform)) {
                Info i = new Info();
                i.totaisTitulos = 1;
                i.score = Double.parseDouble(score);
                map.put(platform, i);
            } else {
                Info i = map.get(platform);
                i.score += Double.parseDouble(score);
                i.totaisTitulos++;
            }

            line = file.readLine();

        }
        
        Set<Map.Entry<String, Info>> set = map.entrySet();
        
        set.forEach((result) -> {
            String valorFormatado = df.format(result.getValue().score / result.getValue().totaisTitulos);                
            System.out.println("Plataforma " + result.getKey() + 
            " | Media de Notas -> " + valorFormatado);
        });
        
        
        
    }
}
