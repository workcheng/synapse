/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *   * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.synapse.aspects.statistics;

/**
 *
 */
public class StatisticsLog {

    public static final int ENDPOINT_STATISTICS = 0;

    public static final int PROXY_SERVICE_STATISTICS = 1;

    public static final int MEDIATOR_STATISTICS = 2;

    private String id;
    private long startTime = -1;
    private long endTime = -1;

    public StatisticsLog(String id) {
        this(id, System.currentTimeMillis());
    }

    public StatisticsLog(String id, long startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getProcessingTime() {
        return this.endTime - this.startTime;
    }
}