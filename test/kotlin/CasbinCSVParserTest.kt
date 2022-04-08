import com.intellij.testFramework.ParsingTestCase
import io.github.will7200.plugins.casbincsv.language.parser.CasbinCSVParserDefinition

class CasbinCSVParserTest : ParsingTestCase("examples_csv", "casbincsv", CasbinCSVParserDefinition()) {
    fun testmodel_resources_with_object() = doTest(true)
    override fun getTestDataPath() = "test/resources"
    override fun includeRanges(): Boolean {
        return true
    }
}