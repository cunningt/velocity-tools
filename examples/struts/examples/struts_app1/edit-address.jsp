<%--
 * Copyright 2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id: edit-address.jsp,v 1.3 2004/02/20 13:31:45 marino Exp $
--%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
    <head>
    	<title>App1</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    </head>

    <body>

        <p><b><bean:message key="edit"/> (JSP <bean:message key="version"/>)</b></p>

        <html:form name="address" method="POST" scope="session" type="examples.app1.AddressBean" action="address1.do">
        <input type="hidden" name="action" value="list">

        <table width="300" border="1" cellspacing="" cellpadding="5">
        	<tr>
        		<td><bean:message key="firstname"/>:</td>
        		<td><html:text name="address" property="firstname"/></td>
        	</tr>
        	<tr>
        		<td><bean:message key="lastname"/></td>
        		<td><html:text name="address" property="lastname"/></td>
        	</tr>
        	<tr>
        		<td><bean:message key="street"/></td>
        		<td><html:text name="address" property="street"/></td>
        	</tr>
        	<tr>
        		<td><bean:message key="zip"/></td>
        		<td><html:text name="address" property="zip"/></td>
        	</tr>
        	<tr>
        		<td><bean:message key="city"/></td>
        		<td><html:text name="address" property="city"/></td>
        	</tr>
        	<tr>
        		<td><bean:message key="country"/></td>
        		<td><html:text name="address" property="country"/></td>
        	</tr>
        </table>
        <br>

        <input type="submit" name="Submit" value="<bean:message key="save"/>  " onclick="address.action.value='save'; document.address.submit(); return false;">
        <input type="submit" name="Submit2" value="<bean:message key="cancel"/>"  onclick="address.action.value='list'; document.address.submit(); return false;">

        </html:form>

        <br>
        <html:link forward="editAddressSrcJsp">Template</html:link>

    </body>
</html>
