package template;

import jp.soars.core.TAgentManager;
import jp.soars.core.TRuleExecutor;
import jp.soars.core.TSOARSBuilder;
import jp.soars.core.TSpotManager;
import jp.soars.utils.random.ICRandom;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * メインクラス
 * @author Author
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // *************************************************************************************************************
        // TSOARSBuilderの必須設定項目．
        //   - simulationStart: シミュレーション開始時刻
        //   - simulationEnd: シミュレーション終了時刻
        //   - tick: 1ステップの時間間隔
        //   - stages: 使用するステージリスト
        //   - agentTypes: 使用するエージェントタイプ集合
        //   - spotTypes: 使用するスポットタイプ集合
        // ステージリストのみ定義するステージ順に読み込ませてください
        // *************************************************************************************************************

        // 時刻設定は、構築するモデルに合わせて変更してください
        String simulationStart = "0/00:00:00"; // シミュレーション開始時刻
        String simulationEnd = "7/00:00:00"; // シミュレーション終了時刻
        String tick = "1:00:00"; // １ステップの時間間隔
        List<Enum<?>> stages = List.of(Stages.sample_stage); // ステージリスト
        Set<Enum<?>> spotTypes = new HashSet<>(); // 全スポットタイプ
        Set<Enum<?>> agentTypes = new HashSet<>(); // 全エージェントタイプ
        Collections.addAll(spotTypes, TypeOfSpot.values()); // TypeOfSpot に登録されているスポットタイプをすべて追加
        Collections.addAll(agentTypes, TypeOfAgent.values()); // TypeOfAgent に登録されているエージェントタイプをすべて追加
        TSOARSBuilder builder = new TSOARSBuilder(simulationStart, simulationEnd, tick, stages, agentTypes, spotTypes); // ビルダー作成

        // *************************************************************************************************************
        // TSOARSBuilderの任意設定項目．
        // 乱数のシードやログの出力フォルダの設定を環境に合わせて変更して下さい
        // *************************************************************************************************************

        // マスター乱数発生器のシード値設定
        long seed = 0L; // シード値（シード値を変えることでシミュレーション結果が変わります）
        builder.setRandomSeed(seed); // シード値設定

        // ログ出力設定
        String pathOfLogDir = "logs"; // ログディレクトリ(自分の環境に合わせて変更してください)
        builder.setRuleLoggingEnabled(pathOfLogDir + File.separator + "rule_log.csv") // ルールログ出力設定
                .setRuntimeLoggingEnabled(pathOfLogDir + File.separator + "runtime_log.csv"); // ランタイムログ出力設定

        // *************************************************************************************************************
        // TSOARSBuilderでシミュレーションに必要なインスタンスの作成と取得．
        // （ここは基本的に変更の必要はありません）
        // *************************************************************************************************************

        builder.build(); // インスタンスのビルド
        TRuleExecutor ruleExecutor = builder.getRuleExecutor(); // ルール実行器
        TAgentManager agentManager = builder.getAgentManager(); // エージェント管理
        TSpotManager spotManager = builder.getSpotManager(); // スポット管理
        ICRandom random = builder.getRandom(); // マスター乱数発生器
        Map<String, Object> globalSharedVariableSet = builder.getGlobalSharedVariableSet(); // グローバル共有変数集合

        // *************************************************************************************************************
        // スポットとエージェントの作成領域
        // *************************************************************************************************************


        // *************************************************************************************************************
        // ログ用の設定領域（PrintWriter）
        //   - 各時刻でエージェントの現在位置のログをとる (スポットログ, spot_log.csv)
        // *************************************************************************************************************

        // *************************************************************************************************************
        // シミュレーションのメインループ
        // *************************************************************************************************************

        while (ruleExecutor.executeStep()) { // 1ステップ分のルールを実行
            // 標準出力に現在時刻を表示する
            System.out.println(ruleExecutor.getCurrentTime());
        }

        // *************************************************************************************************************
        // シミュレーションの終了処理
        // *************************************************************************************************************

        ruleExecutor.shutdown(); // ルール実行器を終了する
    }
}
