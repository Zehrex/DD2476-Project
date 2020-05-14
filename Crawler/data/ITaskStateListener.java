50
https://raw.githubusercontent.com/iqiyi/TaskManager/master/TaskManager/src/main/java/org/qiyi/basecore/taskmanager/iface/ITaskStateListener.java
/*
 *
 * Copyright (C) 2020 iQIYI (www.iqiyi.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.qiyi.basecore.taskmanager.iface;

import org.qiyi.basecore.taskmanager.Task;

public interface ITaskStateListener {
    public static final int ON_START = 1;
    public static final int ON_FINISHED = 2;
    public static final int ON_CANCELED = 3;

    public void onTaskStateChange(Task task, int state);
}