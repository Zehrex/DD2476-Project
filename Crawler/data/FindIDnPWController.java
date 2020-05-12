2
https://raw.githubusercontent.com/lilllung09/OwlWordList/master/src/application/FindIDnPWController.java
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FindIDnPWController implements Initializable {
	
	@FXML private ImageView checkbyph;
	@FXML private ImageView checkbyid;
	@FXML private ImageView backtologinicon;
	
	@FXML private TextField inputPH;
	@FXML private TextField inputID;
	
	@FXML private Label userIDbyPH;
	@FXML private Label userPWbyID;
	
	ColorAdjust mouseentered = new ColorAdjust();
	ColorAdjust mouseexit = new ColorAdjust();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mouseentered.setBrightness(-0.5);
		mouseexit.setBrightness(0.0);
		
		
		checkbyph.setOnMouseClicked(event -> clicked(event));
		checkbyph.setOnMouseEntered(event -> mouseenter(event));
		checkbyph.setOnMouseExited(event -> mouseexit(event));
		
		checkbyid.setOnMouseClicked(event -> clicked(event));
		checkbyid.setOnMouseEntered(event -> mouseenter(event));
		checkbyid.setOnMouseExited(event -> mouseexit(event));
		
		backtologinicon.setOnMouseClicked(event -> clicked(event));
		backtologinicon.setOnMouseEntered(event -> mouseenter(event));
		backtologinicon.setOnMouseExited(event -> mouseexit(event));
		
		

	}
	
	public void mouseenter(MouseEvent event) {
		ImageView icon = (ImageView) event.getSource();
		icon.setEffect(mouseentered);
	}
	
	public void mouseexit(MouseEvent event) {
		ImageView icon = (ImageView) event.getSource();
		icon.setEffect(mouseexit);
	}
	
	public void clicked(MouseEvent event) {
		ImageView icon = (ImageView) event.getSource();
		
		if (icon.equals(checkbyph)) {
			if (!inputPH.getText().equals(""))
				findIDPWdb(icon, inputPH.getText());
			
		} else if (icon.equals(checkbyid)) {
			if (!inputPH.getText().equals("")) {
				findIDPWdb(icon, inputID.getText());
			} else {
				userPWbyID.setText("핸드폰 번호가 필요합니다. 입력해주세요.");
			}
			
		} else if (icon.equals(backtologinicon)) {
			System.out.println("backtologinicon");
			this.newScene(event, "Root", "로그인");
			
		} else {
			
		}
	}
	
	public void findIDPWdb(ImageView icon, String input) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String quary = "";
			String column = "";
			
			if (icon.equals(checkbyph)) {
				quary = "SELECT UserID FROM AccountInfo WHERE UserPH='"+ input + "'";
				column = "UserID";
				
			} else if (icon.equals(checkbyid)) {
				quary = "SELECT UserPW FROM AccountInfo WHERE UserPH='"+ inputPH.getText() + "' AND UserID='"+ input + "'";
				column = "UserPW";
				
			}
			
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			
			String rsstr = "";
			
			while(rs.next()){
				rsstr = rs.getString(column);
	            System.out.println(rsstr);
	            
			}
			if (rsstr.equals("")) {   	
            	if (icon.equals(checkbyph)) {
            		userIDbyPH.setText("해당 번호로 가입되어있는 계정이 없습니다.");
    				
    			} else if (icon.equals(checkbyid)) {
    				userPWbyID.setText("해당 ID로 가입되어있는 계정이 없습니다.");
    			}
            	
            } else if(!rsstr.equals("")) {
            	String str = "";
            	for (int i=5; i < rsstr.length() - 1; i++) {
					str += "*";
				}
            	
            	if (icon.equals(checkbyph)) {
            		userIDbyPH.setText("찾은 ID : " + rsstr.substring(0, 4) + str);
            		
    			} else if (icon.equals(checkbyid)) {
    				userPWbyID.setText("찾은 PW : " + rsstr.substring(0, 4) + str);
    				
    			}
            	
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
