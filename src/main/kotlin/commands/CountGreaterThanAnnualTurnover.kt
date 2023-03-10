package commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.Input
import tools.result.Result

class CountGreaterThanAnnualTurnover: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести количество элементов, значение поля annualTurnover которых больше заданного"
    override fun action(input: Input): Result? {
        val turnover: Double = input.getNextWord(null).toDouble()
        var count = 0
        for (org in orgs) {
            if (org!!.getAnnualTurnover()!! > turnover) {
                count++
            }
        }

        input.outMsg(count.toString() + "\n")

        return null
    }
    override fun getDescription(): String = description
}