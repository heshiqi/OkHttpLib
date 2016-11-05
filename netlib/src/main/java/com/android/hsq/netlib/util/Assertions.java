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
package com.android.hsq.netlib.util;

import android.text.TextUtils;

/**
 * Provides methods for asserting the truth of expressions and properties.
 */
public final class Assertions {

  private Assertions() {}


  /**
   * Ensures that an object reference is not null.
   *
   * @param reference An object reference.
   * @return The non-null reference that was validated.
   * @throws NullPointerException If {@code reference} is null.
   */
  public static <T> T checkNotNull(T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }



  /**
   * Ensures that a string passed as an argument to the calling method is not null or 0-length.
   *
   * @param string A string.
   * @return The non-null, non-empty string that was validated.
   * @throws IllegalArgumentException If {@code string} is null or 0-length.
   */
  public static String checkNotEmpty(String string) {
    if (TextUtils.isEmpty(string)) {
      throw new IllegalArgumentException();
    }
    return string;
  }

}
