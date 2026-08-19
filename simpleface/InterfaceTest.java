package simpleface.simpleface;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.text.Text;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * These tests intentionally use reflection because the current Interface
 * class keeps most game state private.
 */
class InterfaceTest {

    private Interface game;

    @BeforeAll
    static void startJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        try {
            Platform.startup(latch::countDown);
        } catch (IllegalStateException alreadyStarted) {
            latch.countDown();
        }

        assertTrue(
            latch.await(10, TimeUnit.SECONDS),
            "JavaFX toolkit did not start"
        );
    }

    @BeforeEach
    void setUp() {
        game = new Interface();

        // Several game-mode methods use gameScene.setFill(...).
        // The real Scene is normally created in start(), so provide one
        // for unit tests without launching the whole application.
        setField(game, "gameScene", new Scene(new Group()));
    }

    // ============================================================
    // SCORE TESTS
    // ============================================================

    @Test
    void initialScoreShouldBeZero() {
        assertEquals(0, getIntField(game, "Score"));
    }

    @Test
    void scoreCounterShouldIncreaseScoreByTen() {
        game.scoreCounter();

        assertEquals(10, getIntField(game, "Score"));
    }

    @Test
    void scoreCounterCalledTwiceShouldIncreaseScoreByTwenty() {
        game.scoreCounter();
        game.scoreCounter();

        assertEquals(20, getIntField(game, "Score"));
    }

    @Test
    void scoreCounterShouldUpdateScoreText() {
        game.scoreCounter();

        assertEquals("Score: 10", getTextField(game, "score"));
    }

    @Test
    void scoreCounterShouldUpdateHighScore() {
        game.scoreCounter();

        assertEquals(10, getIntField(game, "highScore"));
        assertEquals("High Score: 10", getTextField(game, "highscore"));
    }

    // ============================================================
    // COIN TESTS
    // ============================================================

    @Test
    void initialCoinsShouldBeZero() {
        assertEquals(0, getIntField(game, "TotalCoins"));
    }

    @Test
    void coinCounterShouldAddCoins() {
        game.coinCounter(100);

        assertEquals(100, getIntField(game, "TotalCoins"));
    }

    @Test
    void coinCounterShouldAccumulateCoins() {
        game.coinCounter(100);
        game.coinCounter(250);

        assertEquals(350, getIntField(game, "TotalCoins"));
    }

    @Test
    void coinCounterShouldUpdateCoinText() {
        game.coinCounter(500);

        assertEquals("X 500", getTextField(game, "coinText"));
    }

    @Test
    void coinCounterShouldAllowZeroCoins() {
        game.coinCounter(0);

        assertEquals(0, getIntField(game, "TotalCoins"));
        assertEquals("X 0", getTextField(game, "coinText"));
    }

    @Test
    void coinCounterShouldAllowNegativeAdjustment() {
        game.coinCounter(500);
        game.coinCounter(-100);

        assertEquals(400, getIntField(game, "TotalCoins"));
    }

    // ============================================================
    // BULLET UPGRADE TESTS
    // ============================================================

    @Test
    void initialBulletUpgradeLevelShouldBeZero() {
        assertEquals(0, getIntField(game, "bulletUpgradeLevel"));
    }

    @Test
    void upgradeBulletsShouldIncreaseUpgradeLevel() {
        setIntField(game, "TotalCoins", 3000);

        game.upgradeBullets();

        assertEquals(1, getIntField(game, "bulletUpgradeLevel"));
    }

    @Test
    void firstBulletUpgradeShouldCost3000Coins() {
        setIntField(game, "TotalCoins", 3000);

        game.upgradeBullets();

        assertEquals(0, getIntField(game, "TotalCoins"));
    }

    @Test
    void firstBulletUpgradeShouldIncreasePrice() {
        setIntField(game, "TotalCoins", 3000);

        game.upgradeBullets();

        assertEquals(4000, getIntField(game, "bulletUpgradePrice"));
    }

    @Test
    void secondBulletUpgradeShouldIncreaseLevelAgain() {
        setIntField(game, "TotalCoins", 10000);

        game.upgradeBullets();
        game.upgradeBullets();

        assertEquals(2, getIntField(game, "bulletUpgradeLevel"));
    }

    @Test
    void secondBulletUpgradeShouldUseUpdatedPrice() {
        setIntField(game, "TotalCoins", 10000);

        game.upgradeBullets();
        game.upgradeBullets();

        // 3000 + 4000 = 7000 spent
        assertEquals(3000, getIntField(game, "TotalCoins"));
    }

    // ============================================================
    // ENEMY SPAWN TESTS
    // ============================================================

    @Test
    void basicEnemyShouldSpawnAtItsThreshold() {
        int threshold = getIntField(game, "basicEnemieScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(9));
    }

    @Test
    void basicEnemyShouldNotBeConfusedWithInvalidType() {
        setIntField(game, "Score", 1000000);

        assertFalse(game.enemySpawnType(999));
    }

    @Test
    void coinEnemyShouldNotSpawnBeforeThreshold() {
        int threshold = getIntField(game, "CoinsSpawnScore");

        if (threshold > 0) {
            setIntField(game, "Score", threshold - 1);
            assertFalse(game.enemySpawnType(4));
        } else {
            assertTrue(game.enemySpawnType(4));
        }
    }

    @Test
    void coinEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "CoinsSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(4));
    }

    @Test
    void missileEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "missleScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(5));
    }

    @Test
    void skeletonEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "mediumSkeletonScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(1));
    }

    @Test
    void ladyEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "largeLadyScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(2));
    }

    @Test
    void ratEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "FastRatScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(3));
    }

    @Test
    void golemEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "GolemSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(6));
    }

    @Test
    void sourcerEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "SourcerSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(7));
    }

    @Test
    void deflectorEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "deflectorSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(8));
    }

    @Test
    void heartEnemyShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "heartSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(10));
    }

    @Test
    void poisonSourcerShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "poisonSourcerSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(11));
    }

    @Test
    void bossPoisonSourcerShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "bossPoisonSourcerSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(12));
    }

    @Test
    void bossSourcerShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "bossSourcerSpanwScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(13));
    }

    @Test
    void bossNinjaShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "bossNinjaSpawnScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(14));
    }

    @Test
    void flyingBombShouldSpawnAtThreshold() {
        int threshold = getIntField(game, "flyingBombScore");
        setIntField(game, "Score", threshold);

        assertTrue(game.enemySpawnType(15));
    }

    @Test
    void invalidNegativeEnemyTypeShouldNeverSpawn() {
        setIntField(game, "Score", 1000000);

        assertFalse(game.enemySpawnType(-1));
    }

    // ============================================================
    // SCORE CHALLENGE TESTS
    // ============================================================

    @Test
    void scoreChallengeShouldUseHighScorePlus5000() {
        setIntField(game, "highScore", 0);

        game.scoreChallange();

        assertEquals(
            "Good job soldier, but can you get to 5000?",
            getTextField(game, "ScoreChallangePrompt")
        );
    }

    @Test
    void scoreChallengeShouldRoundUpToNearestThousand() {
        setIntField(game, "highScore", 123);

        game.scoreChallange();

        assertEquals(
            "Good job soldier, but can you get to 6000?",
            getTextField(game, "ScoreChallangePrompt")
        );
    }

    @Test
    void scoreChallengePromptShouldBecomeVisible() {
        setIntField(game, "highScore", 5000);

        game.scoreChallange();

        Node prompt = getField(game, "ScoreChallangePrompt", Node.class);

        assertEquals(1.0, prompt.getOpacity(), 0.001);
    }

    // ============================================================
    // DIFFICULTY MODE TESTS
    // ============================================================

    @Test
    void easyModeShouldSetDifficultyTypeToZero() {
        game.easyMode();

        assertEquals(0, getIntField(game, "difficutlyType"));
    }

    @Test
    void easyModeShouldResetScore() {
        setIntField(game, "Score", 500);

        game.easyMode();

        assertEquals(0, getIntField(game, "Score"));
    }

    @Test
    void easyModeShouldGiveStartingCoins() {
        game.easyMode();

        assertEquals(1000, getIntField(game, "TotalCoins"));
    }

    @Test
    void normalModeShouldSetDifficultyTypeToOne() {
        game.normalMode();

        assertEquals(1, getIntField(game, "difficutlyType"));
    }

    @Test
    void normalModeShouldResetScore() {
        setIntField(game, "Score", 500);

        game.normalMode();

        assertEquals(0, getIntField(game, "Score"));
    }

    @Test
    void normalModeShouldGive300Coins() {
        game.normalMode();

        assertEquals(300, getIntField(game, "TotalCoins"));
    }

    @Test
    void hardModeShouldSetDifficultyTypeToTwo() {
        game.hardMode();

        assertEquals(2, getIntField(game, "difficutlyType"));
    }

    @Test
    void hardModeShouldStartWith400Score() {
        game.hardMode();

        assertEquals(400, getIntField(game, "Score"));
    }

    @Test
    void hardModeShouldStartWithZeroCoins() {
        game.hardMode();

        assertEquals(0, getIntField(game, "TotalCoins"));
    }

    @Test
    void ultraHardModeShouldSetDifficultyTypeToThree() {
        game.ultraHardMode();

        assertEquals(3, getIntField(game, "difficutlyType"));
    }

    @Test
    void ultraHardModeShouldSetScoreTo1000() {
        game.ultraHardMode();

        assertEquals(1000, getIntField(game, "Score"));
    }

    // ============================================================
    // GAME STATE TESTS
    // ============================================================

    @Test
    void newGameShouldInitiallyBePausedAccordingToCurrentImplementation() {
        // Interface initializes previousTime to -1.
        // Therefore isPaused() returns true until the timer is started.
        assertTrue(game.isPaused());
    }

    @Test
    void upgradeScreenShouldChangeGameState() {
        game.upgradeScreen();

        assertEquals(2, getIntField(game, "gameState"));
    }

    // ============================================================
    // BOSS HEALTH TEST
    // ============================================================

    @Test
    void setBossHealthToZeroShouldHideFullHealthBar() {
        game.setBossHeatlthToZero();

        Node fullHealthBar = getField(game, "FullhealthBarImg", Node.class);

        assertEquals(0.0, fullHealthBar.getOpacity(), 0.001);
    }

    @Test
    void setBossHealthToZeroShouldHideFirstHealthBar() {
        game.setBossHeatlthToZero();

        Node healthBar1 = getField(game, "healthBar1", Node.class);

        assertEquals(0.0, healthBar1.getOpacity(), 0.001);
    }

    // ============================================================
    // NUKE TEST - verifies behavior on an empty enemy list
    // ============================================================

    @Test
    void nukeShouldNotThrowWhenThereAreNoEnemies() {
        assertDoesNotThrow(() -> game.nuke());
    }

    // ============================================================
    // CLEANUP TEST - verifies behavior on empty groups
    // ============================================================

    @Test
    void cleanupShouldNotThrowWhenThereAreNoEnemiesOrBullets() {
        assertDoesNotThrow(() -> game.cleanup());
    }

    // ============================================================
    // REFLECTION HELPERS
    // ============================================================

    private static Object getField(Object object, String fieldName) {
        try {
            Field field = findField(object.getClass(), fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new AssertionError(
                "Could not read field '" + fieldName + "'",
                e
            );
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T getField(
            Object object,
            String fieldName,
            Class<T> type) {

        Object value = getField(object, fieldName);

        assertNotNull(
            value,
            "Field '" + fieldName + "' should not be null"
        );

        return (T) value;
    }

    private static int getIntField(Object object, String fieldName) {
        try {
            Field field = findField(object.getClass(), fieldName);
            field.setAccessible(true);
            return field.getInt(object);
        } catch (Exception e) {
            throw new AssertionError(
                "Could not read integer field '" + fieldName + "'",
                e
            );
        }
    }

    private static String getTextField(Object object, String fieldName) {
        Text text = getField(object, fieldName, Text.class);
        return text.getText();
    }

    private static void setIntField(
            Object object,
            String fieldName,
            int value) {

        try {
            Field field = findField(object.getClass(), fieldName);
            field.setAccessible(true);
            field.setInt(object, value);
        } catch (Exception e) {
            throw new AssertionError(
                "Could not set integer field '" + fieldName + "'",
                e
            );
        }
    }

    private static void setField(
            Object object,
            String fieldName,
            Object value) {

        try {
            Field field = findField(object.getClass(), fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new AssertionError(
                "Could not set field '" + fieldName + "'",
                e
            );
        }
    }

    private static Field findField(
            Class<?> clazz,
            String fieldName) throws NoSuchFieldException {

        Class<?> current = clazz;

        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }

        throw new NoSuchFieldException(fieldName);
    }
}
