package demoudp;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUDP
{
    private DatagramSocket socket;
    private DatagramPacket packet;
    private final int receiveport = 15678;
    private final int bufferSize = 1024;
    
    public ClientUDP() {
        try {
            socket = new DatagramSocket(receiveport);
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        }
    }

    public void closeUDP() {
        socket.close();
    }

    public void receiveUDP() {
        ObjectInputStream is = null;
        try {
            byte[] buffer = new byte[bufferSize];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            is = new ObjectInputStream(bais);
            Object received = is.readObject();
            ((Demo)(received)).getInfo();
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IOException occur. " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (is != null) try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String arg[]) throws Exception {
        ClientUDP client = new ClientUDP();
        client.receiveUDP();
        client.closeUDP();
    }
}
