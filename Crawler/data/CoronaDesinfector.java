60
https://raw.githubusercontent.com/Jeka1978/coronadesinfectorlifedemo/master/src/main/java/com/epam/CoronaDesinfector.java
package com.epam;

/**
 * @author Evgeny Borisov
 */

public class CoronaDesinfector {

    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;


    public void start(Room room) {
        announcer.announce("Начинаем дезинфекцию, всё вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Рискните зайти обратно");
    }

    private void desinfect(Room room){
        System.out.println("зачитывается молитва: 'корона изыди!' - молитва прочитана, вирус низвергнут в ад");
    }
}
