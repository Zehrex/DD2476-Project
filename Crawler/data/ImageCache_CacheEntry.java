2
https://raw.githubusercontent.com/AtomizedSoul/TulpApp/master/TulpApp.Android/obj/Release/90/android/src/crc643f46942d9dd1fff9/ImageCache_CacheEntry.java
package crc643f46942d9dd1fff9;


public class ImageCache_CacheEntry
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"";
		mono.android.Runtime.register ("Xamarin.Forms.Platform.Android.ImageCache+CacheEntry, Xamarin.Forms.Platform.Android", ImageCache_CacheEntry.class, __md_methods);
	}


	public ImageCache_CacheEntry ()
	{
		super ();
		if (getClass () == ImageCache_CacheEntry.class)
			mono.android.TypeManager.Activate ("Xamarin.Forms.Platform.Android.ImageCache+CacheEntry, Xamarin.Forms.Platform.Android", "", this, new java.lang.Object[] {  });
	}

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}
