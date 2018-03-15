package oyy.com.intentservicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyIntentService.updateListener{

    private TextView downLoadTv;

    private LinearLayout listLayout;

    private List<View> viewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downLoadTv = findViewById(R.id.downLoadTv);
        listLayout = findViewById(R.id.listLayout);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        viewList = new ArrayList<>();
        final Intent intent = new Intent(this, MyIntentService.class);
        downLoadTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              View view =  getLayoutInflater().inflate(R.layout.layout_item, null);
              viewList.add(view);
              intent.putExtra("index", viewList.size());

              listLayout.removeAllViews();
              for (int i = 0; i < viewList.size(); i ++) {
                  listLayout.addView(viewList.get(i));
                  MyIntentService.setUpdateListener(MainActivity.this);
              }
              startService(intent);
            }
        });
    }

    @Override
    public void update(int index, int Progress) {
       View view = listLayout.getChildAt(index - 1);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setProgress(Progress);

    }
}
