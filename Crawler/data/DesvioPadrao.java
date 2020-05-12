2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/DesvioPadrao.java
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
 * @author gustavo
 * @author Fernando José Schmatz
 */
public class DesvioPadrao {

    private double totalQuadrado = 0.0;

    public static class Info {

        public int totaisTitulos;
        public double score;
        private double somaValorAoQuadrado;

    }

    public void gerarDesvioPadraoPlataforma() {
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

        //set.stream().filter(p -> p.getKey().equals("Android"))
        set.forEach((result) -> {

                    double mediaPlataforma = result.getValue().score / result.getValue().totaisTitulos;
                    double somaAoQuadrado  = this.getSomaAoQuadrado(result.getKey(), mediaPlataforma);
                    double totaisTitulos   = result.getValue().totaisTitulos;
                    
                    Double desvioPadrao = Math.sqrt( (somaAoQuadrado/totaisTitulos) );
                    
                    System.out.println("Desvio Padrão do "+result.getKey()
                            +" é de "+ df.format(desvioPadrao));

                });

    }

    private double getSomaAoQuadrado(String plataforma, double mediaAritmetica) {

        String line = "";

        Map<String, Info> map = new TreeMap<>();
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();

        while (line != null) {
            String[] col = line.split(";");

            String platform = col[1];
            String score = col[3];

            if (!map.containsKey(platform)) {
                Info i = new Info();
                i.score = Double.parseDouble(score);
                i.somaValorAoQuadrado = Math.pow((Double.parseDouble(score) - mediaAritmetica), 2);
                map.put(platform, i);

            } else {
                Info i = map.get(platform);
                i.score += Double.parseDouble(score);
                i.somaValorAoQuadrado += Math.pow((Double.parseDouble(score) - mediaAritmetica), 2);

            }
            line = file.readLine();

        }

        Set<Map.Entry<String, Info>> set = map.entrySet();

        set.stream().filter(p -> p.getKey().equals(plataforma))
                .forEach((result) -> {
                   totalQuadrado = result.getValue().somaValorAoQuadrado;
                });

        return totalQuadrado;
    }

}
