74
https://raw.githubusercontent.com/rayfowler/rotp-public/master/src/rotp/model/incidents/DemandTributeIncident.java
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rotp.model.incidents;

import rotp.model.empires.Empire;

public class DemandTributeIncident extends DiplomaticIncident {
    private static final long serialVersionUID = 1L;
    final int empMe;
    final int empYou;
    private boolean demanding = false;
    public static DemandTributeIncident create(Empire e1, Empire e2, boolean b) {
        DemandTributeIncident inc = new DemandTributeIncident(e1, e2, b);
        return inc;
    }
    private DemandTributeIncident(Empire e1, Empire e2, boolean b) {
        empMe = e1.id;
        empYou = e2.id;
        demanding = b;
        severity = demanding ? 0 : 10;

        dateOccurred = galaxy().currentYear();
        duration = 3;
    }
    @Override
    public String title()               { return text("INC_DEMANDED_TRIBUTE_TITLE"); }
    @Override
    public String key()                 { return "Demand Tribute"; }
    @Override
    public String decode(String s) {
        String s1 = s.replace("[year]", str(dateOccurred));
        s1 = galaxy().empire(empMe).replaceTokens(s1, "my");
        s1 = galaxy().empire(empYou).replaceTokens(s1, "your");
        return s1;
    }
}
