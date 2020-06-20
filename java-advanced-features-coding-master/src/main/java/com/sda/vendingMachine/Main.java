package com.sda.vendingMachine;

import com.sda.vendingMachine.service.DbService;
import com.sda.vendingMachine.service.IoService;

/**
 * Main class of the application.
 */
public class Main {

    public static void main(String[] args){
        DbService dbService = new DbService();
        IoService ioService = new IoService();

        VendingMachine vm = dbService.read();

        vm.setDbService(dbService);
        vm.setIoService(ioService);
        vm.start();
    }
}
