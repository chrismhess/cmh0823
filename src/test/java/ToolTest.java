import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToolTest {
    Tool testTool;
    @BeforeEach
    void init(){
        testTool = new Tool("CHNS", "Chainsaw", "Stihl");
    }
    @Test
    void testGetToolCode() {
        assertEquals("CHNS",testTool.getToolCode());
    }
    @Test
    void getToolType() {
        assertEquals("Chainsaw", testTool.getToolType());
    }
    @Test
    void getToolBrand() {
        assertEquals("Stihl", testTool.getToolBrand());
    }
}