package AirportSimulatorTwo.Util;

import lombok.experimental.UtilityClass;
@UtilityClass
public class JspPathHelper {
    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";
    private static final String JSTL_FORMAT = "/WEB-INF/JstlDemo/%s.jsp";
    private static final String JST_FORMS_FORMAT = "/WEB-INF/jspForms/%s.jsp";
    public static String getJspPath (String jspPageName){
        return String.format(JSP_FORMAT, jspPageName);
    }

    public static String getJstlDemoPath (String jstlPageName){
        return String.format(JSTL_FORMAT, jstlPageName);
    }

    public static String getJspFormPath (String jstFormPage){
        return String.format(JST_FORMS_FORMAT, jstFormPage);
    }
}
