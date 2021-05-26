package com.codeHub.service;

import com.codeHub.models.Participant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RmiService extends UnicastRemoteObject implements DNDInterface {

    public RmiService() throws RemoteException{}

    public List<Participant> getDndList() {
        List<Participant>dndList=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Participant person = new Participant("Dar", "Lucil",39,"+2547263632233");
            dndList.add(person);
            person =null;

        }
        return dndList;
    }
}


