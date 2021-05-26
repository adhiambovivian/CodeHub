package com.codeHub.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RmiService extends UnicastRemoteObject implements DNDInterface {

    public RmiService() throws RemoteException{}

    public List<Person> getDndList() {
        List<Person>dndList=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person("coment", "OPTIN", i, "+2547263632233" + i);
            dndList.add(person);
            person =null;

        }
        return dndList;
    }
}


