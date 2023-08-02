package template;
 
 import jp.soars.core.TAgent;
 import jp.soars.core.TRole;
 
 public final class RoleOfFather extends TRole {
     /**
      * コンストラクタ
      * @param owner この役割を持つエージェント
      */
     public RoleOfFather(TAgent owner) {
         // 親クラスのコンストラクタを呼び出す．
         // 以下の2つの引数は省略可能で，その場合デフォルト値で設定される．
         // 第3引数 : この役割が持つルール数 (デフォルト値 10)
         // 第4引数 : この役割が持つ子役割数 (デフォルト値 5)
         super(TypeOfRole.Father, owner);
     }
 }
