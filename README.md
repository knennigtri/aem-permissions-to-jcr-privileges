# AEM Project to show Permissions helper class


This helper class, AEMPermissionsToJCRPrivileges, takes in an AEM permission and returns an ArrayList<Privileges> of the JCR Privileges.

You can test these relationships by using the AEMPermissionServlet

Below is the full mapping this class does.
`"READ": ["jcr:read"]`
`"MODIFY": ["jcr:modifyProperties","jcr:lockManagement","jcr:versionManagement","jcr:removeChildNodes","jcr:removeNode","jcr:addChildNodes","jcr:nodeTypeManagement"]`
`"CREATE": ["jcr:addChildNodes","jcr:nodeTypeManagement"]`
`"DELETE": ["jcr:removeChildNodes","jcr:removeNode"]`
`"READACL": ["jcr:readAccessControl"]`
`"EDITACL": ["jcr:modifyAccessControl"]`
`"REPLICATE": ["crx:replicate"]`

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* NOT USED ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* NOT USED ui.content: contains sample content using the components from the ui.apps
* NOT USED ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* NOT USED ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish
    
Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
