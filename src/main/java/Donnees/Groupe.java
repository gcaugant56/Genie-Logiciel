package Donnees;


import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Groupe {
    @SerializedName("userName")
    private ArrayList<String> userName = new ArrayList<>();
    @SerializedName("Message")
    private ArrayList<Message> messages = new ArrayList<>();
    @SerializedName("Name")
    private String name;

    public Groupe(String name, ArrayList<String> userName) {
        this.name = name;
        this.userName = userName;
    }

    public ArrayList<String> getUserName() {
        return userName;
    }

    public ArrayList<Message> getMessageGroup() {
        return messages;
    }

    public void setUserName(ArrayList<String> userName) {
        this.userName = userName;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}