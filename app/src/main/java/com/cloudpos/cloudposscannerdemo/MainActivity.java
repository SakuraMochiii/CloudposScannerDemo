package com.cloudpos.cloudposscannerdemo;

import android.content.Context;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cloudpos.scanserver.aidl.IScanCallBack;
import com.cloudpos.scanserver.aidl.IScanService;
import com.cloudpos.scanserver.aidl.ScanParameter;
import com.cloudpos.scanserver.aidl.ScanResult;
import com.cloudpos.cloudposscannerdemo.UI.ListViewAdapter;
import com.cloudpos.cloudposscannerdemo.control.AidlController;
import com.cloudpos.cloudposscannerdemo.control.IAIDLListener;

public class MainActivity extends AppCompatActivity implements IAIDLListener{

    private TextView mTv;
    private ListView mListView;
    private ImageView mImage;
    private Context mContext;
    private IAIDLListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        listener = this;
        initView();
        initUI();
        AidlController.getInstance().startScanService(mContext, listener);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mTv = (TextView) findViewById(R.id.textview);
        mImage = (ImageView) findViewById(R.id.imageview);
    }

    private void initUI() {
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mListView.setAdapter(new ListViewAdapter(mContext));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTv.setText("");
                mImage.setImageBitmap(null);
                switch (position) {
                    case 0:
                        synchronizationScan();
                        break;
                    case 1:
                        continuousScan();
                        break;
                    case 2:
                        noFullScreenScan();
                        break;
                    case 3:
                        returnBitmapScan();
                        break;
                    case 4:
                        noBoxScan();
                        break;
                    case 5:
                        scanFormat();
                        break;
                    case 6:
                        customScanWindow();
                        break;
                }
            }
        });
    }


    /**
     * 同步扫码
     */
    private void synchronizationScan() {
        ScanParameter parameter = new ScanParameter();
        try {
            ScanResult result = scanService.scanBarcode(parameter);
            mTv.setText("Result : \n" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * 异步扫码
     */

    private void continuousScan() {
        ScanParameter parameter = new ScanParameter();
        try {
            scanService.startScan(parameter, new IScanCallBack.Stub() {
                @Override
                public void foundBarcode(ScanResult result) {
//                    Message message = new Message();
//                    message.what = 0;
//                    message.obj = result;
//                    handler.sendMessage(message);
//
//                    try {
//                        scanService.stopScan();
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ScanResult obj = (ScanResult) msg.obj;
                    mTv.setText("Result : \n" + obj);
                    break;
            }
        }
    };

    /**
     * 扫码窗口非全屏
     */
    private void noFullScreenScan() {
        ScanParameter parameter = new ScanParameter();
        parameter.set(ScanParameter.KEY_UI_WINDOW_TOP, 20);
        parameter.set(ScanParameter.KEY_UI_WINDOW_LEFT, 20);
        parameter.set(ScanParameter.KEY_UI_WINDOW_WIDTH, 300);
        parameter.set(ScanParameter.KEY_UI_WINDOW_HEIGHT, 400);
        parameter.set(ScanParameter.KEY_SCAN_SECTION_WIDTH, 200);
        parameter.set(ScanParameter.KEY_SCAN_SECTION_HEIGHT, 200);
        try {
            ScanResult result = scanService.scanBarcode(parameter);
            mTv.setText("Result : \n" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫码返回图片
     */
    private void returnBitmapScan() {
        ScanParameter parameter = new ScanParameter();
        parameter.set(ScanParameter.KEY_ENABLE_RETURN_IMAGE, true);
        try {
            ScanResult result = scanService.scanBarcode(parameter);
            Bitmap decodeBitmap = result.getDecodeBitmap();
            mImage.setImageBitmap(decodeBitmap);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去掉扫码框 全屏扫码
     */
    private void noBoxScan() {
        ScanParameter parameter = new ScanParameter();
        parameter.set(ScanParameter.KEY_ENABLE_SCAN_SECTION, false);
        try {
            ScanResult result = scanService.scanBarcode(parameter);
            mTv.setText("Result : \n" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 只扫二维码和code128码
     */
    private void scanFormat() {
        ScanParameter parameter = new ScanParameter();
        parameter.set(ScanParameter.KEY_DECODEFORMAT, "QR, Code128");
        try {
            ScanResult result = scanService.scanBarcode(parameter);
            mTv.setText("Result : \n" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义扫码框
     */
    private void customScanWindow() {
        ScanParameter parameter = new ScanParameter();
        parameter.set(ScanParameter.KEY_SCAN_SECTION_BORDER_COLOR, Color.rgb(0xFF, 0x00, 0x00));
        parameter.set(ScanParameter.KEY_SCAN_SECTION_CORNER_COLOR, Color.rgb(0x00, 0xFF, 0x00));
        parameter.set(ScanParameter.KEY_SCAN_SECTION_LINE_COLOR, Color.rgb(0x00, 0x00, 0xFF));
        parameter.set(ScanParameter.KEY_DISPLAY_SCAN_LINE, "no");
        parameter.set(ScanParameter.KEY_SCAN_TIP_TEXT, "上海慧银");
        parameter.set(ScanParameter.KEY_SCAN_TIP_TEXTCOLOR, Color.rgb(0xFF, 0x00, 0x00));
        parameter.set(ScanParameter.KEY_SCAN_TIP_TEXTMARGIN, 0);
        parameter.set(ScanParameter.KEY_SCAN_TIP_TEXTSIZE , 25);
        try {
            ScanResult result = scanService.scanBarcode(parameter);
            mTv.setText("Result : \n" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private IScanService scanService; //扫码服务
    private ServiceConnection scanConn;

    @Override
    public void serviceConnected(Object objService, ServiceConnection connection) {
        if(objService instanceof IScanService){
            scanService = (IScanService) objService;
            scanConn = connection;
        }
    }

    @Override
    protected void onDestroy() {
        if(scanService != null){
            this.unbindService(scanConn);
            scanService = null;
            scanConn = null;
        }
        super.onDestroy();
    }
}
