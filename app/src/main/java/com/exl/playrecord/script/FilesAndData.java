package com.exl.playrecord.script;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.exl.playrecord.MainActivity;
import com.exl.playrecord.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 2015/3/24.
 */
public class FilesAndData {
    static MainActivity activity;

    static final String fileName="Data.json",tmpFileName="Tmp.json";

    public FilesAndData(MainActivity activity) throws FileNotFoundException, IOException {
        this.activity=activity;
    }
    public File getDataDir(){
        File root = Environment.getExternalStorageDirectory();
        Log.v("getDir",""+root);
        File dir = new File(root.getAbsolutePath() + "/Music Salvation Data/Datas");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }
    public static String turnUriToName(Uri u){
        String a=u.toString(),b="";
        a= Uri.decode(a);
        for(int i=a.length();i>0;i--){
            if(a.substring(i-1, i).equals("/")){
                break;
            }else if(a.substring(i-3, i).equals("%20")){
                b+=" ";
                i-=2;
            }else b+=a.substring(i-1, i);
        }

        return Reversion(b);
    }
    public static String Reversion(String temp){
        String c="";
        for(int i=temp.length();i>0;i--){
            c+=temp.subSequence(i-1, i);
        }
        return c;
    }

    public void readData(){//TAG 存檔讀取
        JSONObject json=null;
        String content=""; //內容
        byte[] buff = new byte[1024];

        try {
            File files = new File(getDataDir(),fileName);
            FileInputStream file =new FileInputStream(files);
            //FileInputStream file=openFileInput(fileName);
            while((file.read(buff))!=-1){
                content+=new String(buff).trim();
            }
            json=new JSONObject(content);
            //TODO  讀取

            file.close();
            Log.v("Data", "Data read");
        } catch (FileNotFoundException e) {
            AlertDialog.Builder io=new AlertDialog.Builder(activity);
            io.setTitle(R.string.data_save_alert_title);
            io.setMessage(R.string.data_save_alert_message);
            io.setNegativeButton(R.string.button_cancel,null);
            io.setPositiveButton(R.string.data_save_alert_btn_redo, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    writeData();
                }
            });
           //TODO  初始值
            Log.v("Data", "Data not found");
            writeData();
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Data","讀取檔案失敗");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("Data","寫入json失敗");
            e.printStackTrace();
        };
    }

    public void writeData(){//存檔寫入
        JSONObject json=new JSONObject();
        try {
            //TODO  寫入
            json.put("test","test");
        } catch (JSONException e) {
            Log.v("Data","無法將參數導入json");
            e.printStackTrace();
        }
        try {
            File tmpFile = new File(getDataDir(), tmpFileName);
            File file= new File(getDataDir(),fileName);
            FileOutputStream writer =new FileOutputStream(tmpFile);
            writer.write(json.toString().getBytes());
            writer.close();
            if (tmpFile.exists()){
                copyFile(tmpFile,file);
                Log.v("Data", "Data saved");
            }else {
                Toast.makeText(activity.getApplicationContext(), R.string.data_tmp,Toast.LENGTH_LONG);
                AlertDialog.Builder io=new AlertDialog.Builder(activity);
                io.setTitle(R.string.data_save_alert_title);
                io.setMessage(R.string.data_save_alert_message);
                io.setNegativeButton(R.string.button_cancel,null);
                io.setPositiveButton(R.string.data_save_alert_btn_redo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        writeData();
                    }
                });
                Log.e("Data", "Tmp File Not Found.");
            }
        } catch (FileNotFoundException e) {
            Log.v("Data","FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.v("Data","IOException");
            e.printStackTrace();
        }
    }
    private void copyFile(File source, File dest) {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
