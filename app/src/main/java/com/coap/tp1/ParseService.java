package com.coap.tp1;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by youness on 23/11/15.
 */
public class ParseService extends IntentService {

    public ParseService() {
        super(ParseService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ListActivity.MyWebRequestReceiver.PROCESS_RESPONSE);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        while(true){


            try {
                Thread.sleep(3000);
                sendBroadcast(broadcastIntent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
