12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgerp/plugin/workload/Plugin.java
package ru.bgerp.plugin.workload;

import org.w3c.dom.Document;

public class Plugin extends ru.bgcrm.plugin.Plugin {
    public static final String ID = "workload";

    public Plugin(Document doc) {
        super(doc, ID);
    }
}
