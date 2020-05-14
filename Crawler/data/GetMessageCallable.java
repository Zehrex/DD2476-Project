13
https://raw.githubusercontent.com/CoboVault/cobo-vault-cold/master/app/src/main/java/com/cobo/cold/callables/GetMessageCallable.java
/*
 * Copyright (c) 2020 Cobo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * in the file COPYING.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cobo.cold.callables;

import com.cobo.cold.encryption.interfaces.CONSTANTS;
import com.cobo.cold.encryptioncore.base.Packet;
import com.cobo.cold.encryptioncore.base.Payload;

import java.util.concurrent.Callable;

public class GetMessageCallable implements Callable<String> {
    @Override
    public String call() {
        try {

            final Packet packet = new Packet.Builder(CONSTANTS.METHODS.GET_MESSAGE)
                    .build();
            final Callable<Packet> callable = new BlockingCallable(packet);
            final Packet result = callable.call();
            final Payload payload = result.getPayload(CONSTANTS.TAGS.MESSAGE);

            if (payload != null) {
                return payload.toHex();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}