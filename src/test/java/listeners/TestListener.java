package listeners;

import helpers.AllureHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("Тест стартовал: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Тест прошёл успешно: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error("Тест упал: {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.warn("Тест пропущен: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info("Начало тестового набора: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("Завершение тестового набора: {}", context.getName());
    }
}
