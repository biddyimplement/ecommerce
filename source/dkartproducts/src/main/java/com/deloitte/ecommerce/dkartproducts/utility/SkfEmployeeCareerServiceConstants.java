package com.deloitte.ecommerce.dkartproducts.utility;


public class SkfEmployeeCareerServiceConstants  {
	
	public static String LIST_ROLES_BASEDON_GRADES ="select grade.gradename,role.genericname,role.roleid from skfrolesmaster role,skfgrademaster grade where businessunitid=:businessunitid and jobfamilyid=:jobfamilyid and role.gradeid = grade.gradeid group by role.genericname,grade.gradename order by grade.gradeid";
}
