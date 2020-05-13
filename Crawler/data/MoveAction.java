15
https://raw.githubusercontent.com/CodinGame/SpringChallenge2020/master/src/main/java/com/codingame/spring2020/action/MoveAction.java
package com.codingame.spring2020.action;

import com.codingame.spring2020.Coord;
import com.codingame.spring2020.PacmanType;

public class MoveAction implements Action {

    private Coord destination;

    public Coord getTarget() {
        return destination;
    }

    public MoveAction(Coord destination, boolean activateSpeed) {
        this.destination = destination;
    }

    @Override
    public PacmanType getType() {
        return null;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.MOVE;
    }
}
