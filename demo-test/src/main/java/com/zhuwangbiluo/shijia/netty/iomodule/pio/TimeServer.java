package com.zhuwangbiluo.shijia.netty.iomodule.pio;


import com.zhuwangbiluo.shijia.netty.iomodule.common.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TImeServer
 *
 * @author shijia.lym
 * @since 2019-05-09
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                //采用默认值
            }
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port：" + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = serverSocket.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (serverSocket != null) {
                System.out.println("The time server close!");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
