125
https://raw.githubusercontent.com/DP-3T/dp3t-sdk-backend/develop/dpppt-backend-sdk/dpppt-backend-sdk-ws/src/test/java/org/dpppt/backend/sdk/ws/controller/TestApplication.java
/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.backend.sdk.ws.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"org.dpppt.backend.sdk.ws.config"})
@SpringBootApplication
public class TestApplication {
}
