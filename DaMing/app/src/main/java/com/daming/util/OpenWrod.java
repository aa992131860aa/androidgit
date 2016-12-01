package com.daming.util;

import java.io.File;

import android.content.Intent;
import android.net.Uri;

public class OpenWrod {
	 public static Intent getWordFileIntent( String param )

	  {

	    Intent intent = new Intent("android.intent.action.VIEW");

	    intent.addCategory("android.intent.category.DEFAULT");

	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    Uri uri = Uri.fromFile(new File(param));

	    intent.setDataAndType(uri, "application/msword");

	    return intent;

	  }
}
