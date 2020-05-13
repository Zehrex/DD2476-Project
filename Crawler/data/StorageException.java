18
https://raw.githubusercontent.com/WeBankFinTech/Schedulis/master/azkaban-spi/src/main/java/azkaban/spi/StorageException.java
/*
 * Copyright 2017 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package azkaban.spi;

/**
 * Super class to capture any exceptions related to {@link Storage}
 */
public class StorageException extends AzkabanException {

  public StorageException(final String message) {
    this(message, null);
  }

  public StorageException(final Throwable throwable) {
    this(null, throwable);
  }

  public StorageException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public StorageException(final String message, final Throwable cause,
      final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
