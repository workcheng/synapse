/*
 * Copyright  1999-2004 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.sandesha2.wsrm;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.sandesha2.Sandesha2Constants;
import org.apache.sandesha2.SandeshaException;
import org.apache.sandesha2.i18n.SandeshaMessageHelper;
import org.apache.sandesha2.i18n.SandeshaMessageKeys;

/**
 * Represent the LastMessage element defined by the WSRM 1.0 epecification.
 */

public class LastMessage implements IOMRMElement {

	private String namespaceValue = null;
	
	public LastMessage(String namespaceValue) throws SandeshaException {
		if (!isNamespaceSupported(namespaceValue))
			throw new SandeshaException (SandeshaMessageHelper.getMessage(
					SandeshaMessageKeys.unknownSpec,
					namespaceValue));
		
		this.namespaceValue = namespaceValue;
	}

	public String getNamespaceValue () {
		return namespaceValue;
	}

	public Object fromOMElement(OMElement element) throws OMException {
		OMElement lastMessagePart = element.getFirstChildWithName(new QName(
				namespaceValue, Sandesha2Constants.WSRM_COMMON.LAST_MSG));
		if (lastMessagePart == null)
			throw new OMException(SandeshaMessageHelper.getMessage(
					SandeshaMessageKeys.noLastMessagePartInElement,
					element.toString()));

		return this;
	}

	public OMElement toOMElement(OMElement sequenceElement) throws OMException {

		OMFactory factory = sequenceElement.getOMFactory();
		
		OMNamespace rmNamespace = factory.createOMNamespace(namespaceValue,Sandesha2Constants.WSRM_COMMON.NS_PREFIX_RM);
		OMElement lastMessageElement = factory.createOMElement(
				Sandesha2Constants.WSRM_COMMON.LAST_MSG, rmNamespace);
		
		sequenceElement.addChild(lastMessageElement);
		return sequenceElement;
	}
	
	public boolean isNamespaceSupported (String namespaceName) {
		if (Sandesha2Constants.SPEC_2005_02.NS_URI.equals(namespaceName))
			return true;
		
		//TODO is this optional or not required.
		if (Sandesha2Constants.SPEC_2007_02.NS_URI.equals(namespaceName))
			return true;
		
		return false;
	}
}
