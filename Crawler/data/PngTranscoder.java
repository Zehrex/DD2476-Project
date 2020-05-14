10
https://raw.githubusercontent.com/GuntherRademacher/rr/master/src/main/java/de/bottlecaps/railroad/core/PngTranscoder.java
package de.bottlecaps.railroad.core;
import java.io.OutputStream;

import net.sf.saxon.s9api.XdmNode;

public interface PngTranscoder
{
  public void transcode(XdmNode e, OutputStream o) throws Exception;
}
