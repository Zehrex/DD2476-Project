2
https://raw.githubusercontent.com/lilllung09/OwlWordList/master/src/application/SigninController.java
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
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SigninController implements Initializable {

	@FXML private ImageView signidable;
	@FXML private ImageView signnickable;
	@FXML private ImageView signinok;
	@FXML private ImageView backtologinicon;
	@FXML private ImageView rePWiseq;
	
	@FXML private ImageView backgroundimg;
	
	@FXML private TextField inputID;
	@FXML private TextField inputNICK;
	@FXML private TextField inputPW;
	@FXML private TextField inputREPW;
	@FXML private TextField inputPH;
	
	ColorAdjust mouseentered = new ColorAdjust();
	ColorAdjust mouseexit = new ColorAdjust();
	
	Image image = null;
	
	boolean idok = false;
	boolean nickok = false;
	boolean pwok = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mouseentered.setBrightness(-0.5);
		mouseexit.setBrightness(0.0);
		
		backgroundimg.setOnMouseEntered(event -> mouseenter(event));
		
		signidable.setOnMouseEntered(event -> mouseenter(event));
		signidable.setOnMouseExited(event -> mouseexit(event));
		signidable.setOnMouseClicked(event -> clicked(event));
		
		signnickable.setOnMouseEntered(event -> mouseenter(event));
		signnickable.setOnMouseExited(event -> mouseexit(event));
		signnickable.setOnMouseClicked(event -> clicked(event));
		
		inputREPW.setOnKeyReleased(event -> checkPW());
		inputPW.setOnKeyReleased(event -> checkPW());
		
		
		inputID.setOnMouseClicked(event -> clickedT(event));
		inputNICK.setOnMouseClicked(event -> clickedT(event));
		
		signinok.setOnMouseClicked(event -> logintodb(event));
		
		

	}
	
	public void logintodb(Event event) {
		image = new Image(getClass().getResourceAsStream("/resources/signno.png"));
		
		if (idok && nickok && pwok) {
			if (!inputID.getText().equals("") && inputID.getText().toString().length() > 5) {
				if (!inputNICK.getText().equals("") && inputID.getText().toString().length() > 2) {
					if (!inputPW.getText().equals("") && inputID.getText().toString().length() > 5) {
						if (!inputREPW.getText().equals("") && inputID.getText().toString().length() > 5) {
							if (inputPW.getText().equals(inputREPW.getText()) && !inputPW.getText().equals(inputID.getText())) {
								if (!inputPH.getText().equals("")) {
									image = new Image(getClass().getResourceAsStream("/resources/signok.png"));
									signinok.setImage(image);
									this.signupdbok(event, inputID.getText(), inputREPW.getText(), inputNICK.getText(), inputPH.getText());
									
								} else {
									signinok.setImage(image);
								}
							} else {
								signinok.setImage(image);
							}
						} else {
							signinok.setImage(image);
						}
					} else {
						signinok.setImage(image);
					}
				} else {
					signinok.setImage(image);
				}
			} else {
				signinok.setImage(image);
			}
		} else {
			signinok.setImage(image);
		}
		
	}
	
	public void mouseenter(MouseEvent event) {
		ImageView icon = (ImageView) event.getSource();
		if (icon.equals(backgroundimg)) {
			image = new Image(getClass().getResourceAsStream("/resources/signno.png"));
			
			if (idok && nickok && pwok) {
				if (!inputID.getText().equals("") && inputID.getText().toString().length() > 5) {
					if (!inputNICK.getText().equals("") && inputID.getText().toString().length() > 2) {
						if (!inputPW.getText().equals("") && inputID.getText().toString().length() > 5) {
							if (!inputREPW.getText().equals("") && inputID.getText().toString().length() > 5) {
								if (inputPW.getText().equals(inputREPW.getText()) && !inputPW.getText().equals(inputID.getText())) {
									if (!inputPH.getText().equals("")) {
										image = new Image(getClass().getResourceAsStream("/resources/signok.png"));
										signinok.setImage(image);
										
									} else {
										signinok.setImage(image);
									}
								} else {
									signinok.setImage(image);
								}
							} else {
								signinok.setImage(image);
							}
						} else {
							signinok.setImage(image);
						}
					} else {
						signinok.setImage(image);
					}
				} else {
					signinok.setImage(image);
				}
			} else {
				signinok.setImage(image);
			}
		} else {
			icon.setEffect(mouseentered);
		}
	}
	
	public void mouseexit(MouseEvent event) {
		ImageView icon = (ImageView) event.getSource();
		icon.setEffect(mouseexit);
	}
	
	public void clickedT(MouseEvent event) {
		TextField field = (TextField) event.getSource();
		image = new Image(getClass().getResourceAsStream("/resources/isok.png"));
		
		if (field.equals(inputID)) {
			signidable.setImage(image);
			
		}
		else if (field.equals(inputNICK)) {
			signnickable.setImage(image);
			
		}
		
	}
	
	public void checkPW() {
		if (inputPW.getText().equals(inputREPW.getText())) {
			image = new Image(getClass().getResourceAsStream("/resources/rePWeq.png"));
			rePWiseq.setImage(image);
			pwok = true;
			
		} else {
			image = new Image(getClass().getResourceAsStream("/resources/rePWnoeq.png"));
			rePWiseq.setImage(image);
			pwok = false;
			
		}
		
	}
	
	public void clicked(MouseEvent event) {
		ImageView icon = (ImageView) event.getSource();

		if (icon.equals(signidable)) {
			System.out.println("signidable");
			signidable(icon, inputID.getText());
			
			
		} else if (icon.equals(signnickable)) {
			System.out.println("signnickable");
			signidable(icon, inputNICK.getText());
			
		} else if (icon.equals(signinok)) {
			System.out.println("signinok");
			
		} else if (icon.equals(backtologinicon)) {
			System.out.println("backtologinicon");
			this.newScene(event, "Root", "로그인");
			
		} else {
			
		}
	}
	
	public void signidable(ImageView icon, String input) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String quary = "";
			String column = "";
			
			if (icon.equals(signidable)) {
				quary = "SELECT UserID FROM AccountInfo WHERE UserID='"+ input + "'";
				column = "UserID";
				
			} else if (icon.equals(signnickable)) {
				quary = "SELECT UserNAME FROM AccountInfo WHERE UserNAME='"+ input + "'";
				column = "UserNAME";
				
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
            	//System.out.println("없음 가능");
				image = new Image(getClass().getResourceAsStream("/resources/able.png"));
            	icon.setImage(image);
            	
            	if (icon.equals(signidable)) {
    				idok = true;
    			} else if (icon.equals(signnickable)) {
    				nickok = true;
    			}
            	
            } else if(rsstr.equals(input)) {
            	//System.out.println("있음 불가");
            	image = new Image(getClass().getResourceAsStream("/resources/noable.png"));
            	icon.setImage(image);
            	
            	if (icon.equals(signidable)) {
    				idok = false;
    			} else if (icon.equals(signnickable)) {
    				nickok = false;
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
	
	public void signupdbok(Event event, String id, String pw, String name, String ph) {
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
        	String quary = "INSERT INTO AccountInfo(UserID, UserPW, UserNAME, UserPH)"
        			+ "VALUES(?,?,?,?)";
        	
    		
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            
            pstm.setString(1, id);
            pstm.setString(2, pw);
            pstm.setString(3, name);
            pstm.setString(4, ph);
            
            pstm.executeUpdate();   
            
            this.newScene(event, "Popup", "팝업");
            RootController.thisuserID = id;
            
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            
        } finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
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
