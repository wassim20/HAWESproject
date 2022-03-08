/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entités.Message;
import Entités.utilisateurs;
import Service.RealTimeService;
import Service.ServiceMessage;
import Service.utilisateurService;
import static Service.utilisateurService.currentUser;
import Tools.Statics;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class ChatController implements Initializable {

    ServiceMessage SM;
    utilisateurService US;

    List<Message> messages;
    List<utilisateurs> utilisateurs;
    List<utilisateurs> allUsers;
    utilisateurs friend;
    @FXML
    private VBox usersList;
    @FXML
    private VBox messagesList;
    @FXML
    private ImageView friendImage;
    @FXML
    private Label friendName;
    @FXML
    private Label friendRole;
    @FXML
    private TextField textfieldMessage;

    RealTimeService realTime;
    @FXML
    private ScrollPane messageScroll;
    @FXML
    private TextField searchUsersTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SM = new ServiceMessage();
        US = new utilisateurService();
        try {
            realTime = new RealTimeService();
        } catch (URISyntaxException ex) {
            System.out.println("exeption in init realtime service :" + ex.getMessage());
        }

        messages = new ArrayList<>();
        utilisateurs = new ArrayList<>();
        allUsers = new ArrayList<>();

        messagesList.getChildren().clear();

        friendImage.setVisible(false);
        friendName.setVisible(false);
        friendRole.setVisible(false);

        friend = new utilisateurs();
        try {
            allUsers = US.getUsers();
            utilisateurs = allUsers;
            displayUsers(utilisateurs);
        } catch (SQLException ex) {
            System.out.println("error get messages :" + ex.getMessage());
        }

        try {
            realTime.setWebSocket(new WebSocketClient(new URI(Statics.wsURI), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake sh) {
                    System.out.println("opened connection");
                }

                @Override
                public void onMessage(String message) {
                    JSONObject json = new JSONObject(message);
                    int id = json.getInt("id");
                    int senderId = json.getInt("senderId");
                    int receiverId = json.getInt("receiverId");
                    String type = json.getString("type");
                    String msg = json.getString("message");

                    if (US.currentUser.getIdUser() == receiverId && friend.getIdUser() == senderId) {
                        Message newMessage = new Message(id, senderId, receiverId, type, msg);
                        newMessage.setSender(friend);
                        newMessage.setReceiver(US.currentUser);
                        messages.add(newMessage);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                displayMessages(messages);
                                
                            }
                        });

                    }
                }

                @Override
                public void onClose(int i, String string, boolean bln) {
                    System.out.println("closed connection");
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("error :" + ex.getMessage());
                }
            });
            realTime.openConnection();
        } catch (URISyntaxException ex) {
            System.out.println("error init socket");
        }
    }

    public void displayUsers(List<utilisateurs> users) {
        usersList.getChildren().clear();
        
        
        
        for (int i = 0; i < users.size(); i++) {
            utilisateurs current = users.get(i);
            

            Pane userPane = new Pane();
            userPane.setBackground(Background.EMPTY);
            userPane.setPrefHeight(56);
            userPane.setPrefWidth(235);
            userPane.setLayoutX(0);
            userPane.setLayoutY(0);

            ImageView img = new ImageView();
            img.setFitHeight(44);
            img.setFitWidth(44);
            img.setPreserveRatio(false);
            img.setLayoutX(5);
            img.setLayoutY(5);
            Image image = new Image(Statics.imgPath + current.getImage());
            img.setImage(image);

            Label fullName = new Label();
            fullName.setPrefHeight(45);
            fullName.setPrefWidth(180);
            fullName.setLayoutX(53);
            fullName.setLayoutY(3);
            fullName.setTextFill(Color.BLACK);

            fullName.setStyle("-fx-font-size :17");
            fullName.setText(current.getPrenomUser() + " " + current.getNomUser());
            fullName.setAlignment(Pos.TOP_LEFT);
            fullName.setWrapText(true);

            userPane.getChildren().add(img);
            userPane.getChildren().add(fullName);

            userPane.setOnMouseEntered((MouseEvent event) -> {
                userPane.setBackground(new Background(new BackgroundFill(Color.web("#f0f2f5"), new CornerRadii(7), Insets.EMPTY)));
            });
            
            userPane.setOnMouseExited((MouseEvent event) -> {
                userPane.setBackground(Background.EMPTY);
            });
            
            
            userPane.setOnMouseClicked(((MouseEvent e) -> {
                try {
                    friendImage.setVisible(true);
                    friendName.setVisible(true);
                    friendRole.setVisible(true);
                    friendImage.setImage(image);
                    friendName.setText(current.getPrenomUser() + " " + current.getNomUser());
                    friendRole.setText(current.getRole());
                    friend = current;
                    messages = SM.getMessages(currentUser.getIdUser(), friend.getIdUser());
                    displayMessages(messages);
                } catch (SQLException ex) {
                    System.out.println("error get messages on click :" + ex.getMessage());
                }
            }));
            usersList.getChildren().add(userPane);

        }
    }

    public void displayMessages(List<Message> messages) {
        messagesList.getChildren().clear();
        for (int i = 0; i < messages.size(); i++) {
            Pane messagePane = new Pane();
            if (currentUser.getIdUser() == messages.get(i).getSenderId()) { //connected user send message
                messagePane = connectedPane(messages.get(i));
            } else {//friend send message
                messagePane = friendPane(messages.get(i));
            }
            messagesList.getChildren().add(messagePane);
        }
        messageScroll.setVvalue(1.0);
    }

    public Pane friendPane(Message message) {
        Pane pane = new Pane();
        pane.setPrefSize(460, 59.2);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        ImageView img = new ImageView();
        img.setFitHeight(50);
        img.setFitWidth(50);
        img.setPreserveRatio(false);
        img.setLayoutX(5);
        img.setLayoutY(5);
        Image image = new Image(Statics.imgPath + message.getSender().getImage());
        img.setImage(image);

        Label msg = new Label();
        msg.setPrefHeight(54);
        msg.setPrefWidth(383);
        msg.setLayoutX(62);
        msg.setLayoutY(5);
        msg.setTextFill(Color.BLACK);
        msg.setStyle("-fx-font-size :18");
        msg.setText(message.getMessage());
        msg.setAlignment(Pos.TOP_LEFT);
        msg.setWrapText(true);

        pane.getChildren().add(img);
        pane.getChildren().add(msg);

        return pane;
    }

    public Pane connectedPane(Message message) {
        Pane pane = new Pane();
        pane.setPrefSize(531, 59.2);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        Label msg = new Label();
        msg.setPrefHeight(54);
        msg.setPrefWidth(383);
        msg.setLayoutX(147);
        msg.setLayoutY(5);
        msg.setTextFill(Color.BLACK);
        msg.setStyle("-fx-font-size :18");
        msg.setText(message.getMessage());
        msg.setAlignment(Pos.TOP_RIGHT);
        msg.setWrapText(true);

        pane.getChildren().add(msg);

        return pane;
    }

    @FXML
    private void sendMessage(ActionEvent event) {

        if (friend.getIdUser() == 0) {
            System.out.println("not allowed");
        } else {
            if (!textfieldMessage.getText().isEmpty()) {
                try {
                    Message message = new Message(0, currentUser.getIdUser(), friend.getIdUser(), "message", textfieldMessage.getText());
                    message.setReceiver(friend);
                    message.setSender(currentUser);
                    SM.addMessage(message);//add to database
                    sendMessageToSocket(message);//send to socket
                    messages.add(message);//add to ui
                    displayMessages(messages);//refresh ui
                    textfieldMessage.setText("");//empty textfield
                } catch (SQLException ex) {
                    System.out.println("error add message :" + ex.getMessage());
                }
            } else {
                System.out.println("message empty");
            }

        }
    }

    public void sendMessageToSocket(Message message) {
        JSONObject obj = new JSONObject();
        obj.put("id", message.getId());
        obj.put("senderId", message.getSenderId());
        obj.put("receiverId", message.getReceiverId());
        obj.put("type", message.getType());
        obj.put("message", message.getMessage());
        obj.put("seen", message.getSeen());
        realTime.getWebSocket().send(obj.toString());
    }

    @FXML
    private void searchUsersAction(KeyEvent event) {
        if (!searchUsersTextField.getText().isEmpty()) {
            utilisateurs = utilisateurs
                    .stream()
                    .filter(c -> c.getNomUser().toLowerCase().startsWith(searchUsersTextField.getText().toLowerCase()) || c.getPrenomUser().toLowerCase().startsWith(searchUsersTextField.getText().toLowerCase()) || (c.getNomUser() + " " + c.getPrenomUser()).toLowerCase().startsWith(searchUsersTextField.getText().toLowerCase()) || (c.getPrenomUser() + " " + c.getNomUser()).toLowerCase().startsWith(searchUsersTextField.getText().toLowerCase()))
                    .collect(Collectors.toList());
            if (utilisateurs.isEmpty()) {
                utilisateurs = allUsers;
            }
        } else {
            utilisateurs = allUsers;
        }
        displayUsers(utilisateurs);
    }

}
