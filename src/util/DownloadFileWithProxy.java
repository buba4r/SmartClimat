/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LPR
 */
public class DownloadFileWithProxy {
    
    

    public static void copyURLToFile(URL url, File file) {
        try {
            SocketAddress addr = new InetSocketAddress("proxyubo.univ-brest.fr", 3128);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            URLConnection conn = url.openConnection(proxy);
            final ReadableByteChannel rbc = Channels.newChannel(conn.getInputStream());
            final FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();

        } catch (IOException ex) {
            Logger.getLogger(DownloadFileWithProxy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
