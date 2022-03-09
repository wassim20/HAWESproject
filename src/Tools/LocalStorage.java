/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entités.utilisateurs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Hassen Chouadah
 */
public class LocalStorage {

    private String fileName = "storage.txt";

    public LocalStorage() throws IOException {
        this.initLLocalStorage();
    }

    public File initLLocalStorage() throws IOException {

        File myObj = new File(fileName);
        if (myObj.createNewFile()) {
            System.out.println("storage created: " + myObj.getName());
        } else {
            System.out.println("storage init.");
        }
        return myObj;
    }

    public void writeToStorage(utilisateurs user) throws IOException {

        FileWriter myWriter = new FileWriter(fileName);
        JSONObject obj = new JSONObject();
        obj.put("idUser", user.getIdUser());
        obj.put("cinUser", user.getCinUser());
        obj.put("nomUser", user.getNomUser());
        obj.put("prenomUser", user.getPrenomUser());
        obj.put("emailUser", user.getEmailUser());
        obj.put("mdpUser", user.getMdpUser());
        obj.put("adresseUser", user.getAdresseUser());
        obj.put("telUser", user.getTelUser());
        obj.put("role", user.getRole());
        obj.put("voiture", user.getVoiture());
        obj.put("image", user.getImage());

        System.out.println(obj.toJSONString());

        myWriter.write(obj.toJSONString());
        myWriter.close();
        System.out.println("Successfully stored connectedUser.");
    }

    public utilisateurs getStoredUser() throws FileNotFoundException {
        utilisateurs user = new utilisateurs();

        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Object obj = JSONValue.parse(data);
            JSONObject json = (JSONObject) obj;
            user = getUserFromJSON(json);
        }
        myReader.close();
        return user;
    }

    public utilisateurs getUserFromJSON(JSONObject json) {
        utilisateurs user = new utilisateurs();
        long idUser = (Long) json.get("idUser");
        String cinUser = (String) json.get("cinUser");
        String nomUser = (String) json.get("nomUser");
        String prenomUser = (String) json.get("prenomUser");
        String emailUser = (String) json.get("emailUser");
        String mdpUser = (String) json.get("mdpUser");
        String adresseUser = (String) json.get("adresseUser");
        String telUser = (String) json.get("telUser");
        String role = (String) json.get("role");
        String voiture = (String) json.get("voiture");
        String image = (String) json.get("image");

        user.setIdUser(Math.toIntExact(idUser));
        user.setCinUser(cinUser);
        user.setNomUser(nomUser);
        user.setPrenomUser(prenomUser);
        user.setEmailUser(emailUser);
        user.setMdpUser(mdpUser);
        user.setAdresseUser(adresseUser);
        user.setTelUser(telUser);
        user.setRole(role);
        user.setVoiture(voiture);
        user.setImage(image);

        return user;

    }

    public void deleteStorage() {
        File myObj = new File(fileName);
        if (myObj.delete()) {
            System.out.println("Deleted storage: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    

}
