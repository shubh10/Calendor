package com.esbati.keivan.persiancalendar.Models;

import org.json.JSONException;
import org.json.JSONObject;

import ir.smartlab.persindatepicker.util.PersianCalendar;

/**
 * Created by asus on 11/21/2016.
 */

public class Event {

    public String mTitle;
    public int mYear;
    public int mMonth;
    public int mDay;
    public boolean isHoliday;
    public PersianCalendar mPersianDate;

    public Event(){

    }

    public Event fromJSON(JSONObject eventJSON) throws JSONException{
        mTitle = eventJSON.getString("title");
        mYear = eventJSON.getInt("year");
        mMonth = eventJSON.getInt("month");
        mDay = eventJSON.getInt("day");
        isHoliday = eventJSON.getBoolean("holiday");
        mPersianDate = new PersianCalendar().setPersianDate(mYear, mMonth, mDay);
        return this;
    }
}
