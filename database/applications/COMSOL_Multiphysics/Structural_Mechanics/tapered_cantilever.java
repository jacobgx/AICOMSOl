/*
 * tapered_cantilever.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:38 by COMSOL 6.3.0.290. */
public class tapered_cantilever {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Structural_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 4 4 0 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 1 3 4 0");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 2, 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7000[kg/m^3]"});

    model.group().create("lg1", "LoadGroup");
    model.group("lg1").label("\u8f7d\u8377\u7ec4\uff0c\u91cd\u529b");
    model.group("lg1").paramName("lgG");
    model.group().create("lg2", "LoadGroup");
    model.group("lg2").label("\u8f7d\u8377\u7ec4\uff0c\u529b");
    model.group("lg2").paramName("lgF");
    model.group().create("cg1", "ConstraintGroup");
    model.group("cg1").label("\u7ea6\u675f\u7ec4\uff0c\u91cd\u529b");
    model.group("cg1").paramName("cgGravity");
    model.group().create("cg2", "ConstraintGroup");
    model.group("cg2").label("\u7ea6\u675f\u7ec4\uff0c\u529b");
    model.group("cg2").paramName("cgForce");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", 0.1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 3);
    model.component("comp1").physics("solid").feature("fix1").set("constraintGroup", "cg1");
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("solid").feature("bl1").selection().set(1);
    model.component("comp1").physics("solid").feature("bl1")
         .set("forceReferenceVolume", new String[]{"0", "-g_const*solid.rho", "0"});
    model.component("comp1").physics("solid").feature("bl1").set("loadGroup", "lg1");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 3);
    model.component("comp1").physics("solid").feature("roll1").set("constraintGroup", "cg2");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(5);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "ForceLength");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceLength", new String[]{"10[MN/m]", "0", "0"});
    model.component("comp1").physics("solid").feature("bndl1").set("loadGroup", "lg2");
    model.component("comp1").physics("solid").create("fix2", "Fixed", 0);
    model.component("comp1").physics("solid").feature("fix2").selection().set(2);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u91cd\u529b", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u529b", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 1, 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").label("\u6b63\u5e94\u529b");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.sGpxx");
    model.result("pg1").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u526a\u5207\u5e94\u529b");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.sGpxy");
    model.result("pg2").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u70b9\u8ba1\u7b97 - \u6b63\u5e94\u529b");
    model.result().numerical("pev1").selection().set(2);
    model.result().numerical("pev1").set("expr", new String[]{"solid.sGpxx"});
    model.result().numerical("pev1").set("descr", new String[]{"\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf"});
    model.result().numerical("pev1").set("unit", new String[]{"N/m^2"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 - \u6b63\u5e94\u529b");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("pev2", "EvalPoint");
    model.result().numerical("pev2").label("\u70b9\u8ba1\u7b97 - \u526a\u5207\u5e94\u529b");
    model.result().numerical("pev2").selection().set(2);
    model.result().numerical("pev2").set("expr", new String[]{"solid.sGpxy"});
    model.result().numerical("pev2").set("descr", new String[]{"\u5e94\u529b\u5f20\u91cf\uff0cxy \u5206\u91cf"});
    model.result().numerical("pev2").set("unit", new String[]{"N/m^2"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u70b9\u8ba1\u7b97 - \u526a\u5207\u5e94\u529b");
    model.result().numerical("pev2").set("table", "tbl2");
    model.result().numerical("pev2").setResult();
    model.result("pg1").run();

    model.title("\u4e24\u79cd\u8f7d\u8377\u5de5\u51b5\u4e0b\u7684\u9525\u5f62\u60ac\u81c2\u6881");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4e00\u4e2a\u8584\u9525\u5f62\u60ac\u81c2\u6881\u7684\u4e8c\u7ef4\u5e73\u9762\u5e94\u529b\u6a21\u578b\u3002\u68c0\u67e5\u4e86\u4e0d\u540c\u7684\u8fb9\u754c\u548c\u8f7d\u8377\u60c5\u51b5\uff0c\u6f14\u793a\u5982\u4f55\u65bd\u52a0\u5e76\u8ba1\u7b97\u4e0d\u540c\u7684\u8f7d\u8377\u53ca\u7ea6\u675f\u7ec4\u3002\u4eff\u771f\u5f97\u5230\u7684\u5e94\u529b\u4e0e NAFEMS \u57fa\u51c6\u503c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("tapered_cantilever.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
