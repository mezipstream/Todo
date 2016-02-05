package me.zipstream.todo.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import me.zipstream.todo.model.ToDoItem;

public class StoreRetrieveData {

    private Context mContext;
    private String mFileName;

    public StoreRetrieveData(Context context, String fileName) {
        mContext = context;
        mFileName = fileName;
    }

    public void saveToFile(List<ToDoItem> items) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            osw.write(toJSONArray(items).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<ToDoItem> loadFromFile() {
        List<ToDoItem> items = new ArrayList<>();

        BufferedReader br = null;
        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(mFileName);
            StringBuilder sb = new StringBuilder();
            String line;
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(sb.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                ToDoItem item = new ToDoItem(jsonArray.getJSONObject(i));
                items.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return items;
    }

    public JSONArray toJSONArray(List<ToDoItem> items) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ToDoItem item : items) {
            JSONObject jsonObject = item.toJSON();
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
}
