9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/axon-support/src/main/java/engineering/everest/lhotse/axon/replay/ReplayCompletionAware.java
package engineering.everest.lhotse.axon.replay;

public interface ReplayCompletionAware {

    default void replayCompleted() {
    }
}
