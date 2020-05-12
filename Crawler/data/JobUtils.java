18
https://raw.githubusercontent.com/WeBankFinTech/Schedulis/master/azkaban-jobtype/src/main/java/com/webank/wedatasphere/schedulis/jobtype/javautils/JobUtils.java
/*
 * Copyright 2020 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.schedulis.jobtype.javautils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class JobUtils {

  public static Logger initJobLogger() {
    Logger rootLogger = Logger.getRootLogger();
    rootLogger.removeAllAppenders();
    ConsoleAppender appender = new ConsoleAppender(new PatternLayout("%p %m\n"));
    appender.activateOptions();
    rootLogger.addAppender(appender);
    return rootLogger;
  }
}
