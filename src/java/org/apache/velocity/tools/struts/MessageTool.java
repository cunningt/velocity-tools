/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Velocity", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.velocity.tools.struts;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import org.apache.struts.util.MessageResources;
import org.apache.struts.action.*;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.view.context.ViewContext;
import org.apache.velocity.tools.view.tools.ViewTool;


/**
 * <p>View tool that provides methods to render Struts message resources.</p>
 * <p><pre>
 * Template example(s):
 *   #if( $msg.exists('greeting') )
 *     $msg.greeting
 *   #end
 *
 * Toolbox configuration:
 * &lt;tool&gt;
 *   &lt;key&gt;msg&lt;/key&gt;
 *   &lt;scope&gt;request&lt;/scope&gt;
 *   &lt;class&gt;org.apache.velocity.tools.struts.MessageTool&lt;/class&gt;
 * &lt;/tool&gt;
 * </pre></p>
 *
 * <p>This tool should only be used in the request scope.</p>
 *
 * @author <a href="mailto:sidler@teamup.com">Gabe Sidler</a>
 * @since VelocityTools 1.0
 * @version $Id: MessageTool.java,v 1.9 2003/11/06 06:19:44 nbubna Exp $
 */
public class MessageTool extends MessageResourcesTool
{

    /**
     * Default constructor. Tool must be initialized before use.
     */
    public MessageTool()
    {}


    /**
     * Looks up and returns the localized message for the specified key.
     * The user's locale is consulted to determine the language of the
     * message.
     *
     * @param key message key
     *
     * @return the localized message for the specified key or
     * <code>null</code> if no such message exists
     */
    public String get(String key)
    {
        return get(key, (Object[])null);
    }

    /**
     * Looks up and returns the localized message for the specified key.
     * The user's locale is consulted to determine the language of the
     * message.
     *
     * @param key message key
     * @param bundle The bundle name to look for.
     *
     * @return the localized message for the specified key or
     * <code>null</code> if no such message exists
     * @since VelocityTools 1.1
     */
    public String get(String key, String bundle)
    {
        return get(key, bundle, (Object[])null);
    }


    /**
     * Looks up and returns the localized message for the specified key.
     * Replacement parameters passed with <code>args</code> are
     * inserted into the message. The user's locale is consulted to
     * determine the language of the message.
     *
     * @param key message key
     * @param args replacement parameters for this message
     *
     * @return the localized message for the specified key or
     * <code>null</code> if no such message exists
     */
    public String get(String key, Object args[])
    {
       return get(key, null, args);
    }

    /**
     * Looks up and returns the localized message for the specified key.
     * Replacement parameters passed with <code>args</code> are
     * inserted into the message. The user's locale is consulted to
     * determine the language of the message.
     *
     * @param key message key
     * @param bundle The bundle name to look for.
     * @param args replacement parameters for this message
     * @since VelocityTools 1.1
     * @return the localized message for the specified key or
     * <code>null</code> if no such message exists
     */
    public String get(String key, String bundle, Object args[])
    {
        MessageResources res = getResources(bundle);
        if (res == null)
        {
            return null;
        }

        // return the requested message
        if (args == null)
        {
            return res.getMessage(this.locale, key);
        }
        else
        {
            return res.getMessage(this.locale, key, args);
        }
    }

    /**
     * Same as {@link #get(String key, Object[] args)}, but takes a
     * <code>java.util.List</code> instead of an array. This is more
     * Velocity friendly.
     *
     * @param key message key
     * @param args replacement parameters for this message
     *
     * @return the localized message for the specified key or
     * <code>null</code> if no such message exists
     */
    public String get(String key, List args)
    {
        return get(key, args.toArray());
    }

    /**
     * Same as {@link #get(String key, Object[] args)}, but takes a
     * <code>java.util.List</code> instead of an array. This is more
     * Velocity friendly.
     *
     * @param key message key
     * @param bundle The bundle name to look for.
     * @param args replacement parameters for this message
     * @since VelocityTools 1.1
     * @return the localized message for the specified key or
     * <code>null</code> if no such message exists
     */
    public String get(String key, String bundle, List args)
    {
        return get(key, bundle, args.toArray());
    }


    /**
     * Checks if a message string for a specified message key exists
     * for the user's locale.
     *
     * @param key message key
     *
     * @return <code>true</code> if a message strings exists,
     * <code>false</code> otherwise
     */
    public boolean exists(String key)
    {
        return exists(key, null);
    }

    /**
     * Checks if a message string for a specified message key exists
     * for the user's locale.
     *
     * @param key message key
     * @param bundle The bundle name to look for.
     * @since VelocityTools 1.1
     * @return <code>true</code> if a message strings exists,
     * <code>false</code> otherwise
     */
    public boolean exists(String key, String bundle)
    {
        MessageResources res = getResources(bundle);
        if (res == null)
        {
            return false;
        }

        // Return the requested message presence indicator
        return res.isPresent(this.locale, key);
    }


    /**
     * Returns the user's locale. If a locale is not found, the default
     * locale is returned.
     */
    public Locale getLocale()
    {
        return this.locale;
    }

}
