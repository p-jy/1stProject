package Swing;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Swing {
	private final List<Member> members = new ArrayList<>();
	private final List<Book> books = new ArrayList<>();
	 private static DefaultListModel<String> listModel = new DefaultListModel<>();
	private CardLayout cardLayout;
	private JFrame frame;
	private JTextField idText;
	private JTextField pwText;
	private JTextField nameText;
	private JTextField numText;
	
	public void initUI() {
		frame = new JFrame("도서 대여 프로그램");
		cardLayout = new CardLayout();
		
		frame.setLayout(cardLayout);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(createMainPanel(), "main");
		frame.add(createMainPanel(), "login");
		frame.add(createMainPanel(), "signup");
		
		frame.setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new GridLayout(3, 1));
		JButton btnLogin = new JButton("로그인");
        JButton btnSignUp = new JButton("회원 가입");
        JButton btnExit = new JButton("종료");

        btnLogin.addActionListener(e-> switchPanel("login"));
        btnSignUp.addActionListener(e-> switchPanel("SignUp"));
        btnExit.addActionListener(e-> exitProgram());
        
        mainPanel.add(btnLogin);
        mainPanel.add(btnSignUp);
        mainPanel.add(btnExit);
        
        return mainPanel;
        
	}
	
	private void switchPanel(String panelName) {
        cardLayout.show(frame.getContentPane(), panelName);
    }
	
	private void exitProgram() {
		int result = JOptionPane.showConfirmDialog(frame, "정말 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	private JPanel createInsertMemberPanel() {
		JPanel insertPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		idText = new JTextField();
		pwText = new JTextField();
		nameText = new JTextField();
		numText = new JTextField();
		JButton btnSignUp = new JButton("회원 가입");
		
		btnSignUp.addActionListener(e-> SignUp());
		
		return insertPanel;
	}
//    btnRegister.addActionListener(e -> registerStudent());
//
//    addComponent(insertPanel, new JLabel("학년"), 0, 0, gbc);
//    addComponent(insertPanel, gradeText, 1, 0, gbc);
//    addComponent(insertPanel, new JLabel("반"), 0, 1, gbc);
//    addComponent(insertPanel, classText, 1, 1, gbc);
//    addComponent(insertPanel, new JLabel("번호"), 0, 2, gbc);
//    addComponent(insertPanel, numText, 1, 2, gbc);
//    addComponent(insertPanel, new JLabel("이름"), 0, 3, gbc);
//    addComponent(insertPanel, nameText, 1, 3, gbc);
//
//    gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
//    insertPanel.add(btnRegister, gbc);
//    return insertPanel;
//}
	private void SignUp() {
		try {
			Member member = new Member(idText.getText(), pwText.getText(), nameText.getText(), numText.getText());
			if (members.contains(member)) throw new Exception("이미 등록된 아이디입니다.");
	        
	        members.add(member);
	        listModel.addElement(member.toString());

	        idText.setText("");
	        pwText.setText("");
	        nameText.setText("");
	        numText.setText("");
	        
	        switchPanel("main");
	        
		} catch(Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}


	
}
