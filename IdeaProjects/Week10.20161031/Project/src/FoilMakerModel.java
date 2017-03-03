import com.sun.jna.platform.win32.Netapi32Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by student on 10/25/16.
 */
public class FoilMakerModel {

    String username, password, userCookie, token;
    FoilMakerController controller;


    public FoilMakerModel(FoilMakerController controller) {
        this.controller = controller;
    }

    public void getUser(String name, String Pw) {
        this.username = name;
        this.password = Pw;
    }
    public void userCookie(String str){
        this.userCookie=str;
    }

    public void GameToken(String token){
        this.token=token;
    }
}