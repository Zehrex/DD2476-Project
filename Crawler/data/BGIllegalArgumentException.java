12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/model/BGIllegalArgumentException.java
package ru.bgcrm.model;

public class BGIllegalArgumentException extends BGMessageException {
    public BGIllegalArgumentException() {
        super("Ошибка параметров запроса.");
    }
}
