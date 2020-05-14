125
https://raw.githubusercontent.com/DP-3T/dp3t-sdk-backend/develop/dpppt-backend-sdk/dpppt-backend-sdk-data/src/test/java/org/dpppt/backend/sdk/data/config/PostgresDataConfig.java
/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.backend.sdk.data.config;

import org.dpppt.backend.sdk.data.util.SingletonPostgresContainer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("postgres")
public class PostgresDataConfig {

    @Bean
    public DataSource dataSource() {
        final SingletonPostgresContainer instance = SingletonPostgresContainer.getInstance();
        instance.start();

        return DataSourceBuilder.create()
            .driverClassName(instance.getDriverClassName())
            .url(instance.getJdbcUrl())
            .username(instance.getUsername())
            .password(instance.getPassword())
            .build();
    }

    @Bean
    public String dbType() {
        return "pgsql";
    }

}
