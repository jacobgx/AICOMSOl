/*
 * loudspeaker_driver_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_driver_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "180[mm]");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "15[mm]", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "8[mm]");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"74[mm]", "0"});
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "1.5[mm]", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c2", 2, 3, 4);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"120[mm]", "1[mm]"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"80.5[mm]", "-1[mm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"42[mm]", "35[mm]"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"6[mm]", "-87[mm]"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"35.5[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"15.5[mm]", "-80[mm]"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"1.2[mm]", "8[mm]"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"17.8[mm]", "-60[mm]"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"26[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"25[mm]", "-80[mm]"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "48[mm] 36[mm] 36[mm] 48[mm]");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "-82[mm] -87[mm] -87[mm] -87[mm]");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("pol1", "r3", "r4");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"0.2[mm]", "25[mm]"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"18.2[mm]", "-64[mm]"});
    model.component("comp1").geom("geom1").feature("r6").setIndex("layer", "1.26[mm]", 0);
    model.component("comp1").geom("geom1").feature("r6").setIndex("layer", "3.84[mm]", 1);
    model.component("comp1").geom("geom1").feature("r6").setIndex("layer", "0.4[mm]", 2);
    model.component("comp1").geom("geom1").feature("r6").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r6").set("layertop", true);
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"0.6[mm]", "9.4[mm]"});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new String[]{"18.2[mm]", "-60.7[mm]"});
    model.component("comp1").geom("geom1").run("r7");
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
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"-18.2[mm]", "0"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"-18.2[mm]", "0"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"-18.2[mm]", "-40.26[mm]"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"-18.2[mm]", "-39[mm]"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("dif2", 5, 6, 7, 8);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.2[mm]");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new String[]{"1.2[mm]", "7.6[mm]"});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"17.8[mm]", "-59.8[mm]"});
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("loudspeaker_driver_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
