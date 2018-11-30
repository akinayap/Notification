package com.akina.notificationview;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.Calendar;

public class ListItem implements Comparable<ListItem> {
    String m_title, m_content;
    boolean m_is_seen;
    int m_item_type;
    Calendar m_cal;

    public ListItem(String content, int type, Calendar cal) {
        switch(type) {
            case 0:
                m_title = "Reminder";
                break;
            case 1:
                m_title = "Yes/No Radio Buttons";
                break;
            case 2:
                m_title = "A/B/C Radio Buttons";
                break;
            case 3:
                m_title = "Edit Text";
                break;
            case 4:
                m_title = "Motivation Rating";
                break;
            case 5:
                m_title = "Helpfulness Rating";
                break;
        }

        m_content = content;
        m_is_seen = false;
        m_item_type = type;
        m_cal = cal;
    }

    public boolean SameDate(ListItem otherItem)
    {
        boolean sameYear = (m_cal.get(Calendar.YEAR) == otherItem.m_cal.get(Calendar.YEAR));
        boolean sameMonth = (m_cal.get(Calendar.MONTH) == otherItem.m_cal.get(Calendar.MONTH));
        boolean sameDay = (m_cal.get(Calendar.DAY_OF_MONTH) == otherItem.m_cal.get(Calendar.DAY_OF_MONTH));
        return ((sameYear && sameMonth) && sameDay);
    }

    @Override
    public int compareTo(@NonNull ListItem i) {
        return m_cal.compareTo(i.m_cal);
    }
}
