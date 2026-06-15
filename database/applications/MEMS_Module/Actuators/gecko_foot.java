/*
 * gecko_foot.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:54 by COMSOL 6.3.0.290. */
public class gecko_foot {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("Fc", "0.4[uN]");
    model.param().descr("Fc", "\u63a5\u89e6\u529b");
    model.param().set("Ff", "0.2[uN]");
    model.param().descr("Ff", "\u6469\u64e6\u529b");
    model.param().set("theta", "pi/3");
    model.param().descr("theta", "\u63a5\u89e6\u89d2");
    model.param().set("Dm", "75[um]");
    model.param().descr("Dm", "\u5fae\u7c73\u7ea7\u521a\u6bdb\u957f\u5ea6");
    model.param().set("Hm", "4.33[um]");
    model.param().descr("Hm", "\u5fae\u7c73\u7ea7\u521a\u6bdb\u5bbd\u5ea6");
    model.param().set("Wm", "4.53[um]");
    model.param().descr("Wm", "\u5fae\u7c73\u7ea7\u521a\u6bdb\u9ad8\u5ea6");
    model.param().set("Dn", "3[um]");
    model.param().descr("Dn", "\u7eb3\u7c73\u7ea7\u521a\u6bdb\u957f\u5ea6");
    model.param().set("Hn", "0.17[um]");
    model.param().descr("Hn", "\u7eb3\u7c73\u7ea7\u521a\u6bdb\u5bbd\u5ea6");
    model.param().set("Wn", "0.18[um]");
    model.param().descr("Wn", "\u7eb3\u7c73\u7ea7\u521a\u6bdb\u9ad8\u5ea6");
    model.param().set("Area", "Wn*Hn");
    model.param().descr("Area", "\u94f2\u72b6\u5319\u7a81\u7684\u6a2a\u622a\u9762\u79ef");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Wn", "Dn", "Hn"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "-Dn", "0"});
    model.component("comp1").geom("geom1").feature("blk1").label("\u7eb3\u7c73\u7ea7\u521a\u6bdb");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("blk1", 3);
    model.component("comp1").geom("geom1").feature("sel1").label("\u7eb3\u7c73\u7ea7\u521a\u6bdb\u672b\u7aef");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("blk1", 6);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").feature("sel2").label("\u7eb3\u7c73\u7ea7\u521a\u6bdb\u6839\u90e8");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{13, 1, 13});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"(Wm-Wn)/12", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("displ", "(Hm-Hn)/12", 2);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"Wm", "Dm", "Hm"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").feature("blk2").label("\u5fae\u7c73\u7ea7\u521a\u6bdb");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("blk2", 3);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5fae\u7c73\u7ea7\u521a\u6bdb\u672b\u7aef");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair().create("p1", "Identity");
    model.component("comp1").pair("p1").source().named("geom1_sel3");
    model.component("comp1").pair("p1").destination().named("geom1_sel2");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"0", "pi/2-theta", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"2e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1200"});

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("max_v_Mises", "maxop1(solid.mises)");
    model.component("comp1").variable("var1").descr("max_v_Mises", "\u6700\u5927 von Mises \u5e94\u529b");
    model.component("comp1").variable("var1").set("max_disp", "maxop1(solid.disp)");
    model.component("comp1").variable("var1").descr("max_disp", "\u6700\u5927\u603b\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("max_ep1", "maxop1(solid.ep1)");
    model.component("comp1").variable("var1").descr("max_ep1", "\u6700\u5927\u7b2c\u4e00\u4e3b\u5e94\u53d8");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(83);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "-Fc/Area", "Ff/Area"});
    model.component("comp1").physics("solid").create("cont1", "Continuity", 2);
    model.component("comp1").physics("solid").feature("cont1").set("pairs", new String[]{"p1"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().all();
    model.component("comp1").mesh("mesh1").feature("swe1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(83);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(162, 163, 164, 168);
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run("swe2");

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
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", 1);
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"max_v_Mises"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6700\u5927 von Mises \u5e94\u529b"});
    model.result().numerical("gev1").set("unit", new String[]{"N/m^2"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("expr", new String[]{"max_disp"});
    model.result().numerical("gev2").set("descr", new String[]{"\u6700\u5927\u603b\u4f4d\u79fb"});
    model.result().numerical("gev2").set("unit", new String[]{"\u00b5m"});
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("expr", new String[]{"max_ep1"});
    model.result().numerical("gev3").set("descr", new String[]{"\u6700\u5927\u7b2c\u4e00\u4e3b\u5e94\u53d8"});
    model.result().numerical("gev3").set("unit", new String[]{"1"});
    model.result().numerical("gev3").set("table", "tbl1");
    model.result().numerical("gev3").appendResult();

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl2");
    model.result().numerical("gev1").setResult();
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").appendResult();
    model.result().numerical("gev3").set("table", "tbl2");
    model.result().numerical("gev3").appendResult();
    model.result("pg1").run();

    model.title("\u58c1\u864e\u8db3");

    model
         .description("\u8fd9\u4e2a\u6a21\u578b\u7814\u7a76\u7eb3\u7c73/\u5fae\u7c73\u7ea7\u5408\u6210\u58c1\u864e\u7ed2\u6bdb\uff0c\u4ee5\u4e0d\u540c\u5c3a\u5ea6\u7684\u60ac\u81c2\u6881\u6765\u63cf\u8ff0\u8fd9\u4e9b\u7ed2\u6bdb\u3002\u5206\u6790\u5f97\u51fa\u63a5\u89e6\u529b\u548c\u6469\u64e6\u529b\u4ea7\u751f\u4e86\u58c1\u864e\u8db3\u4e0a\u7684\u5e94\u529b\u548c\u6320\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("gecko_foot.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
