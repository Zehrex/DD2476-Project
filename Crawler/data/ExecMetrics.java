18
https://raw.githubusercontent.com/WeBankFinTech/Schedulis/master/azkaban-exec-server/src/main/java/azkaban/execapp/ExecMetrics.java
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
 */

package azkaban.execapp;

import azkaban.metrics.MetricsManager;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class ExecMetrics is in charge of collecting metrics from executors.
 */
@Singleton
public class ExecMetrics {

  private final MetricsManager metricsManager;

  @Inject
  ExecMetrics(final MetricsManager metricsManager) {
    this.metricsManager = metricsManager;
    setupStaticMetrics();
  }

  public void setupStaticMetrics() {

  }

  public void addFlowRunnerManagerMetrics(final FlowRunnerManager flowRunnerManager) {
    this.metricsManager
        .addGauge("EXEC-NumRunningFlows", flowRunnerManager::getNumRunningFlows);
    this.metricsManager
        .addGauge("EXEC-NumQueuedFlows", flowRunnerManager::getNumQueuedFlows);
    this.metricsManager
        .addGauge("EXEC-ProjectDirCacheHitRatio", flowRunnerManager::getProjectDirCacheHitRatio);
  }
}