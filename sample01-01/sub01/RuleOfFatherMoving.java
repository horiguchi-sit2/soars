package sample01.sub01;

import jp.soars.core.*;

import java.util.Map;

public final class RuleOfFatherMoving extends TAgentRule {

    private String debug_info = "";

    /**
     * コンストラクタ
     * @param name ルール名
     * @param owner このルールをもつ役割
     */
    public RuleOfFatherMoving(String name, TRole owner) {
        // 親クラスのコンストラクタを呼び出す．
        super(name, owner);
    }

    /**
     * ルールを実行する．
     * @param currentTime 現在時刻
     * @param currentStage 現在ステージ
     * @param spotManager スポット管理
     * @param agentManager エージェント管理
     * @param globalSharedVariables グローバル共有変数集合
     */
    @Override
    public void doIt(TTime currentTime, Enum<?> currentStage, TSpotManager spotManager, TAgentManager agentManager, Map<String, Object> globalSharedVariables) {
        debug_info = "";

        RoleOfFather fatherRole = (RoleOfFather) getOwnerRole();
        if(isAt(fatherRole.getMyHome())){ // 自宅にいたら
            moveTo(fatherRole.getMyCompany()); // 会社に移動する．
            debug_info += "自宅から会社に移動しました．";
        } else if(isAt(fatherRole.getMyCompany())) { // 会社にいたら
            moveTo(fatherRole.getMyHome()); // 自宅に移動する．
            debug_info += "会社から自宅に移動しました．";
        } else { // どちらにもいなかったら
            debug_info += "自宅でも会社でもないので，何もしません．";
        }
    }

    /**
     * ルールログで表示するデバッグ情報．
     * @return デバッグ情報
     */
    @Override
    public final String debugInfo() {
        // デバッグ情報を出力する．
        return debug_info;
    }
}