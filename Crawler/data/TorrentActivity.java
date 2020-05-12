4
https://raw.githubusercontent.com/abdalmoniem/Movie-App/master/base/src/main/java/butter/droid/base/activities/TorrentActivity.java
package butter.droid.base.activities;

import butter.droid.base.torrent.TorrentService;

public interface TorrentActivity {

	TorrentService getTorrentService();

	void onTorrentServiceConnected();

	void onTorrentServiceDisconnected();
}