package com.codeHub.service;

import com.codeHub.models.Blacklist;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RmiService extends UnicastRemoteObject implements DNDInterface {

    public RmiService() throws RemoteException{}

    public List<Blacklist> getDndList() {
        List<Blacklist>dndList=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Blacklist blacklist = new Blacklist("coment", "OPTIN", i, "+2547263632233" + i);
            dndList.add(blacklist);
            blacklist=null;

        }
        return dndList;
    }
}


