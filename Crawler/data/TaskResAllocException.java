23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/core/src/main/java/com/webank/wedatasphere/exchangis/executor/exception/TaskResAllocException.java
/*
 *
 *  Copyright 2020 WeBank
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.exchangis.executor.exception;

/**
 * @author davidhua
 * 2019/4/26
 */
public class TaskResAllocException extends RuntimeException{
    public TaskResAllocException(){
        super();
    }

    public TaskResAllocException(String message){ super(message);}

    public TaskResAllocException(String message, Throwable e){
        super(message, e);
    }
}