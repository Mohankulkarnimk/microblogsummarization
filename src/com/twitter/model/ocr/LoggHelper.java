/**
 * Copyright @ 2015 Quan Nguyen
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
package com.twitter.model.ocr;

/**
 * Helper for logging.
 *
 * @author O.J. Sousa Rodrigues
 */
public class LoggHelper extends Exception {

    @Override
    public String toString() {
        LoggerConfig.INSTANCE.loadConfig();

        StackTraceElement[] sTrace = this.getStackTrace();
        String className = sTrace[0].getClassName();

        return className;
    }
}
