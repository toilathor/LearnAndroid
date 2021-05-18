package com.lqt.duynguyenhairsalon.Model;


import java.util.List;

public class mTask {
    private int ID_Task;
    private String Date_Task;
    private int Sum_Money_Task;
    private int Is_Save_Photo;
    private int Is_Consulting;
    private int Is_Successful_Task;
    private User User;
    private List<String> List_Service;

    public mTask(int ID_Task, String date_Task, int sum_Money_Task, int is_Save_Photo, int is_Consulting, int is_Successful_Task, com.lqt.duynguyenhairsalon.Model.User user, List<String> list_Service) {
        this.ID_Task = ID_Task;
        Date_Task = date_Task;
        Sum_Money_Task = sum_Money_Task;
        Is_Save_Photo = is_Save_Photo;
        Is_Consulting = is_Consulting;
        Is_Successful_Task = is_Successful_Task;
        User = user;
        List_Service = list_Service;
    }

    public int getID_Task() {
        return ID_Task;
    }

    public void setID_Task(int ID_Task) {
        this.ID_Task = ID_Task;
    }

    public String getDate_Task() {
        return Date_Task;
    }

    public void setDate_Task(String date_Task) {
        Date_Task = date_Task;
    }

    public int getSum_Money_Task() {
        return Sum_Money_Task;
    }

    public void setSum_Money_Task(int sum_Money_Task) {
        Sum_Money_Task = sum_Money_Task;
    }

    public int getIs_Save_Photo() {
        return Is_Save_Photo;
    }

    public void setIs_Save_Photo(int is_Save_Photo) {
        Is_Save_Photo = is_Save_Photo;
    }

    public int getIs_Consulting() {
        return Is_Consulting;
    }

    public void setIs_Consulting(int is_Consulting) {
        Is_Consulting = is_Consulting;
    }

    public int getIs_Successful_Task() {
        return Is_Successful_Task;
    }

    public void setIs_Successful_Task(int is_Successful_Task) {
        Is_Successful_Task = is_Successful_Task;
    }

    public com.lqt.duynguyenhairsalon.Model.User getUser() {
        return User;
    }

    public void setUser(com.lqt.duynguyenhairsalon.Model.User user) {
        User = user;
    }

    public List<String> getList_Service() {
        return List_Service;
    }

    public void setList_Service(List<String> list_Service) {
        List_Service = list_Service;
    }
}
