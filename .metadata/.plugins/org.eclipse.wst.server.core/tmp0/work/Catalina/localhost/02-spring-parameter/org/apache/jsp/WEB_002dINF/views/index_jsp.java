/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.58
 * Generated at: 2022-10-22 08:59:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h1>Hello Spring</h1>\r\n");
      out.write("	<!-- <img src=\"images/layout/spring_logo.png\"> -->\r\n");
      out.write("<!-- 	a태그에서 get방식으로 넘길때에는 ? 사용 (쿼리 스트링) -->\r\n");
      out.write("	<ul> \r\n");
      out.write("		<li><a href=\"Parameter01.do?data01=10&data02=20&data03=30&data03=40\">parameter01-query-sting으로 데이터 보내보기</a></li>\r\n");
      out.write("		<li><a href=\"Parameter02.do?data01=10&data02=20&data03=30&data03=40\">parameter02-query-sting으로 데이터 보내보기</a></li>\r\n");
      out.write("		<li><a href=\"Parameter03.do?data01=10&date02=20&data03=30&data03=40\">parameter03-query-sting으로 데이터 보내보기</a></li>\r\n");
      out.write("		<li><a href=\"parameter/10/20/30\">parameter06-rest로 데이터 보내기</a></li>\r\n");
      out.write("		<!-- data03=30&data03=40 - 똑같은 데이터를 날리면 뒤에 것이 나온다(이럴땐 배열로 받아야한다) -->\r\n");
      out.write("	</ul>\r\n");
      out.write("	\r\n");
      out.write("	<form method=\"GET\" action=\"Parameter03.do\"> \r\n");
      out.write("		<legend>Parameter03.do</legend>\r\n");
      out.write("		<div><label><span>data01</span><input type=\"text\" name=\"data01\"></label></div>\r\n");
      out.write("		<div><label><span>data02</span><input type=\"text\" name=\"data02\"></label></div>\r\n");
      out.write("		<div>\r\n");
      out.write("			<label><span>data03-30</span><input type=\"checkbox\" name=\"data03\" value=\"30\"></label>\r\n");
      out.write("			<label><span>data03-40</span><input type=\"checkbox\" name=\"data03\" value=\"40\"></label>\r\n");
      out.write("		</div>\r\n");
      out.write("		<button>Parameter03.do로 전송</button>\r\n");
      out.write("	</form>\r\n");
      out.write("	<hr />\r\n");
      out.write("	<form method=\"GET\" action=\"Parameter04.do\">\r\n");
      out.write("		<legend>Parameter04.do</legend>\r\n");
      out.write("		<div><label><span>data01</span><input type=\"text\" name=\"data01\"></label></div>\r\n");
      out.write("		<div><label><span>data02</span><input type=\"text\" name=\"data02\"></label></div>\r\n");
      out.write("		<div>\r\n");
      out.write("			<label><span>data03-30</span><input type=\"checkbox\" name=\"data03\" value=\"30\"></label>\r\n");
      out.write("			<label><span>data03-40</span><input type=\"checkbox\" name=\"data03\" value=\"40\"></label>\r\n");
      out.write("		</div>\r\n");
      out.write("		<button>Parameter04.do로 전송</button>\r\n");
      out.write("	</form>\r\n");
      out.write("	<hr />\r\n");
      out.write("	<form method=\"GET\" action=\"Parameter05.do\">\r\n");
      out.write("		<legend>Parameter05.do</legend>\r\n");
      out.write("		<div><label><span>data01</span><input type=\"text\" name=\"data01\"></label></div>\r\n");
      out.write("		<div><label><span>data02</span><input type=\"text\" name=\"data02\"></label></div>\r\n");
      out.write("		<div>\r\n");
      out.write("			<label><span>data03-30</span><input type=\"checkbox\" name=\"data03\" value=\"30\"></label>\r\n");
      out.write("			<label><span>data03-40</span><input type=\"checkbox\" name=\"data03\" value=\"40\"></label>\r\n");
      out.write("		</div>\r\n");
      out.write("		<button>Parameter05.do로 전송</button>\r\n");
      out.write("	</form>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}