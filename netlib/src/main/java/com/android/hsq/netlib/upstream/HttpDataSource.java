/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.hsq.netlib.upstream;

import com.android.hsq.netlib.request.DataRequest;
import com.android.hsq.netlib.util.Exceptions;

import java.util.List;
import java.util.Map;

public interface HttpDataSource extends DataSource {

  @Override
  long syncOpen(DataRequest dataSpec) throws Exceptions.HttpDataSourceException;

  @Override
  void open(DataRequest dataSpec) throws Exceptions.HttpDataSourceException;

  @Override
  void close() throws Exceptions.HttpDataSourceException;


  /**
   * 获取响应头
   *
   * @return The response headers, or {@code null} if response headers are unavailable.
   */
  Map<String, List<String>> getResponseHeaders();

}
