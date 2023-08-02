package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import data.vo.Message;

/*
 * Message
 * 	DAO : Data Access Object의 약자로 데이터처리하는 객체를 만들 때 붙여서 만들기도 한다.
 */

public class MessagesDAO extends DAO{
	
	// 데이터 등록을 처리할 메서드
	public static int createMessage(String target, String body, String pass) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> obj = new HashMap<>();
		obj.put("target", target);
		obj.put("body", body);
		obj.put("pass", pass);
		
		int r = session.insert("messages.create", obj);
		session.close();
		
		return r;
	}
	// 특정 동물에 대한 메세지들을 읽어오는 것을 처리할 메서드
	public static List<Message> readMessages(String target){
		SqlSession session = factory.openSession();
		List<Message> li = session.selectList("messages.readByTarget", target);
		session.close();
		
		return li;
	}
	// 특정 메세지 비밀번호 가져오는 메서드
	public static String findByPass(String no) {
		SqlSession session = factory.openSession();
		String pass = session.selectOne("messages.findByPass", no);
		
		return pass;
	}
	// 특정 메세지를 수정하는 메서드
	public static int modifyMessage(String no, String body) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> obj = new HashMap<>();
		obj.put("no", no);
		obj.put("body", body);
		
		int r = session.update("messages.update", obj);

		session.close();
		return r;
	}
	// 특정 메세지를 삭제하는 메서드
	public static int deleteMessage(String no) {
		SqlSession session = factory.openSession(true);
		
		int r = session.delete("messages.delete", no);
		session.close();
		
		return r;
	}
}
