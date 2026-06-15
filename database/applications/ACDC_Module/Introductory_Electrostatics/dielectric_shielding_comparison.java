/*
 * dielectric_shielding_comparison.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class dielectric_shielding_comparison {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electrostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");

    model.param().set("er_b", "20");
    model.param().descr("er_b", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u7535\u4ecb\u8d28");
    model.param().set("V0", "1[V]");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{8, 10});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0.1, -5});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{3.5, 1});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0.1, 2});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 2, 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.5);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", -5);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("copy1", "fil1", "r1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.95);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0.1, 0});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new double[]{-0.1, 0});
    model.component("comp1").geom("geom1").feature("c2").set("rot", 90);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "1[mm]", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(4, 5);
    model.component("comp1").selection("sel1").label("\u5168\u4fdd\u771f");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(42, 43);
    model.component("comp1").selection("sel2").label("\u4ecb\u7535\u5c4f\u853d");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(1, 4, 5, 6, 7, 9);
    model.component("comp1").selection("sel3").label("\u6a21\u578b\u57df");

    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 2);
    model.component("comp1").physics("es").feature("ccns1").selection().set(4, 5);
    model.component("comp1").physics("es").selection().named("sel3");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(22, 24, 44, 45);
    model.component("comp1").physics("es").create("gnd2", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd2").selection().set(4, 5, 34, 35);
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(29, 31, 46, 47);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "V0");
    model.component("comp1").physics("es").create("term2", "Terminal", 1);
    model.component("comp1").physics("es").feature("term2").selection().set(6, 7, 36, 37);
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", "V0");
    model.component("comp1").physics("es").create("des1", "DielectricShielding", 1);
    model.component("comp1").physics("es").feature("des1").selection().named("sel2");
    model.component("comp1").physics("es").feature("des1").set("ds", "1[mm]");
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("es").feature("symp1").selection()
         .set(9, 11, 13, 14, 16, 18, 19, 23, 25, 26, 27, 30);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"er_b"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"er_b"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a (es)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "es.normE");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg1").feature("surf1").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("number", 21);
    model.result("pg1").feature("con1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("con1").feature("col1").set("expr", "es.normE");
    model.result("pg1").feature("con1").feature("col1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").selection().set(6, 29, 36, 37, 46, 47);
    model.result("pg1").feature("str1").set("selnumber", 40);
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg1").feature("str1").feature("col1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "es.Q0_1/V0", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "es.Q0_2/V0", 0);
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result("pg1").run();

    model.title("\u4ecb\u7535\u5c4f\u853d\u5efa\u6a21\u65b9\u6cd5\u6bd4\u8f83");

    model
         .description("\u4ecb\u7535\u5c4f\u853d\u8fb9\u754c\u6761\u4ef6\u7528\u4e8e\u9759\u7535\u573a\u5efa\u6a21\uff0c\u53ef\u4ee5\u8fd1\u4f3c\u6a21\u62df\u5177\u6709\u8f83\u9ad8\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff08\u4e0e\u5468\u56f4\u4ecb\u8d28\u76f8\u6bd4\uff09\u7684\u8584\u5c42\u6750\u6599\u3002\u672c\u4f8b\u5c06\u4ecb\u7535\u5c4f\u853d\u8fb9\u754c\u6761\u4ef6\u4e0e\u9ad8\u4fdd\u771f\u6a21\u578b\u8fdb\u884c\u6bd4\u8f83\uff0c\u5e76\u8ba8\u8bba\u6b64\u8fb9\u754c\u6761\u4ef6\u7684\u9002\u7528\u8303\u56f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("dielectric_shielding_comparison.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
