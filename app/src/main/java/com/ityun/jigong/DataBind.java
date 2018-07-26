package com.ityun.jigong;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/7/20 0020.
 */

public class DataBind  extends BmobObject {

    private long  jobDate;

    private float  workingHours;

    private  String  personId;

    public long getJobDate() {
        return jobDate;
    }

    public void setJobDate(long jobDate) {
        this.jobDate = jobDate;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
