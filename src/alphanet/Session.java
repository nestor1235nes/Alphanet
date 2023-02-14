/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphanet;

/**
 *
 * @author Gama
 */
public final class Session {
    private static Session session;

    private int loggedUser;

    private Session() {}

    public static Session getCurrentInstance() {
        if (session == null) {
            session = new Session();
        }
        return null;
    }

    public void setLoggedUser(int username) {
        loggedUser = username;
    }

    public int getLoggedUser() {
        return loggedUser;
    }
}
