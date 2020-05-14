12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/util/distr/call/InstallationCall.java
package ru.bgcrm.util.distr.call;

import java.io.File;

import ru.bgcrm.util.Preferences;

public interface InstallationCall {
    public boolean call(Preferences setup, File zip, String param);
}
