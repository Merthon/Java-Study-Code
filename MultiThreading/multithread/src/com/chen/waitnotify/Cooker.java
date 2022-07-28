package com.chen.waitnotify;

public class Cooker extends Thread{
    private Desk desk;
    public Cooker(Desk desk) {
        this.desk=desk;
    }
    @Override
    public void run() {
        while (true){
            synchronized (desk.getLock()){
                if(desk.getCount() == 0){
                    break;
                }else {
                    if(!desk.isFlag()){
                        System.out.println("正在做");
                        desk.setFlag(true);
                        desk.getLock().notifyAll();
                    }else {
                        try {
                            desk.getLock().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
