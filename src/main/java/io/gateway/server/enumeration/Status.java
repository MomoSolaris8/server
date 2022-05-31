package io.gateway.server.enumeration;

/**
 * @author momo
 * @version 1.0
 * @since 5/29/2022
 */

public enum Status{
    /**
     */

    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");
    private  final  String status;

    Status(String status){
        this.status = status;
    }

    public String  getStatus(){
        return this.status;
    }

}
