17
https://raw.githubusercontent.com/javmarina/Nintendo-Switch-Remote-Control/master/gui/client/src/main/java/com/javmarina/client/services/NintendoControllerService.java
package com.javmarina.client.services;

import com.studiohartman.jamepad.ControllerIndex;


/**
 * Nintendo controllers have A-B and X-Y inverted in respect to the DirectInput (DirectX) specification
 */
class NintendoControllerService extends DefaultJamepadService {

    NintendoControllerService(final ControllerIndex controllerIndex) {
        super(controllerIndex);
    }

    @Override
    void getPressedButtons(final boolean[] buttons) {
        super.getPressedButtons(buttons);
        // Invert Y and X
        final boolean x = buttons[13];
        buttons[13] = buttons[10];
        buttons[10] = x;

        // Invert A and B
        final boolean a = buttons[12];
        buttons[12] = buttons[11];
        buttons[11] = a;
    }
}
