package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.EmaillistVo;

@Repository
public class EmaillistDao {
	
	//Sql에 있는 다섯가지 문법?
	//sqlsession.insert("emaillist.insert",vo);
	//sqlsession.update("emaillist.update",vo);
	//sqlsession.delete("emaillist.delete",no);
	//sqlsession.selectOne("emaillist.getbyno",no);
	//sqlsession.selectList("emaillist.getlist",page);
	
	@Autowired
	private SqlSession sqlsession; 
	
	//sql세션 이용해서 emaillist.xml에서 첫번째로 지정해준 namespace가 emaillist이고
	//getList는 emaillist.xml에서 정해준 id값이므로
	//"emaillist.getList" 로 경로지정해주면 그 안에 있는 쿼리문 돌아감.
	public List<EmaillistVo> getList(){
		List<EmaillistVo> list = sqlsession.selectList("emaillist.getList");
		return list;
	}
	
	public int insert(EmaillistVo vo) {
		return sqlsession.insert("emaillist.insert",vo);
	}
	
	public EmaillistVo get(int no) {
		EmaillistVo vo = sqlsession.selectOne("emaillist.getByNo", no);
		System.out.println(vo.toString());
		return vo;
	}
	

}
