12
https://raw.githubusercontent.com/xindoo/regex/master/src/main/java/xyz/xindoo/re/nfa/strategy/CharMatchStrategy.java
package xyz.xindoo.re.nfa.strategy;

public class CharMatchStrategy extends MatchStrategy{

    @Override
    public boolean isMatch(char c, String edge) {
        return edge.charAt(0) == c;
    }
}
