package com.earcs.grabm.util.executor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

/**
 * @author Sachith Dickwella
 */
public class GrabTaskHandler {

    public static Handler sendTaskHandler(Handler handler, Map<String, String> keyValue) {
        Bundle bundle = new Bundle();

        if (keyValue != null) {
            Iterator it = keyValue.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                bundle.putString(key, keyValue.get(key));
            }
        }

        Message message = handler.obtainMessage();
        message.setData(bundle);
        handler.sendMessage(message);

        return handler;
    }

    public static Thread executeSingleThread(Runnable runnable)
            throws InterruptedException {
        Thread isolatedThread = new Thread(runnable);
        isolatedThread.start();
        return isolatedThread;
    }
}
