package io.collapse.wsproxy

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.lang.Thread
import java.net.Socket
import org.eclipse.jetty.websocket.WebSocket
import scala.io.Source

class WebSocketProxy(host:String, port:Int) extends WebSocket with WebSocket.OnTextMessage {

  val socket:Socket = new Socket(host, port);

  def onOpen(connection:WebSocket.Connection):Unit = {
    connection.setMaxBinaryMessageSize(1024 * 1024 * 1);
    connection.setMaxIdleTime(1000 * 60 * 60 * 24 * 2);
    connection.setMaxTextMessageSize(1024 * 1024 * 1);

    try {
      SocketProxy(connection, socket, {
          exception:Exception =>
            connection.disconnect();
        }).start()
    }
    catch {
      case exception:Exception =>
      exception.printStackTrace();
      connection.disconnect();
    }
  }

  def onClose(closeCode:Int, message:String):Unit = {
    socket.getOutputStream().close();
    socket.getInputStream().close();
    socket.close();
  }

  def onMessage(message:String):Unit = {
    socket.getOutputStream().write(message.getBytes());
  }
}

object WebSocketProxy {
  def apply(host:String, port:Int):WebSocketProxy = new WebSocketProxy(host, port)
}
