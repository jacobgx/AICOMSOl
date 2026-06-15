/*
 * ladder_frame_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:00 by COMSOL 6.3.0.290. */
public class ladder_frame_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Beams_and_Shells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "ladder_frame_geom.step");
    model.component("comp1").geom("geom1").feature("imp1").set("removeredundant", true);
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("dm1", "DistanceMeasurement");
    model.component("comp1").geom("geom1").feature("dm1").selection("ent1").set("imp1.id5", 44);
    model.component("comp1").geom("geom1").feature("dm1").selection("ent2").set("imp1.id5", 43);
    model.component("comp1").geom("geom1").feature("dm1").set("parname", "th1");
    model.component("comp1").geom("geom1").run("dm1");
    model.component("comp1").geom("geom1").create("dm2", "DistanceMeasurement");
    model.component("comp1").geom("geom1").feature("dm2").selection("ent1").set("imp1.id101", 65);
    model.component("comp1").geom("geom1").feature("dm2").selection("ent2").set("imp1.id101", 67);
    model.component("comp1").geom("geom1").feature("dm2").set("parname", "th2");
    model.component("comp1").geom("geom1").run("dm2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("imp1.id5", 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 25, 28, 29, 32, 33, 36, 37, 40, 45, 46, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 65, 66, 71, 72, 83, 84, 85, 92, 93, 94);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("imp1.id8", 3, 7, 8, 11, 12, 15, 17, 21, 24, 26, 28, 31, 32, 35, 36, 39, 40, 43, 47, 48, 51, 52, 53, 56, 58, 61, 63, 65, 76, 77, 78, 80, 81, 84, 85, 86, 93, 94, 95, 99, 100, 101, 102);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("imp1.id95", 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 29, 30, 31, 32, 41, 42, 43, 44, 48, 51, 62, 63, 64, 65, 67, 68, 69, 70, 71, 72, 76, 79, 87, 90, 91, 94, 95, 98);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("imp1.id92", 2, 4, 6, 8, 22, 23, 24, 25, 27, 31, 32, 33, 34, 35, 44, 45, 46, 47, 52, 55, 57, 58, 59, 60, 61, 63, 67, 70, 74, 75, 78, 79, 82, 83, 86, 88, 91, 92, 95, 96, 99, 101, 102);
    model.component("comp1").geom("geom1").defeaturing("Fillets").selection("input")
         .set("imp1.id101", "imp1.id104", "imp1.id11", "imp1.id110", "imp1.id113", "imp1.id116", "imp1.id119", "imp1.id122", "imp1.id14", "imp1.id17", "imp1.id20", "imp1.id23", "imp1.id26", "imp1.id29", "imp1.id32", "imp1.id35", "imp1.id38", "imp1.id41", "imp1.id44", "imp1.id47", "imp1.id53", "imp1.id56", "imp1.id59", "imp1.id62", "imp1.id65", "imp1.id68", "imp1.id71", "imp1.id74", "imp1.id77", "imp1.id80", "imp1.id83", "imp1.id86", "imp1.id89");
    model.component("comp1").geom("geom1").defeaturing("Fillets").set("entsize", "7[mm]");
    model.component("comp1").geom("geom1").defeaturing("Fillets").find();
    model.component("comp1").geom("geom1").defeaturing("Fillets").deleteAll("dfi1");
    model.component("comp1").geom("geom1").create("mid1", "Midsurface");
    model.component("comp1").geom("geom1").feature("mid1").selection("input").set("dfi1");
    model.component("comp1").geom("geom1").run("mid1");
    model.component("comp1").geom("geom1").create("extract1", "Extract");
    model.component("comp1").geom("geom1").feature("extract1").selection("input").named("sel1");
    model.component("comp1").geom("geom1").feature("extract1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").run("extract1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("extract1", "mid1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").run("rmd1");

    model.title("\u68af\u67b6\u51e0\u4f55");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e\u5bfc\u5165\u7684\u5b9e\u4f53 CAD \u6587\u4ef6\u751f\u6210\u58f3\u6a21\u578b\uff0c\u4ee5\u4fbf\u4f7f\u7528\u201c\u58f3\u201d\u7269\u7406\u573a\u63a5\u53e3\u8fdb\u884c\u5206\u6790\u3002");

    model.label("ladder_frame_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
