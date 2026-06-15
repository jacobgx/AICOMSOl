/*
 * thick_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:26 by COMSOL 6.3.0.290. */
public class thick_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new double[]{3.25, 2.75});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e2", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e2").set("semiaxes", new int[]{2, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e2").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e3", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e3")
         .set("semiaxes", new double[]{2.416, 1.583});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e3").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("e1", "e3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("e2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1.783, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 2.3, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1.165, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0.812, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 2.833, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 1.348, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 1.783, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 0.453, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("tool")
         .set("pol1", "pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("par1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.3, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.6, 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 4, 8, 11, 40, 41, 49, 50);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(15, 16, 31, 32, 51, 52);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").selection().set(20, 41, 72);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(7, 14, 23, 30, 39, 48);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-1e6"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(7, 14, 23, 30, 39, 48);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(24);
    model.result().numerical("pev1").set("expr", new String[]{"solid.sGpyy"});
    model.result().numerical("pev1").set("descr", new String[]{"\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf"});
    model.result().numerical("pev1").set("unit", new String[]{"MPa"});
    model.result().numerical("pev1").setIndex("descr", "\u5e94\u529b\u5f20\u91cf\uff0cy \u5206\u91cf (COMSOL)", 0);
    model.result().numerical("pev1").setIndex("expr", "-5.38[MPa]", 1);
    model.result().numerical("pev1").setIndex("descr", "\u5e94\u529b\u5f20\u91cf\uff0cy \u5206\u91cf (NAFEMS)", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.sGpyy");
    model.result("pg1").feature("vol1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf");
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").run();

    model.title("\u539a\u677f\u5e94\u529b\u5206\u6790");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u5206\u6790\u539a\u677f\u4e0a\u8868\u9762\u53d7\u5230\u7684\u538b\u529b\u3002\n\u672c\u4f8b\u5c06\u5f97\u5230\u7684\u89e3\u4e0e NAFEMS \u57fa\u51c6\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("thick_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
