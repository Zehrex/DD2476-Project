4
https://raw.githubusercontent.com/kemusiro/jr100-emulator-v2/master/src/jp/asamomiji/emulator/device/gamepad/GamepadEvent.java
/**
 * JR-100 Emulator Version 2
 *
 * Copyright (c) 2006-2020 Kenichi Miyata
 *
 * This software is released under the the MIT license
 * http://opensource.org/licenses/mit-license.php
 */
package jp.asamomiji.emulator.device.gamepad;

import net.java.games.input.Controller;

public class GamepadEvent {
    private Controller controller;

    protected GamepadEvent(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }
}
