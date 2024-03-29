22
https://raw.githubusercontent.com/geekidea/spring-cloud-plus/master/scp-common/scp-common-core/src/main/java/io/geekidea/cloud/common/core/util/UUIDUtil.java
/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.cloud.common.core.util;

import java.util.UUID;

/**
 * @author geekidea
 * @date 2018-11-08
 */
public class UUIDUtil {

    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
