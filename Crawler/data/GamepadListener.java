4
https://raw.githubusercontent.com/kemusiro/jr100-emulator-v2/master/src/jp/asamomiji/emulator/device/gamepad/GamepadListener.java
/**
 * JR-100 Emulator Version 2
 *
 * Copyright (c) 2006-2020 Kenichi Miyata
 *
 * This software is released under the the MIT license
 * http://opensource.org/licenses/mit-license.php
 */
package jp.asamomiji.emulator.device.gamepad;

import java.util.EventListener;

public interface GamepadListener extends EventListener {
    public void buttonPressed(GamepadEvent e);
    public void buttonReleased(GamepadEvent e);
    public void directionChanged(GamepadEvent e);
}
