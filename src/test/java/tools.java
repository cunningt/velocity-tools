/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.Locale;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;
import org.apache.velocity.tools.config.FactoryConfiguration;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.apache.velocity.tools.view.ViewResourceTool;

/**
 * Test java configuration class
 *
 * @author Nathan Bubna
 * @version $Id: tools.java 511959 2007-02-26 19:24:39Z nbubna $
 */
public class tools
{
    public static FactoryConfiguration getConfiguration()
    {
        EasyFactoryConfiguration easy = new EasyFactoryConfiguration();
        easy.number("version", 2.0);
        easy.toolbox("request")
                .property("locale", Locale.US)
                .tool(ViewResourceTool.class);
        easy.toolbox("application")
                .tool("calc", MathTool.class)
                .tool(NumberTool.class)
                    .property("locale", Locale.FRENCH);
        return easy;
    }
}
