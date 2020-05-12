2
https://raw.githubusercontent.com/gustavost645/tarefa_reviews/master/src/main/java/br/univates/GeradorLista/MelhorJogoRacing.java
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fernando José Schmatz
 * @author Gustavo Steinhoefel
 */
public class MelhorJogoRacing
{
    public static class Info {
        public String nomeJogo;
        public String genero;
        public double SomadorScoreJogosRacing;   
        public int ContadorJogosRacing;  
    }
    
    public boolean generoRacing(String str) {
        Pattern patt = Pattern.compile("\\bRacing");
        Matcher m = patt.matcher(str);
        return m.matches();
    }
    
    public void gerarPlataformaMelhorJogoRacing(){
        String line = "";
        DecimalFormat df = new DecimalFormat("#.##");
        
                
        Map<String, Info> map = new TreeMap<>();
        File arq = new File("game-reviews.csv");
        SimpleReader file = new SimpleReader(arq.getAbsolutePath());

        line = file.readLine();
        line = file.readLine();

        while (line != null) {
            String[] col = line.split(";");
                        
            String platform = col[1];
            String score = col[3];
            String genero = col[4];
                                  
            // tem algumas variaçoes do racing tbm
            // fazer somente o racing?            
            
            if (!map.containsKey(platform) && generoRacing(genero)) {
                Info i = new Info();
                i.ContadorJogosRacing = 1;
                i.SomadorScoreJogosRacing = Double.parseDouble(score);
                map.put(platform, i);                
            } else if(generoRacing(genero)) {
                Info i = map.get(platform);                
                i.SomadorScoreJogosRacing += Double.parseDouble(score);
                i.ContadorJogosRacing++;
                map.replace(platform, i); 
            }             

            line = file.readLine();
        } 
        
        double souMelhorNota = 0;
        String melhorPlat = "";
        Set<Map.Entry<String, Info>> set = map.entrySet();
                
        for(Map.Entry<String, Info> teste : set){
            double mediaRacing = teste.getValue().SomadorScoreJogosRacing
                                 / teste.getValue().ContadorJogosRacing;

            if(mediaRacing >= souMelhorNota){
                souMelhorNota = mediaRacing;
                melhorPlat = teste.getKey();
            }        
        }
        
        String valorFormatado = df.format(souMelhorNota);
        System.out.println("A plataforma " + melhorPlat + 
            " possui os melhores jogos Racing e sua variações com uma média de notas de " + valorFormatado);
        //SteamOs vence com 8.9 (roubando!)
    } 
}

