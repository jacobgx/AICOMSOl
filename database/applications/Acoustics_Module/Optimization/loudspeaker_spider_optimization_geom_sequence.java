/*
 * loudspeaker_spider_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_spider_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "8[mm]");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"74[mm]", "0"});
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "1.5[mm]", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"42[mm]", "35[mm]"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"6[mm]", "-87[mm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"35.5[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"15.5[mm]", "-80[mm]"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"1.2[mm]", "8[mm]"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"17.8[mm]", "-60[mm]"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"26[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"25[mm]", "-80[mm]"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "48[mm] 36[mm] 36[mm] 48[mm]");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "-82[mm] -87[mm] -87[mm] -87[mm]");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pol1", "r2", "r3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"0.2[mm]", "25[mm]"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"18.2[mm]", "-64[mm]"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "1.26[mm]", 0);
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "3.84[mm]", 1);
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "0.4[mm]", 2);
    model.component("comp1").geom("geom1").feature("r5").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r5").set("layertop", true);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"0.6[mm]", "9.4[mm]"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"18.2[mm]", "-60.7[mm]"});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("x", "18.4[mm] 23[mm] 26[mm] 26[mm] 32[mm] 32[mm] 38[mm] 38[mm] 44[mm] 44[mm] 50[mm] 50[mm] 56[mm] 56[mm] 59[mm] 66[mm] 66[mm] 59[mm] 56[mm] 56[mm] 50[mm] 50[mm] 44[mm] 44[mm] 38[mm] 38[mm] 32[mm] 32[mm] 26[mm] 26[mm] 23[mm] 18.4[mm]");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("y", "-44.1[mm] -44.1[mm] -42.1[mm] -42.1[mm] -46.1[mm] -46.1[mm] -42.1[mm] -42.1[mm] -46.1[mm] -46.1[mm]  -42.1[mm] -42.1[mm] -46.1[mm] -46.1[mm] -44.1[mm] -44.1[mm] -44.5[mm] -44.5[mm] -46.5[mm] -46.5[mm]  -42.5[mm] -42.5[mm] -46.5[mm] -46.5[mm]  -42.5[mm] -42.5[mm] -46.5[mm] -46.5[mm]  -42.5[mm] -42.5[mm] -44.5[mm] -44.5[mm]");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol3")
         .set("x", "18.4[mm] 66[mm] 66[mm] 67.5[mm] 67.5[mm] 18.4[mm] 18.4[mm] 18.4[mm]");
    model.component("comp1").geom("geom1").feature("pol3").set("y", "-39[mm] 0 0 0 0 -40.26[mm] -40.26[mm] -39[mm]");
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-18.2[mm]", 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "18.2[mm]", 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-39[mm]", 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-23.5[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-39[mm]", 1, 2);
    model.component("comp1").geom("geom1").feature("qb1").set("w", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"18.2[mm]", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"18.2[mm]", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"18.2[mm]", "-39[mm]"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"18.2[mm]", "-40.26[mm]"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "18.2[mm]", 0, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "-18.2[mm]", 0, 2);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "-40.26[mm]", 1, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "-24.26[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "-40.26[mm]", 1, 2);
    model.component("comp1").geom("geom1").feature("qb2").set("w", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").run("qb2");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"18.2[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new String[]{"0[mm]", "-40.26[mm]"});
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("qb1", "qb2", "r7");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 2, 3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 2, 4, 5, 7, 8, 9);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("dif1", 5, 6, 7, 8);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.2[mm]");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new String[]{"47.8[mm]", "0.4[mm]"});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"18.2[mm]", "-49.7[mm]"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u97f3\u5708");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 11);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u590d\u5408\u6750\u6599");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 1, 10, 14);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5e03\u819c");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 12, 13);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u97f3\u5708\u67b6");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 3, 4, 5, 6, 7, 8, 9);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u6ce1\u6cab");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 17, 18);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u5f62\u72b6\u4f18\u5316");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 12);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7ed3\u6784\u57df");
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"sel1", "sel2", "sel3", "sel4", "sel5"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u56fa\u5b9a\u8fb9\u754c 1");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 72, 75);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u56fa\u5b9a\u8fb9\u754c 2");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin", 71, 75);
    model.component("comp1").geom("geom1").run("sel8");

    model.title(null);

    model.description("");

    model.label("loudspeaker_spider_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
