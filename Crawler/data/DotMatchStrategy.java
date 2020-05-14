12
https://raw.githubusercontent.com/xindoo/regex/master/src/main/java/xyz/xindoo/re/nfa/strategy/DotMatchStrategy.java
package xyz.xindoo.re.nfa.strategy;

public class DotMatchStrategy extends MatchStrategy {
    @Override
    public boolean isMatch(char c, String edge) {
        return c != '\n' && c != '\r';
    }
}
