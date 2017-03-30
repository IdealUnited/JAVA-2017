package com.validate.controller;

import java.awt.Color;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.NormalFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/*
 * 功能：调用AuthServlet可以生成一个验证码图片
 * 调用格式: /servlet/AuthServlet
 * 
 * 时间：2007-9-4  
 */
public class AuthServlet extends AbstractController {

	private static final long serialVersionUID = 8433932715396048208L;
	private String actionFlg; // 验证码处理标签：随机产生: 1 /"8888": 0

	public void setActionFlg(String actionFlg) {
		this.actionFlg = actionFlg;
	}

	@Override
	protected final ModelAndView handleRequestInternal(
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();// request.getSession(false)当参数为false时，有可能不创建session
		response.setContentType("image/gif");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(0, 95, 0)));
		cs.setFilterFactory(new NormalFilterFactory());
		cs.setFontFactory(new RandomFontFactory());

		ServletOutputStream out = response.getOutputStream();
		EncoderHelper.getChallangeAndWriteImage(cs, "PNG", out);
		out.close();

		String randCode = cs.getCurWord();
		if ("0".equals(actionFlg)) {
			randCode = "8888";
		}

		session.setAttribute("rand", randCode);
		return null;
	}

	static public String getAuthCode(HttpSession session) {// 返回验证
		return (String) session.getAttribute("rand");
	}
}
