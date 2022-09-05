package MemberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import DB.DBConn;
import MemberVO.MemberVO;

public class MemberDAO {
	
	public MemberDAO() {
		
	}

	
	private Connection conn; 
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	
	public MemberVO getMemberVO1(String mem_id) throws ClassNotFoundException, SQLException{
	       
        MemberVO memberVO = new MemberVO();
        conn = new DBConn().getConnection();
        try {
        	pstmt = conn.prepareStatement("select * from members where mem_id=?");
        	pstmt.setString(1, mem_id);
           
            rs = pstmt.executeQuery();
           
            if(rs.next()){
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return memberVO;    
    }
	
	public Vector getMemberVO(String mem_id) throws ClassNotFoundException, SQLException{
	       
        Vector data = new Vector();
        conn = new DBConn().getConnection();
        MemberVO memberVO = new MemberVO();
        try {
        	pstmt = conn.prepareStatement("select * from members where mem_id = ?");
        	pstmt.setString(1, mem_id);
           
            rs = pstmt.executeQuery();
           
            if(rs.next()){
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				data.add(memberVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return data;   
    }//getMemberList()
	
	public Vector getColumn(){
		Vector col = new Vector();
        col.add("가게이름");
        col.add("닉네임");
        col.add("별점");
        col.add("내용");

        return col;
    }//getColumn
	
	public Vector getComment(String mem_nick) throws ClassNotFoundException, SQLException {
		Vector data = new Vector();
		MemberVO memberVO = new MemberVO();

        conn = new DBConn().getConnection();
        try {
        	pstmt = conn.prepareStatement("select * from comments where mem_nick = ?");
        	pstmt.setString(1, mem_nick);
           
            rs = pstmt.executeQuery(); 
           
            while(rs.next()){
				String rest_name = rs.getString("rest_name");
                String nick = rs.getString("mem_nick");
                String comm_star = rs.getString("comm_star");
                String comm_review = rs.getString("comm_review");
               
                Vector row = new Vector();
                row.add(rest_name);
                row.add(nick);
                row.add(comm_star);
                row.add(comm_review);
               
                data.add(row);   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
        return data;
	}
	public Vector getComment_review(String mem_nick) throws ClassNotFoundException, SQLException {
		Vector data = new Vector();
		MemberVO memberVO = new MemberVO();

        conn = new DBConn().getConnection();
        try {
        	pstmt = conn.prepareStatement("select comm_review from comments where mem_nick = ?");
        	pstmt.setString(1, mem_nick);
           
            rs = pstmt.executeQuery(); 
           
            while(rs.next()){
                String comm_review = rs.getString("comm_review");
               
                Vector row = new Vector();
                row.add(comm_review);
               
                data.add(row);   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
        return data;
	}
	
	public int countComment() throws ClassNotFoundException, SQLException {
		int count=0;
		conn = new DBConn().getConnection();
		try {
			pstmt = conn.prepareStatement("select count(*) cnt from comments");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt > 0) {
					return cnt;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public int update(MemberVO memberVO) 
			throws ClassNotFoundException, SQLException {
		
		int updateCount=0;
		
		conn = new DBConn().getConnection();
		
		try {
			if(memberVO.getMem_id().length() == 0 || memberVO.getMem_email().length() == 0 || memberVO.getMem_name().length() ==0
					|| memberVO.getMem_pw().length() ==0 || memberVO.getMem_phone().length() == 0 
					|| memberVO.getMem_nick().length() == 0) {
				return -2;
			}
			pstmt = conn.prepareStatement(
					"update members set mem_pw = ?, mem_name = ?, mem_nick = ?, mem_email = ?, mem_phone = ? where mem_id = ?");
			pstmt.setString(1, memberVO.getMem_pw());
			pstmt.setString(2, memberVO.getMem_name());
			pstmt.setString(3, memberVO.getMem_nick());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setString(5, memberVO.getMem_phone());
			pstmt.setString(6, memberVO.getMem_id());
			updateCount = pstmt.executeUpdate();

			if(updateCount>0) {
				return 1;
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
	public int save(MemberVO memberVO) throws ClassNotFoundException, SQLException {
		conn = new DBConn().getConnection();
		
		try {
			if(memberVO.getMem_id().length() == 0 || memberVO.getMem_email().length() == 0 || memberVO.getMem_name().length() ==0
					|| memberVO.getMem_pw().length() ==0 || memberVO.getMem_phone().length() == 0 
					|| memberVO.getMem_nick().length() == 0) {
				return -2;
			}
			
			pstmt = conn.prepareStatement("insert into members values(seq_rest.nextval, ?,?,?,?,?,?, sysdate)");
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_nick());
			pstmt.setString(3, memberVO.getMem_id());
			pstmt.setString(4, memberVO.getMem_pw());
			pstmt.setString(6, memberVO.getMem_email());
			pstmt.setString(5, memberVO.getMem_phone());
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean findExistID(String str) throws ClassNotFoundException, SQLException {
		conn = new DBConn().getConnection();
		try {
			pstmt = conn.prepareStatement("select count(*) cnt from members where mem_id = ?");
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt > 0) {
					return true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;

	}
	
	public int findByUsernameAndPassword(String getMem_id, String getMem_pw) throws ClassNotFoundException, SQLException {

		conn = new DBConn().getConnection();
		
		try {
			pstmt = conn.prepareStatement("select mem_id from members where mem_id = ? and mem_pw = ?");
			pstmt.setString(1, getMem_id);
			pstmt.setString(2, getMem_pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 

				return 1;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return -1; 
	}
	
	
	public Vector<MemberVO> findByAll() throws ClassNotFoundException, SQLException{
		conn = new DBConn().getConnection();
		Vector<MemberVO> memberVOs = new Vector<>();
		try {
			pstmt = conn.prepareStatement("select * from members");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_num(rs.getLong("mem_num"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pw(rs.getString("mem_pw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_regi(rs.getTimestamp("mem_regi"));
				memberVOs.add(memberVO);
			}
			return memberVOs;
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

