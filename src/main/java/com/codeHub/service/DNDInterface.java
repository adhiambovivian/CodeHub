package com.codeHub.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DNDInterface extends Remote {

    public List<Person> getDndList() throws RemoteException;


}
