12
https://raw.githubusercontent.com/xindoo/regex/master/src/main/java/xyz/xindoo/re/nfa/strategy/EpsilonMatchStrategy.java
package xyz.xindoo.re.nfa.strategy;

public class EpsilonMatchStrategy extends MatchStrategy {
    @Override
    public boolean isMatch(char c, String edge) {
        return true;
    }
}
