package io.collapse.wsproxy

import java.lang.Thread
import java.net.Socket
import org.eclipse.jetty.websocket.WebSocket
import scala.io.Source

class SocketProxy(val connection:WebSocket.Connection, val socket:Socket, onException:Exception => Unit) extends Thread {
  override def run():Unit = {
    try {
      Source.fromInputStream(socket.getInputStream()).getLines().foreach {
        line:String =>
        connection.sendMessage(line)
      }
    } catch {
      case exception:Exception => onException(exception)
    }
  }
}

object SocketProxy {
  def apply(connection:WebSocket.Connection, socket:Socket, onException:Exception => Unit) = new SocketProxy(connection, socket, onException)
}
