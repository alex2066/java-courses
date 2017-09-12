import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {
	private Calculator calc;
	
	@Before
	public void init() {
		this.calc = new Calculator();
	}
	
	@After
	public void tearDown() {
		this.calc = null;
	}
	
	@Test
	public void testSumFunction() {
		assertThat(this.calc.sum(7.0, 3.0), is(10.0));
	}
	
	@Test
	public void testDivisionFunction() {
		assertThat(this.calc.division(8.0, 2.0), is(4.0));
		assertThat(this.calc.division(7.0, 0.0), is(Double.POSITIVE_INFINITY));
		assertThat(this.calc.division(-2.0, 0.0), is(Double.NEGATIVE_INFINITY));
		assertThat(this.calc.division(0.0, 0.0), is(Double.NaN));
	}
}