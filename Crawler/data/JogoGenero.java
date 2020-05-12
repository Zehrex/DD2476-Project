2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/JogoGenero.java
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
public class JogoGenero
{
    public static class Info {
        public int reviews;
        public int totaisTitulos;
    }    
    
    public void gerarListaJogoGenero(){
        String line = "";

        Map<String, Info> map = new TreeMap<>();
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();

        while (line != null) {
            String[] col = line.split(";");

            String titulo = col[0];
            String Platform = col[1];
            Double Score = Double.parseDouble(col[3]);
            String genero = col[4];
            
            if (!map.containsKey(genero)) {
                Info i = new Info();
                i.totaisTitulos = 1;
                map.put(genero, i);
            } else {
                Info i = map.get(genero);
                i.totaisTitulos++;
            }

            line = file.readLine();

        }
        Set<Map.Entry<String, Info>> set = map.entrySet();
        
        
        set.forEach((result) -> {
            System.out.println("Gênero " + result.getKey() + " | Total de Titulos " + result.getValue().totaisTitulos);
        });
    }
}



