package me.zipstream.todo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ToDoItem implements Serializable {

    private static final String TO_DO_UUID = "to_do_uuid";
    private static final String TO_DO_TEXT = "to_do_text";
    private static final String TO_DO_DATE = "to_do_date";
    private static final String TO_DO_COLOR = "to_do_color";
    private static final String TO_DO_REMINDER = "to_do_reminder";

    private UUID mUUID;
    private Date mDate;
    private String mText;
    private int mColor;
    private boolean mHasReminder;

    public ToDoItem(String text, boolean hasReminder, Date date) {
        mText = text;
        mHasReminder = hasReminder;
        mDate = date;
        mColor = 1677725;  // #19999D
        mUUID = UUID.randomUUID();
    }

    public ToDoItem(JSONObject jsonObject) throws JSONException {
        mText = jsonObject.getString(TO_DO_TEXT);
        mHasReminder = jsonObject.getBoolean(TO_DO_REMINDER);
        mColor = jsonObject.getInt(TO_DO_COLOR);
        mUUID = UUID.fromString(jsonObject.getString(TO_DO_UUID));
        if (jsonObject.has(TO_DO_DATE)) {
            mDate = new Date(jsonObject.getLong(TO_DO_DATE));
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TO_DO_TEXT, mText);
        jsonObject.put(TO_DO_REMINDER, mHasReminder);
        jsonObject.put(TO_DO_COLOR, mColor);
        jsonObject.put(TO_DO_UUID, mUUID.toString());
        if (mDate != null) {
            jsonObject.put(TO_DO_DATE, mDate.getTime());
        }

        return jsonObject;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public boolean isHasReminder() {
        return mHasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        mHasReminder = hasReminder;
    }
}
