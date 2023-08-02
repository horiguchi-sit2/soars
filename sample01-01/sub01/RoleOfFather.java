package sample01.sub01;

import jp.soars.core.TAgent;
import jp.soars.core.TRole;
import jp.soars.core.TSpot;

public final class RoleOfFather extends TRole {

    /** 父親の通勤ルール名 */
    private static final String RULE_NAME_OF_FATHER_GOING_OFFICE = "goingOfficeRule";

    /** 父親の帰宅ルール名 */
    private static final String RULE_NAME_OF_FATHER_GOING_HOME = "goingHomeRule";

    /** 自宅 */
    private TSpot myHome;

    /** 会社 */
    private TSpot myCompany;

    /**
     * コンストラクタ
     * @param owner この役割を持つエージェント
     */
    public RoleOfFather(TAgent owner, TSpot home, TSpot company) {
        // 親クラスのコンストラクタを呼び出す．
        // 以下の2つの引数は省略可能で，その場合デフォルト値で設定される．
        // 第3引数 : この役割が持つルール数 (デフォルト値 10)
        // 第4引数 : この役割が持つ子役割数 (デフォルト値 5)
        super(TypeOfRole.Father, owner);
        myHome = home;
        myCompany = company;

        // ルールの定義
        new RuleOfFatherMoving(RULE_NAME_OF_FATHER_GOING_OFFICE, this).setTimeAndStage(9, 0, 0, Stages.agent_moving);
        new RuleOfFatherMoving(RULE_NAME_OF_FATHER_GOING_HOME, this).setTimeAndStage(17, 0, 0, Stages.agent_moving);
    }

    /**
     * 自宅を返す．
     * @return 自宅
     */
    public TSpot getMyHome() { return myHome;}

    /**
     * 会社を返す．
     * @return 会社
     */
    public TSpot getMyCompany() { return myCompany;}
}
