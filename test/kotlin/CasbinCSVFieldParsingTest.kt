import org.junit.Test
import kotlin.test.assertEquals

/**
 * Test to document and verify the CSV field parsing behavior for enforcement requests.
 * 
 * This addresses the issue where requests like "alice, data2, read" were incorrectly
 * being parsed as 4 fields instead of 3 when there was trailing whitespace or commas.
 */
class CasbinCSVFieldParsingTest {
    
    /**
     * Simulates the field parsing logic that should be applied in CasbinEditorTreeDiff.
     * This is the correct behavior: trim whitespace and filter empty fields.
     */
    private fun parseFields(rawFields: List<String>): Array<String> {
        return rawFields.map { it.trim() }.filter { it.isNotEmpty() }.toTypedArray()
    }
    
    @Test
    fun testBasicRequest_ThreeFields() {
        // Simulating: alice, data2, read
        val rawFields = listOf("alice", " data2", " read")
        val result = parseFields(rawFields)
        
        assertEquals(3, result.size, "Should parse 3 fields from 'alice, data2, read'")
        assertEquals("alice", result[0])
        assertEquals("data2", result[1])
        assertEquals("read", result[2])
    }
    
    @Test
    fun testRequestWithTrailingComma_ShouldIgnoreEmptyField() {
        // Simulating: alice, data2, read,
        val rawFields = listOf("alice", " data2", " read", "")
        val result = parseFields(rawFields)
        
        assertEquals(3, result.size, "Should parse 3 fields even with trailing comma")
        assertEquals("alice", result[0])
        assertEquals("data2", result[1])
        assertEquals("read", result[2])
    }
    
    @Test
    fun testRequestWithLeadingWhitespace() {
        // Simulating:  alice,  data2,  read
        val rawFields = listOf(" alice", "  data2", "  read")
        val result = parseFields(rawFields)
        
        assertEquals(3, result.size, "Should parse 3 fields with leading whitespace trimmed")
        assertEquals("alice", result[0])
        assertEquals("data2", result[1])
        assertEquals("read", result[2])
    }
    
    @Test
    fun testRequestWithTrailingWhitespace() {
        // Simulating: alice , data2 , read 
        val rawFields = listOf("alice ", "data2 ", "read ")
        val result = parseFields(rawFields)
        
        assertEquals(3, result.size, "Should parse 3 fields with trailing whitespace trimmed")
        assertEquals("alice", result[0])
        assertEquals("data2", result[1])
        assertEquals("read", result[2])
    }
    
    @Test
    fun testRequestWithMultipleEmptyFields() {
        // Simulating: alice,, data2,, read,
        val rawFields = listOf("alice", "", " data2", "", " read", "")
        val result = parseFields(rawFields)
        
        assertEquals(3, result.size, "Should filter out all empty fields")
        assertEquals("alice", result[0])
        assertEquals("data2", result[1])
        assertEquals("read", result[2])
    }
    
    @Test
    fun testEmptyLineWithSingleEmptyField() {
        // Simulating an empty line or single empty field
        val rawFields = listOf("")
        val result = parseFields(rawFields)
        
        assertEquals(0, result.size, "Empty field should be filtered out")
    }
    
    @Test
    fun testRBACExampleFromIssue() {
        // The exact example from the issue: "alice, data2, read"
        // When parsed with spaces after commas
        val rawFields = listOf("alice", " data2", " read")
        val result = parseFields(rawFields)
        
        assertEquals(3, result.size, "RBAC request should have exactly 3 fields matching request_definition")
        assertEquals("alice", result[0], "First field should be 'alice' (sub)")
        assertEquals("data2", result[1], "Second field should be 'data2' (obj)")
        assertEquals("read", result[2], "Third field should be 'read' (act)")
    }
}
