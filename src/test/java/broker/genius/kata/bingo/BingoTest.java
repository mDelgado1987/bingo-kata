package broker.genius.kata.bingo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/html"},
        features = {"classpath:features"},
        glue = "broker.genius.kata.bingo.stepsdefinitions")
public class BingoTest {
}
