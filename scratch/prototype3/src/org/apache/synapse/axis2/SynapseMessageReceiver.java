/*
 * Copyright 2004,2005 The Apache Software Foundation.
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
package org.apache.synapse.axis2;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;

import org.apache.axis2.engine.MessageReceiver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.synapse.SynapseEnvironment;
import org.apache.synapse.SynapseMessage;

/**
 *
 * 
 * <p>
 * 
 * This is used to "catch" messages in Axis2 and pass them to Synapse for processing.
 *
 */
public class SynapseMessageReceiver implements MessageReceiver {

	private Log log = LogFactory.getLog(getClass());

	public void receive(MessageContext mc) throws AxisFault {
		log.debug("receiving message");
		SynapseEnvironment env = Axis2SynapseEnvironmentFinder
				.getSynapseEnvironment(mc);
		SynapseMessage smc = new Axis2SynapseMessage(mc);
		env.injectMessage(smc);
	}
}
