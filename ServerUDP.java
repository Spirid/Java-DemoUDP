package demoudp;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerUDP
{
    private DatagramSocket socket;
    private DatagramPacket packet;
    private final int sendport = 15679;
    private final int receiveport = 15678;

    public ServerUDP() {
        try {
            socket = new DatagramSocket(sendport);
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        }
    }

    public void closeUDP() {
        socket.close();
    }

    public void sendUDP() {
        Demo obj = new Demo(42, "sender", "test message sending");
        ObjectOutputStream os = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            os =  new ObjectOutputStream(baos);
            os.writeObject(obj);
            byte[] buffer = baos.toByteArray();
            packet = new DatagramPacket(buffer, buffer.length);
            packet.setAddress(InetAddress.getByName("localhost"));
            packet.setPort(receiveport);
            socket.send(packet);
            System.out.println("Packet is sent.");
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException occur. " + e.getMessage());
        } finally {
            if (os != null) try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String arg[]) throws Exception {
        ServerUDP server = new ServerUDP();
        server.sendUDP();
        server.closeUDP();
    }
}