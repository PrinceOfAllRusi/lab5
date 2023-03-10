package commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.CreateOrganization
import tools.Input
import tools.result.Result

class Update: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "обновить значение элемента коллекции, id которого равен заданному"
    override fun action( input: Input ): Result? {
        val orgComp = OrganizationComparator()
        val creator = CreateOrganization()
        val idOrg: String = input.getNextWord( null )
        val id = idOrg.toInt()
        var lastOrganization: Organization? = null

        for ( org in orgs ) {
            if ( org.getId() == id ) {
                lastOrganization = org
                break
            }
        }

        val newOrganization: Organization = creator.create( input, lastOrganization )

        orgs.remove( lastOrganization )
        orgs.add( newOrganization )
        orgs.sortWith( orgComp )

        return null
    }
    override fun getDescription(): String = description
}