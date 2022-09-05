package javaoraMain;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javaoraDAO.JavaoraDAO;
import javaoraVO.JavaoraComVO;
import javaoraVO.JavaoraMemVO;
import javaoraVO.JavaoraRestVO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import db.TelInfoVO;
//import db.comments;

//class test_test {
//	private String string;
//
//	public test_test(String string) {
//		super();
//		this.string = string;
//	}
//	
//}
public class JavaoraMain_2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	public JavaoraMain_2() {
	}

	public JavaoraMain_2(String rest_num, String mem_id, int temp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaoraMain_2 frame = new JavaoraMain_2(rest_num, mem_id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */
//	public String java_01(String st) {
//		
//		return "str";
//	}
	public JavaoraMain_2(String rest_num, String mem_id) throws ClassNotFoundException, SQLException {

		System.out.println(rest_num +"식당 num");
		JavaoraDAO tidao = new JavaoraDAO();
//		String a = tidao.abc;
//		System.out.println(a +"요고");
		JavaoraRestVO tiArray = tidao.getAllInfo_rest(rest_num); // 식당 정보
		ArrayList<JavaoraComVO> com_Array = tidao.getAllInfo_com(rest_num); // 리뷰 정보
		ArrayList<JavaoraMemVO> mem_Array = tidao.getAllInfo_mem(mem_id); // 회원 정보

		// System.out.println(tiArray.get(1).getRest_address());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// -------------------------------------------------------- 식당정보들
		JPanel panel = new JPanel();
		panel.setBounds(64, 47, 741, 179);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel panel_resname = new JPanel(); // 사진
		panel.add(panel_resname);

		JLabel ima = new JLabel(); // 사진
		ImageIcon bsImg = new ImageIcon(JavaoraMain_2.class.getResource("/image/"+rest_num+".jpg"));
		Image img = bsImg.getImage();
		Image updateImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH); // 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
		// 새로운 Image 객체로 ImageIcon 객체 생성
		ImageIcon updateIcon = new ImageIcon(updateImg);

		JLabel imgLabel = new JLabel(updateIcon);

		ima.setIcon(bsImg);
		ima.setPreferredSize(new Dimension(200, 100)); // 크기조정
		panel_resname.add(imgLabel);

		JPanel panel_resdb = new JPanel();
		panel.add(panel_resdb);
//		panel.setPreferredSize(new Dimension(350, 100));
		panel_resdb.setLayout(new GridLayout(0, 1));

		// --------------------------------------------------------------

		JLabel rest_name = new JLabel("이름 : " + tiArray.getRest_name()); // 이름
		panel_resdb.add(rest_name);
		System.out.println(rest_name.getPreferredSize());
		rest_name.setPreferredSize(new Dimension(150, 30));
		JLabel address = new JLabel("주소 : " + tiArray.getRest_address());
		panel_resdb.add(address);

		JLabel phone = new JLabel("전화번호 : " + tiArray.getRest_phone());
		panel_resdb.add(phone);

		JLabel opent = new JLabel("영업시간 : " + tiArray.getRest_opent());
		panel_resdb.add(opent);

		JLabel closed = new JLabel("휴무일 : " + tiArray.getRest_closed());
		panel_resdb.add(closed);

//				System.out.println(rest_name.getPreferredSize());

//		ImagePanel pnMiddle = new ImagePanel(new ImageIcon("img/bawe.png").getImage());
//		panel.add(pnMiddle);
//		pnMiddle.setLayout(null);

		ImageIcon menuImg = new ImageIcon(JavaoraMain_2.class.getResource("/image/"+rest_num+"menu.jpg")); // 이미지경로 db에서 받아오기
		Image menu = menuImg.getImage();

		// 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
		Image updateImg_1 = menu.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
		// 새로운 Image 객체로 ImageIcon 객체 생성
		ImageIcon updateIcon_1 = new ImageIcon(updateImg_1);
		JLabel menu_ima = new JLabel(updateIcon_1);
		menu_ima.setBounds(64, 236, 741, 244);
		contentPane.add(menu_ima);
// ------------------------------------------------- 리뷰작성
		JComboBox comboBox = new JComboBox();
		String str[] = { "★★★★★", "★★★★", "★★★", "★★", "★" };
		for (int i = 0; i < str.length; i++) {
			comboBox.addItem(str[i]);
		}
		comboBox.setBounds(64, 507, 100, 37);
		contentPane.add(comboBox);

		System.out.println(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());

		JTextArea textArea = new JTextArea();
		textArea.setBounds(202, 603, 593, 31);
		// textArea.setCaretPosition(textArea.getDocument().getLength());

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(195, 512, 593, 31);
		contentPane.add(scrollPane);

		JButton button = new JButton("등록하기"); // 리뷰등록하기
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				textArea.get
				int abc = comboBox.getItemAt(comboBox.getSelectedIndex()).toString().length(); // 별의개수(벌점)

				System.out.println(abc);
				System.out.println(textArea.getText());
				boolean b1;
				try {
					b1 = tidao.insert_nametel(rest_num,tiArray.getRest_name(), mem_Array.get(0).getMem_nick(), 
							mem_id,abc,textArea.getText());
				if (b1) {
					JOptionPane.showMessageDialog(null, "등록 완료");
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "등록 실패");
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		button.setBounds(811, 510, 100, 31);
		contentPane.add(button);
//------------------리뷰 가져오기
		ArrayList<String> list = new ArrayList();
		String revi;
		ArrayList<JavaoraComVO> com_ti = tidao.getAllInfo_com(rest_num);

		for (JavaoraComVO imsi : com_ti) {

			revi = (imsi.getComm_star() + "점 | " + imsi.getMem_nick() + " | " + imsi.getComm_review() + "\n");
			list.add(revi);
		}
		String[] result = list.toArray(new String[0]);

		// 리뷰 보여주는 리스트
		JScrollPane total_List_Scroll = new JScrollPane();

		total_List_Scroll.setBounds(64, 578, 847, 103);

		contentPane.add(total_List_Scroll);

		JList list_01 = new JList(result);
//		list_01.setBounds(64, 578, 847, 103);
//		contentPane.add(list_01);
		total_List_Scroll.setViewportView(list_01);

		JButton btnNewButton = new JButton("창 닫기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 16));
		btnNewButton.setBounds(833, 10, 131, 37);
		contentPane.add(btnNewButton);
	}
}
