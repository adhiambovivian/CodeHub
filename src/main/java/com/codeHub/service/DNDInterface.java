package com.codeHub.service;

import com.codeHub.models.Blacklist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DNDInterface extends Remote {

    public List<Blacklist> getDndList() throws RemoteException;


}
