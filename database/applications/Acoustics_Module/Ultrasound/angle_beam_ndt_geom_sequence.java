/*
 * angle_beam_ndt_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class angle_beam_ndt_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("alpha", "28[deg]", "\u6362\u80fd\u5668\u4f4d\u7f6e\u89d2\u5ea6");
    model.param().set("W", "20[mm]", "\u6954\u5757\u5bbd\u5ea6");
    model.param().set("H", "10[mm]", "\u6954\u5757\u9ad8\u5ea6");
    model.param().set("L", "12[mm]", "\u6954\u5757\u4fa7\u8fb9\u957f\u5ea6");
    model.param().set("D", "9[mm]", "\u6362\u80fd\u5668\u76f4\u5f84");
    model.param().set("H_pzt", "1.55[mm]", "\u538b\u7535\u6676\u4f53\u9ad8\u5ea6");
    model.param().set("H_match", "0.56[mm]", "\u5339\u914d\u5c42\u9ad8\u5ea6");
    model.param().set("W_ts", "100[mm]", "\u6d4b\u8bd5\u6837\u672c\u5bbd\u5ea6");
    model.param().set("H_ts", "15[mm]", "\u6d4b\u8bd5\u6837\u672c\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "H-L*sin(alpha)", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "L*cos(alpha)", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "H", 1);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pt1", 1);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("pt2", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ls1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 2);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("pt3", "Point");
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", "(L-D)/2*cos(alpha)", 0);
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", "H-(L+D)/2*sin(alpha)", 1);
    model.component("comp1").geom("geom1").run("pt3");
    model.component("comp1").geom("geom1").create("pt4", "Point");
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", "(L+D)/2*cos(alpha)", 0);
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", "H-(L-D)/2*sin(alpha)", 1);
    model.component("comp1").geom("geom1").run("pt4");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"D", "H_pzt+H_match"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"(L-D)/2*cos(alpha)", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "H-(L+D)/2*sin(alpha)", 1);
    model.component("comp1").geom("geom1").feature("r2").set("rot", "alpha");
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "H_match", 0);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"L", "3.5*H_pzt"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "H-L*sin(alpha)"});
    model.component("comp1").geom("geom1").feature("r3").set("rot", "alpha");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("r2");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("del1", 3);
    model.component("comp1").geom("geom1").feature("pare1").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare1").selection("vertexproj").set("pt3", 1);
    model.component("comp1").geom("geom1").feature("pare1").selection("vertexproj").set("pt4", 1);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"W_ts", "H_ts"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-W_ts/10", "-H_ts"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("layer", "H_ts/2", 0);
    model.component("comp1").geom("geom1").feature("r4").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r4").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r4").set("layerright", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.017, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.01, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.019, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.009, 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pol1", "r4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("angle_beam_ndt_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
