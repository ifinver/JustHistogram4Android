package com.xtuone.justhistogram;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import java.util.Calendar;

/**
 * Entity mapped to table "Summery".
 */
public class Summery {

    private long date;
    private long rec_total;
    private long send_total;
    private long rec_sys_mobile;
    private long rec_mine_mobile;
    private long send_sys_mobile;
    private long send_mine_mobile;
    private long rec_sys_wifi;
    private long rec_mine_wifi;
    private long send_sys_wifi;
    private long send_mine_wifi;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Summery() {
    }

    public Summery(long date) {
        this.date = date;
    }

    public Summery(long date, long rec_total, long send_total, long rec_sys_mobile, long rec_mine_mobile, long send_sys_mobile, long send_mine_mobile, long rec_sys_wifi, long rec_mine_wifi, long send_sys_wifi, long send_mine_wifi) {
        this.date = date;
        this.rec_total = rec_total;
        this.send_total = send_total;
        this.rec_sys_mobile = rec_sys_mobile;
        this.rec_mine_mobile = rec_mine_mobile;
        this.send_sys_mobile = send_sys_mobile;
        this.send_mine_mobile = send_mine_mobile;
        this.rec_sys_wifi = rec_sys_wifi;
        this.rec_mine_wifi = rec_mine_wifi;
        this.send_sys_wifi = send_sys_wifi;
        this.send_mine_wifi = send_mine_wifi;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getRec_total() {
        return rec_total;
    }

    public void setRec_total(long rec_total) {
        this.rec_total = rec_total;
    }

    public long getSend_total() {
        return send_total;
    }

    public void setSend_total(long send_total) {
        this.send_total = send_total;
    }

    public long getRec_sys_mobile() {
        return rec_sys_mobile;
    }

    public void setRec_sys_mobile(long rec_sys_mobile) {
        this.rec_sys_mobile = rec_sys_mobile;
    }

    public long getRec_mine_mobile() {
        return rec_mine_mobile;
    }

    public void setRec_mine_mobile(long rec_mine_mobile) {
        this.rec_mine_mobile = rec_mine_mobile;
    }

    public long getSend_sys_mobile() {
        return send_sys_mobile;
    }

    public void setSend_sys_mobile(long send_sys_mobile) {
        this.send_sys_mobile = send_sys_mobile;
    }

    public long getSend_mine_mobile() {
        return send_mine_mobile;
    }

    public void setSend_mine_mobile(long send_mine_mobile) {
        this.send_mine_mobile = send_mine_mobile;
    }

    public long getRec_sys_wifi() {
        return rec_sys_wifi;
    }

    public void setRec_sys_wifi(long rec_sys_wifi) {
        this.rec_sys_wifi = rec_sys_wifi;
    }

    public long getRec_mine_wifi() {
        return rec_mine_wifi;
    }

    public void setRec_mine_wifi(long rec_mine_wifi) {
        this.rec_mine_wifi = rec_mine_wifi;
    }

    public long getSend_sys_wifi() {
        return send_sys_wifi;
    }

    public void setSend_sys_wifi(long send_sys_wifi) {
        this.send_sys_wifi = send_sys_wifi;
    }

    public long getSend_mine_wifi() {
        return send_mine_wifi;
    }

    public void setSend_mine_wifi(long send_mine_wifi) {
        this.send_mine_wifi = send_mine_wifi;
    }

    // KEEP METHODS - put your custom methods here
    public void setTimeInMillis(long timeMillis){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1; //0-11
        int day = c.get(Calendar.DAY_OF_MONTH);

        this.date = year *10000+month*100+day;
    }

    public boolean isSameDay(long timeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1; //0-11
        int day = c.get(Calendar.DAY_OF_MONTH);

        return this.date == year *10000+month*100+day;
    }

    public static long calculateDate(long timeMillis){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        return c.get(Calendar.YEAR) *10000+(c.get(Calendar.MONTH) + 1)*100+c.get(Calendar.DAY_OF_MONTH);
    }
    // KEEP METHODS END

}
