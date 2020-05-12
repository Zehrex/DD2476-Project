3
https://raw.githubusercontent.com/sudiptog81/notetaker-android/master/app/src/main/java/pro/ghosh/notetaker/DataCallback.java
package pro.ghosh.notetaker;

import java.util.List;

interface DataCallback {
  void onCallback(List<Note> notes);
}