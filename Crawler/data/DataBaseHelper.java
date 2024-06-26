139
https://raw.githubusercontent.com/DP-3T/dp3t-sdk-android/master-alpha/dp3t-sdk/sdk/src/calibration/java/org/dpppt/android/sdk/internal/database/DatabaseHelper.java
/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.android.sdk.internal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {

	public static SQLiteDatabase getWritableDatabase(Context context) {
		return DatabaseOpenHelper.getInstance(context).getWritableDatabase();
	}

}
