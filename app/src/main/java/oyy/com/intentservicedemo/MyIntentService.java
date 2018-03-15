package oyy.com.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by
 * ouyangyi on 18/3/14.
 */

public class MyIntentService extends IntentService {
    /**
     * 是否正在运行
     */
    private boolean isRunning;

    private int count; //进度


    private static updateListener updateListener;

    public MyIntentService() {
        super("MyIntentService");
    }

    public interface updateListener {
        void update(int index, int Progress);
    }

    public static void setUpdateListener(updateListener listener) {
        updateListener = listener;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        isRunning = true;
        count = 0;

        try {
            Thread.sleep(1000);
            while (isRunning) {
                count ++;
                if(count >= 100) {
                    isRunning = false;
                }
                updateListener.update(intent.getIntExtra("index", 0), count);

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
