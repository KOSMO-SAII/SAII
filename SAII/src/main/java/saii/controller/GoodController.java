package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.goodDAO;
import saii.domain.mainboardDAO;
import saii.domain.memberDAO;
import saii.dto.goodDTO;
import saii.dto.mainboardDTO;
import saii.dto.memberDTO;

@WebServlet("/good")
public class GoodController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(Integer.parseInt(req.getParameter("mode")) == 0) {
			String m_id = req.getParameter("m_id");
			
			memberDTO memdto = new memberDTO();
			memberDAO memdao = new memberDAO();
			String id = req.getSession().getAttribute("UserId").toString();
			memdto = memdao.userinfo(id);
			
			goodDAO gdao = new goodDAO();
			gdao.pushGood(m_id, memdto.getNickname());
			
			memdao.close();
			gdao.close();
		}
		if(Integer.parseInt(req.getParameter("mode")) == 1) {
			String m_id = req.getParameter("m_id");
			
			memberDTO memdto = new memberDTO();
			memberDAO memdao = new memberDAO();
			String id = req.getSession().getAttribute("UserId").toString();
			memdto = memdao.userinfo(id);
			
			goodDAO gdao = new goodDAO();
			gdao.cancelGood(m_id, memdto.getNickname());
			
			memdao.close();
			gdao.close();
		}
		
		// 게시글 가져오기
		mainboardDAO dao = new mainboardDAO();
		String m_id = req.getParameter("m_id");
		mainboardDTO dto = dao.selectView(m_id);
								
		//dto.setContent(dto.getContent().replaceAll("/r/n", "<br/>"));
								
		memberDTO memdto = new memberDTO();		
		memberDAO memdao = new memberDAO();
		String id = req.getSession().getAttribute("UserId").toString();
		memdto = memdao.userinfo(id);
					
		goodDTO gdto = new goodDTO();
					
		goodDAO gdao = new goodDAO();
		//boolean goodWhether = gdao.goodWhether(dto.getM_id(), memdto.getNickname());
					
		memdao.close();
		gdao.close();
					
		//req.setAttribute("goodWhether", goodWhether);
		req.setAttribute("memdto", memdto);
								
		dao.close();
				
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/saii/MainView.jsp").forward(req, resp);
	}
}
