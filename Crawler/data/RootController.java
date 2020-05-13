2
https://raw.githubusercontent.com/lilllung09/OwlWordList/master/src/application/RootController.java
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootController implements Initializable {
	
	@FXML private TextField inputID;
	@FXML private PasswordField inputPW;
	@FXML private Label signinbutton;
	@FXML private Label idpwfoundbutton;
	@FXML private Label errorMsg;
	
	public static String thisuserID = "";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inputID.setOnAction(event -> logintodb(event));
		inputPW.setOnAction(event -> logintodb(event));
		
		inputID.setOnMouseClicked(event -> errorMsg.setText(""));
		inputPW.setOnMouseClicked(event -> errorMsg.setText(""));
		
		signinbutton.setOnMouseClicked(event -> signinclick(event));
		signinbutton.setOnMouseEntered(event -> underlineON(event));
		signinbutton.setOnMouseExited(event -> underlineOFF(event));
		
		idpwfoundbutton.setOnMouseClicked(event -> idpwfoundclick(event));
		idpwfoundbutton.setOnMouseEntered(event -> underlineON(event));
		idpwfoundbutton.setOnMouseExited(event -> underlineOFF(event));
		
		if (!thisuserID.equals(""))
			inputID.setText(thisuserID);
	}

	public void logintodb(ActionEvent event) {
		errorMsg.setText("");
		if (!inputID.getText().toString().equals("")) {
			if (!inputPW.getText().toString().equals("")) {
				Connection conn = null;
				PreparedStatement pstm = null;
				ResultSet rs = null;
			        
				try {
					String quary = "SELECT * FROM AccountInfo WHERE UserID='"+ inputID.getText() + "' AND UserPW='"+ inputPW.getText() + "'";

					conn = DBConnection.getConnection();
					pstm = conn.prepareStatement(quary);
					rs = pstm.executeQuery();
					String userID = "";
					
					while(rs.next()){
						userID = rs.getString("UserID");
			            System.out.println(userID);
					}
					if (inputID.getText().toString().equals(userID)) {
						thisuserID = userID;
						final Node source = (Node) event.getSource();
						final Stage stage = (Stage) source.getScene().getWindow();
						stage.close();

						Parent root;
						try {
							root = FXMLLoader.load(getClass().getResource("MenuSelect.fxml"));
							Scene scene = new Scene(root);
							Stage primaryStage = new Stage();
							primaryStage.setTitle("메뉴선택");
							primaryStage.setResizable(true);
							primaryStage.setScene(scene);
							primaryStage.show();
							
						} catch (IOException e) {}
					}
					else {
						errorMsg.setText("ID 또는 PW를 확인해주세요.");
					}
			            
				} catch (SQLException sqle) {
					System.out.println("SELECT문에서 예외 발생");
					sqle.printStackTrace();
			            
				} finally{
					// DB 연결을 종료한다.
					try{
						System.out.println("Database 연결을 종료합니다.");
						if ( rs != null ){rs.close();}   
						if ( pstm != null ){pstm.close();}   
						if ( conn != null ){conn.close(); }
					} catch(Exception e){throw new RuntimeException(e.getMessage());}
				}
			}
			else {
				errorMsg.setText("ID 또는 PW를 확인해주세요.");
			}
		}
		else {
			errorMsg.setText("ID 또는 PW를 확인해주세요.");
		}
		
		
	}

	public void signinclick(MouseEvent event) {
		this.newScene(event, "Signin", "회원가입");
		
	}
	
	public void idpwfoundclick(MouseEvent event) {
		this.newScene(event, "FindIDnPW", "ID 또는 PW 찾기");
	}
	
	public void underlineON(MouseEvent event) {
		Label label = (Label) event.getSource();
		label.setUnderline(true);
		
	}
	
	public void underlineOFF(MouseEvent event) {
		Label label = (Label) event.getSource();
		label.setUnderline(false);
	}
	
	public void newScene(Event event, String fxml, String title) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle(title);
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {}
		
	}


}
