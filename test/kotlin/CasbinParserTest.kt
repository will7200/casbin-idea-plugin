import com.intellij.testFramework.ParsingTestCase
import io.github.will7200.plugins.casbin.language.parser.CasbinParserDefinition

class CasbinParserTest : ParsingTestCase("examples", "conf", CasbinParserDefinition()) {
    fun testabac_model() = doTest(true)
    fun testabac_rule_model() = doTest(true)
    fun testabac_rule_with_domains_model() = doTest(true)
    fun testbasic_model() = doTest(true)
    fun testbasic_with_root_model() = doTest(true)
    fun testbasic_without_resources_model() = doTest(true)
    fun testbasic_without_users_model() = doTest(true)
    fun testgroup_with_domain_model() = doTest(true)
    fun testipmatch_model() = doTest(true)
    fun testkeymatch_model() = doTest(true)
    fun testkeymatch2_model() = doTest(true)
    fun testpriority_model() = doTest(true)
    fun testrbac_model() = doTest(true)
    fun testrbac_with_deny_model() = doTest(true)
    fun testrbac_with_domains_model() = doTest(true)
    fun testrbac_with_not_deny_model() = doTest(true)
    fun testrbac_with_resource_roles_model() = doTest(true)

    override fun getTestDataPath() = "test/resources"
    override fun includeRanges(): Boolean {
        return true
    }
}