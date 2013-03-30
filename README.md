WSProxy provides a Servlet that proxies a WebSocket to a Socket

Usage
=====

To configure the Servlet, add the following to `src/main/webapp/WEB-INF/web.xml`

    <servlet>
      <servlet-name>WebSocketProxy</servlet-name>
      <servlet-class>io.collapse.WebSocketProxyServlet</servlet-class>
      <load-on-startup>1</load-on-startup>
      <init-param>
        <param-name>host</param-name>
        <param-value>localhost</param-value>
      </init-param>
      <init-param>
        <param-name>port</param-name>
        <param-value>6667</param-name>
      </init-param>
    </servlet>
    <servlet-mapping>
      <servlet-name>WebSocketProxy</servlet-name>
      <url-pattern>/proxy</url-pattern>
    </servlet-mapping>

Be sure to replace the `host` and `port` parameters with the host and port that
you'd like to connect the WebSocket to.

With the configuration above, you can connect via WebSocket to ws://localhost:8080/proxy
and communicate with whatever is listening to a socket at localhost on port 6667.

To try it out, you may wish to connect with your web browser and talk to netcat listening
on the given host and port.

Running
=======

To run locally, you may execute

    ./run.sh

This will build the jar and run Jetty on port 8080.
