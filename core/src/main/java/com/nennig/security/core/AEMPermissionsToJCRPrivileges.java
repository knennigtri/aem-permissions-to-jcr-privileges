package com.nennig.security.core;

import java.util.ArrayList;

import javax.jcr.RepositoryException;
import javax.jcr.security.AccessControlException;
import javax.jcr.security.AccessControlManager;
import javax.jcr.security.Privilege;

/**
 * This class translates the 7 standard AEM Permissions to JCR Privileges
 * 
 * AEM Permission Strings: READ,MODIFY,CREATE,DELETE,READACL,EDITACL,REPLICATE
 *
 * @author Kevin Nennig (nennig@adobe.com)
 */
public class AEMPermissionsToJCRPrivileges {
	public static final String READ = "READ";
	public static final String MODIFY = "MODIFY";
	public static final String CREATE = "CREATE";
	public static final String DELETE = "DELETE";
	public static final String READACL = "READACL";
	public static final String EDITACL = "EDITACL";
	public static final String REPLICATE = "REPLICATE";
	
	//Method to translate the permission string to actual JCR privileges
	public static ArrayList<Privilege> getPriviledge(AccessControlManager acm, String string) throws AccessControlException, RepositoryException{
		if(string.toUpperCase().equals(READ))
			return createReadPrivileges(acm);
		if(string.toUpperCase().equals(MODIFY))
			return createModifyPrivileges(acm);
		if(string.toUpperCase().equals(CREATE))
			return createCreatePrivileges(acm);
		if(string.toUpperCase().equals(DELETE))
			return createDeletePrivileges(acm);
		if(string.toUpperCase().equals(READACL))
			return createReadACLPrivileges(acm);
		if(string.toUpperCase().equals(EDITACL))
			return createEditACLPrivileges(acm);
		if(string.toUpperCase().equals(REPLICATE))
			return createReplicatePrivileges(acm);
		return null;
	}

	//Method to return the proper JCR privileges for the AEM permission "Read"
	private static ArrayList<Privilege> createReadPrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName(Privilege.JCR_READ));
		return list;
	}
	
	//Method to return the proper JCR privileges for the AEM permission "Modify"
	private static ArrayList<Privilege> createModifyPrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName(Privilege.JCR_MODIFY_PROPERTIES));
		list.add(acm.privilegeFromName(Privilege.JCR_LOCK_MANAGEMENT));
		list.add(acm.privilegeFromName(Privilege.JCR_VERSION_MANAGEMENT));
		list.add(acm.privilegeFromName(Privilege.JCR_REMOVE_CHILD_NODES));
		list.add(acm.privilegeFromName(Privilege.JCR_REMOVE_NODE));
		list.add(acm.privilegeFromName(Privilege.JCR_ADD_CHILD_NODES));
		list.add(acm.privilegeFromName(Privilege.JCR_NODE_TYPE_MANAGEMENT));
		return list;
	}
	
	//Method to return the proper JCR privileges for the AEM permission "Create"
	private static ArrayList<Privilege> createCreatePrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName(Privilege.JCR_ADD_CHILD_NODES));
		list.add(acm.privilegeFromName(Privilege.JCR_NODE_TYPE_MANAGEMENT));
		return list;
	}
	
	//Method to return the proper JCR privileges for the AEM permission "Delete"
	private static ArrayList<Privilege> createDeletePrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName(Privilege.JCR_REMOVE_CHILD_NODES));
		list.add(acm.privilegeFromName(Privilege.JCR_REMOVE_NODE));
		return list;
	}
	
	//Method to return the proper JCR privileges for the AEM permission "Read ACL"
	private static ArrayList<Privilege> createReadACLPrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName(Privilege.JCR_READ_ACCESS_CONTROL));
		return list;
	}
	
	//Method to return the proper JCR privileges for the AEM permission "Edit ACL"
	private static ArrayList<Privilege> createEditACLPrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName(Privilege.JCR_MODIFY_ACCESS_CONTROL));
		return list;
	}
	
	//Method to return the proper JCR privileges for the AEM permission "Replicate"
	private static ArrayList<Privilege> createReplicatePrivileges(AccessControlManager acm) throws AccessControlException, RepositoryException{
		ArrayList<Privilege> list = new ArrayList<Privilege>();
		list.add(acm.privilegeFromName("crx:replicate"));
		return list;
	}
}
