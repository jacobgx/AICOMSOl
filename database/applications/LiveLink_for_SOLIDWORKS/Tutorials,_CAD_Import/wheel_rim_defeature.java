/*
 * wheel_rim_defeature.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:48 by COMSOL 6.3.0.290. */
public class wheel_rim_defeature {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_SOLIDWORKS\\Tutorials,_CAD_Import");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "wheel_rim.x_b");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").defeaturing("SmallFaces").set("entsize", "1e-4");
    model.component("comp1").geom("geom1").defeaturing("SmallFaces").find();
    model.component("comp1").geom("geom1").defeaturing("SmallFaces").deleteAll("dsf1");
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").set("entsize", "4e-4");
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").find();
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").deleteAll("dsl1");
    model.component("comp1").geom("geom1").defeaturing("ShortEdges").set("entsize", "4e-4");
    model.component("comp1").geom("geom1").defeaturing("ShortEdges").find();
    model.component("comp1").geom("geom1").defeaturing("ShortEdges").set("entsize", "9e-4");
    model.component("comp1").geom("geom1").defeaturing("ShortEdges").find();
    model.component("comp1").geom("geom1").defeaturing("ShortEdges").find();
    model.component("comp1").geom("geom1").defeaturing("ShortEdges").deleteAll("dse1");
    model.component("comp1").geom("geom1").defeaturing("Fillets").set("entsize", "2[mm]");
    model.component("comp1").geom("geom1").defeaturing("Fillets").find();
    model.component("comp1").geom("geom1").defeaturing("Fillets").detail().clear("dse1");
    model.component("comp1").geom("geom1").defeaturing("Fillets").detail()
         .set("dse1", 240, 241, 257, 263, 274, 275, 279, 287, 316, 333, 335, 339, 352, 359, 360, 367, 368, 373, 379, 401, 427, 436, 464, 465, 466, 473, 479, 489, 493, 507, 515, 516, 521, 528, 530, 553, 588, 592, 610, 615);
    model.component("comp1").geom("geom1").defeaturing("Fillets").delete("dfi1");

    model.component("comp1").mesh("mesh1").run();

    model.title("\u901a\u8fc7\u7279\u5f81\u53bb\u9664\u5de5\u5177\u79fb\u9664\u5c0f\u51e0\u4f55\u5b9e\u4f53");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u4ece\u5bfc\u5165\u7684 CAD \u51e0\u4f55\u4e2d\u79fb\u9664\u5c0f\u7ec6\u8282\u7684\u66ff\u4ee3\u5de5\u4f5c\u6d41\u7a0b\u3002\u5c3d\u7ba1\u201c\u4fee\u590d\u201d\u64cd\u4f5c\u53ef\u79fb\u9664\u6307\u5b9a\u5bb9\u5dee\u8303\u56f4\u5185\u7684\u6240\u6709\u7ec6\u8282\uff0c\u4f46\u672c\u6559\u7a0b\u4e2d\u4f7f\u7528\u7684\u7279\u5f81\u53bb\u9664\u5de5\u5177\u5141\u8bb8\u60a8\u9009\u62e9\u5e94\u79fb\u9664\u7684\u5b9e\u4f53\u3002");

    model.mesh().clearMeshes();

    model.label("wheel_rim_defeature.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
