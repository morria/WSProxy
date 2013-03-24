package io.collapse.wsproxy

import java.io.IOException
import java.util.concurrent.CopyOnWriteArraySet
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.jetty.websocket.WebSocket
import org.eclipse.jetty.websocket.WebSocketServlet

/**
 * Configure in WEB-INF/web.xml as
 *
 * == Example ==
 * ```
 *    <servlet>
 *      <servlet-name>WebSocketProxy</servlet-name>
 *      <servlet-class>io.collapse.WebSocketProxyServlet</servlet-class>
 *      <load-on-startup>1</load-on-startup>
 *      <init-param>
 *        <param-name>host</param-name>
 *        <param-value>localhost</param-value>
 *      </init-param>
 *      <init-param>
 *        <param-name>port</param-name>
 *        <param-value>6667</param-name>
 *      </init-param>
 *    </servlet>
 *    <servlet-mapping>
 *      <servlet-name>WebSocketProxy</servlet-name>
 *      <url-pattern>/proxy</url-pattern>
 *    </servlet-mapping>
 * ```
 */
class WebSocketProxyServlet extends WebSocketServlet {

  override def doGet(request:HttpServletRequest, response:HttpServletResponse):Unit = {
    getServletContext().getNamedDispatcher("default").forward(request,response);
  }

  def doWebSocketConnect(request:HttpServletRequest, protocol:String):WebSocket =
    WebSocketProxy(getInitParameter("host"), getInitParameter("port").toInt);
}
