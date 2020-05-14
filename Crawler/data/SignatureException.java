139
https://raw.githubusercontent.com/DP-3T/dp3t-sdk-android/master-alpha/dp3t-sdk/sdk/src/main/java/org/dpppt/android/sdk/backend/SignatureException.java
/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.android.sdk.backend;

public class SignatureException extends io.jsonwebtoken.security.SignatureException {

	public SignatureException(String message) {
		super(message);
	}

	public SignatureException(String message, Throwable cause) {
		super(message, cause);
	}

}
