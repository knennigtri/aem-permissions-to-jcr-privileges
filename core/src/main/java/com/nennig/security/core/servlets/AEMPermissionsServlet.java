package com.nennig.security.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.security.AccessControlManager;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nennig.security.core.AEMPermissionsToJCRPrivileges;
/**
 * Basic Servlet to show the relationship between AEM Permissions and JCR Privileges.
 * Access http://localhost:4502/bin/permissions.html to see all relationships
 * Access http://localhost:4502/bin/permissions.selector.html where selector is an AEM Permission
 * to see the corresponding JCR privileges
 * 
 * @author Kevin Nennig (knennig213@gmail.com)
 */
@SlingServlet(paths = "/bin/permissions", extensions="html")
public class AEMPermissionsServlet extends SlingSafeMethodsServlet{
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
    protected void doGet(final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();

		AccessControlManager acm;
		try {
			JackrabbitSession session = (JackrabbitSession)request.getResourceResolver().adaptTo(Session.class);
			acm = session.getAccessControlManager();
			
			String[] selectors = request.getRequestPathInfo().getSelectors();
			ArrayList<String> selectorList = new ArrayList<String>(Arrays.asList(selectors));

			if(selectorList.isEmpty()){
				selectorList.add(AEMPermissionsToJCRPrivileges.READ);
				selectorList.add(AEMPermissionsToJCRPrivileges.MODIFY);
				selectorList.add(AEMPermissionsToJCRPrivileges.CREATE);
				selectorList.add(AEMPermissionsToJCRPrivileges.DELETE);
				selectorList.add(AEMPermissionsToJCRPrivileges.READACL);
				selectorList.add(AEMPermissionsToJCRPrivileges.EDITACL);
				selectorList.add(AEMPermissionsToJCRPrivileges.REPLICATE);
			}
			
			for(String selector : selectorList){
				logger.info("Permission: " +selector);
				jsonObject.put(selector, AEMPermissionsToJCRPrivileges.getPriviledge(acm, selector));
			}
			

			
		} catch (Exception e) {	
			logger.error(e.getMessage());
		} 
	
		response.getWriter().print(jsonObject.toString());
		response.getWriter().close();   
		
	}
}
