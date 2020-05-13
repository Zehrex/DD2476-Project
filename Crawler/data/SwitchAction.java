15
https://raw.githubusercontent.com/CodinGame/SpringChallenge2020/master/src/main/java/com/codingame/spring2020/action/SwitchAction.java
package com.codingame.spring2020.action;

import com.codingame.spring2020.PacmanType;

public class SwitchAction implements Action {

    private PacmanType type;

    public PacmanType getNewType() {
        return type;
    }

    public SwitchAction(PacmanType type) {
        this.type = type;
    }

    @Override
    public PacmanType getType() {
        return type;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.SWITCH;
    }
}
