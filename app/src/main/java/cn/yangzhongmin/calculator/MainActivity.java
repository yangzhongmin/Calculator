package cn.yangzhongmin.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/3/28.
 * MainActivity是计算器程序的主入口，也是它的使用界面
 */

public class MainActivity extends Activity implements View.OnClickListener{

    List<Button> mButtonList;
    TextView mTvRithmetic;
    TextView mTvResult;
    StringBuffer buffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);

        initView();
        setClickListener();
    }

    /**
     * 为所有Button控件设置监听器
     */
    private void setClickListener() {
        for(Button button : mButtonList){
            button.setOnClickListener(this);
        }
    }

    /**
     * initView方法初始化MainActivity所用到的所有空间。
     * 并把所有Button空间储存在mButtonList这个List集合中
     */
    private void initView() {
        mTvRithmetic = (TextView) findViewById(R.id.tv_arithmetic);
        mTvResult = (TextView) findViewById(R.id.tv_result);

        mButtonList = new ArrayList<Button>();
        mButtonList.add((Button) findViewById(R.id.btn_history));
        mButtonList.add((Button) findViewById(R.id.btn_delete));
        mButtonList.add((Button) findViewById(R.id.btn_clean));
        mButtonList.add((Button) findViewById(R.id.btn_plus));
        mButtonList.add((Button) findViewById(R.id.btn_subtraction));
        mButtonList.add((Button) findViewById(R.id.btn_multiplication));
        mButtonList.add((Button) findViewById(R.id.btn_division));
        mButtonList.add((Button) findViewById(R.id.btn_equal));
        mButtonList.add((Button) findViewById(R.id.btn_one));
        mButtonList.add((Button) findViewById(R.id.btn_two));
        mButtonList.add((Button) findViewById(R.id.btn_three));
        mButtonList.add((Button) findViewById(R.id.btn_four));
        mButtonList.add((Button) findViewById(R.id.btn_five));
        mButtonList.add((Button) findViewById(R.id.btn_six));
        mButtonList.add((Button) findViewById(R.id.btn_seven));
        mButtonList.add((Button) findViewById(R.id.btn_eight));
        mButtonList.add((Button) findViewById(R.id.btn_nine));
        mButtonList.add((Button) findViewById(R.id.btn_zero));
        mButtonList.add((Button) findViewById(R.id.btn_point));
        }

    /**
     *实现View.OnClickListener接口中的onClick（View v)方法
     * 用来实现所有Button按钮点击事件的监听
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_zero:
            case R.id.btn_one:
            case R.id.btn_two:
            case R.id.btn_three:
            case R.id.btn_four:
            case R.id.btn_five:
            case R.id.btn_six:
            case R.id.btn_seven:
            case R.id.btn_eight:
            case R.id.btn_nine:
                buffer.delete(0,buffer.length());
                if (mTvRithmetic.getText().toString().equals("")){
                    buffer.append(((Button)v).getText().toString());
                }else{
                    buffer.append(mTvRithmetic.getText().toString() + ((Button)v).getText().toString());
                }
                mTvRithmetic.setText(buffer.toString());
                break;

            /**
             * 此处有可以连续输入点的问题，待修改
             */
            case R.id.btn_point:
                buffer.delete(0,buffer.length());
                if (mTvRithmetic.getText().toString().equals("")){
                    buffer.append("0" + ((Button)v).getText().toString());
                }else {
                    buffer.append(mTvRithmetic.getText().toString() + ((Button) v).getText().toString());
                }
                mTvRithmetic.setText(buffer.toString());
                break;

            case R.id.btn_clean:
                mTvRithmetic.setText("0");
                break;

            case R.id.btn_delete:
                if (mTvRithmetic.getText().length()>1) {
                    buffer.delete(0, buffer.length());
                    buffer.append(mTvRithmetic.getText().toString());
                    buffer.deleteCharAt(buffer.length() - 1);
                    mTvRithmetic.setText(buffer.toString());
                }
                if(mTvRithmetic.getText().length()==1){
                    mTvRithmetic.setText("0");
                }
                    break;

            /**
             * 此处需要添加判定目前输入框中是否有内容，没有内容时不允许输入+ * ÷
             */
            case R.id.btn_plus:
            case R.id.btn_multiplication:
            case R.id.btn_division:
                buffer.delete(0, buffer.length());
                buffer.append(mTvRithmetic.getText().toString());
                if( buffer.charAt(buffer.length()-1) == '＋'
                        || buffer.charAt(buffer.length()-1) == '－'
                        || buffer.charAt(buffer.length()-1) == '×'
                        || buffer.charAt(buffer.length()-1) == '÷'){
                    buffer.deleteCharAt(buffer.length() - 1);
                    buffer.append(((Button)v).getText().toString());
                }else {
                    buffer.append(((Button)v).getText().toString());
                }
                mTvRithmetic.setText(buffer.toString());
                break;

            /**
             * 需要补全减号  等于号   历史记录等几个按键的逻辑。
             */
            case R.id.btn_subtraction:
                break;
        }
    }
}
