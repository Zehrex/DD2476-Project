2
https://raw.githubusercontent.com/devwckd/wckd-vips/master/src/main/java/co/wckd/vips/adapter/StringArrayToPermissionsAdapter.java
package co.wckd.vips.adapter;

import co.wckd.boilerplate.adapter.ObjectAdapter;
import co.wckd.vips.entity.section.Permissions;

import java.util.Arrays;

public class StringArrayToPermissionsAdapter implements ObjectAdapter<String[], Permissions> {

    @Override
    public Permissions adapt(String[] strings) {
        Permissions permissions = new Permissions();

        if (strings.length != 0)
            permissions.setSection(Arrays.asList(strings));

        return permissions;
    }
}
