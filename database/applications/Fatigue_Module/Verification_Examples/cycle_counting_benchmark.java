/*
 * cycle_counting_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:04 by COMSOL 6.3.0.290. */
public class cycle_counting_benchmark {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{10, 100});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2")
         .set("size", new String[]{"10-6.25", "50-sqrt(12.5^2-8.75^2)"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{6.25, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1")
         .set("pos", new String[]{"6.25+12.5", "50-sqrt(12.5^2-8.75^2)"});
    model.component("comp1").geom("geom1").feature("c1").set("r", 12.5);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1", "r2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.func().create("int1", "Interpolation");
    model.func("int1").setIndex("table", 1, 0, 0);
    model.func("int1").setIndex("table", -2, 0, 1);
    model.func("int1").setIndex("table", 2, 1, 0);
    model.func("int1").setIndex("table", 1, 1, 1);
    model.func("int1").setIndex("table", 3, 2, 0);
    model.func("int1").setIndex("table", -3, 2, 1);
    model.func("int1").setIndex("table", 4, 3, 0);
    model.func("int1").setIndex("table", 5, 3, 1);
    model.func("int1").setIndex("table", 5, 4, 0);
    model.func("int1").setIndex("table", -1, 4, 1);
    model.func("int1").setIndex("table", 6, 5, 0);
    model.func("int1").setIndex("table", 3, 5, 1);
    model.func("int1").setIndex("table", 7, 6, 0);
    model.func("int1").setIndex("table", -4, 6, 1);
    model.func("int1").setIndex("table", 8, 7, 0);
    model.func("int1").setIndex("table", 4, 7, 1);
    model.func("int1").setIndex("table", 9, 8, 0);
    model.func("int1").setIndex("table", -2, 8, 1);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", 0.00625);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "F*int1(case)", "0"});

    model.param().set("F", "10*6.25*12.5/2");
    model.param().descr("F", "\u8f7d\u8377\u5355\u4f4d");
    model.param().set("case", "1");
    model.param().descr("case", "\u8f7d\u8377\u5de5\u51b5");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"69e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.34"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2700"});

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "F", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "F", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "case", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,1,9)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 9, 0);
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
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 100, 1, 1);
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").setIndex("genpoints", -6.25, 0, 0);
    model.result().dataset("mir2").setIndex("genpoints", 6.25, 1, 0);
    model.result().dataset("mir2").setIndex("genpoints", 0, 1, 1);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir2");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.SGpYY");
    model.result("pg1").feature("surf1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0cYY \u5206\u91cf");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8f7d\u8377\u5faa\u73af\u54cd\u5e94");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(3);
    model.result("pg2").feature("ptgr1").set("expr", "solid.SGpYY");
    model.result("pg2").feature("ptgr1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0cYY \u5206\u91cf");
    model.result("pg2").run();

    model.func().create("an1", "Analytic");
    model.func("an1").set("args", "R, N");
    model.func("an1").set("expr", "(94e6*(R/-0.36)^1.15)*N^-0.119");

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("cdam1", "CumulativeDamageModel2", 0);
    model.component("comp1").physics("ftg").feature("cdam1").selection().set(3);
    model.component("comp1").physics("ftg").feature("cdam1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("cdam1").set("cDamDiscretizationMean", 5);
    model.component("comp1").physics("ftg").feature("cdam1").set("cDamDiscretizationRange", 7);
    model.component("comp1").physics("ftg").feature("cdam1").set("lifeCurve", "an1");
    model.component("comp1").physics("ftg").feature("cdam1").set("m", 100000);
    model.component("comp1").physics("ftg").feature("cdam1").set("Ncut", "1e8");

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").selection().all();
    model.result("pg3").feature("ptgr1").set("expr", new String[]{"ftg.fus"});
    model.result("pg3").feature("ptgr1").selection().set(3);
    model.result("pg3").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("hist1", "MatrixHistogram");
    model.result("pg4").feature("hist1").set("data", "dset2");
    model.result("pg4").feature("hist1").set("expr", "ftg.cdam1.csc");
    model.result("pg4").label("\u5e94\u529b\u5faa\u73af\u5206\u5e03 (ftg)");
    model.result("pg4").feature("hist1").set("xdata", "\u5e73\u5747\u5e94\u529b");
    model.result("pg4").feature("hist1").set("ydata", "\u5e94\u529b\u5e45\u503c");
    model.result("pg4").feature("hist1").create("hght", "HistogramHeight");
    model.result("pg4").feature("hist1").feature("hght")
         .label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f\uff08\u7528\u4e8e\u4e09\u7ef4\u76f4\u65b9\u56fe\uff09");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("hist1", "MatrixHistogram");
    model.result("pg5").feature("hist1").set("data", "dset2");
    model.result("pg5").feature("hist1").set("expr", "ftg.cdam1.rus");
    model.result("pg5").label("\u75b2\u52b3\u5229\u7528\u7387\u5206\u5e03 (ftg)");
    model.result("pg5").feature("hist1").set("xdata", "\u5e73\u5747\u5e94\u529b");
    model.result("pg5").feature("hist1").set("ydata", "\u5e94\u529b\u5e45\u503c");
    model.result("pg5").feature("hist1").create("hght", "HistogramHeight");
    model.result("pg5").feature("hist1").feature("hght")
         .label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f\uff08\u7528\u4e8e\u4e09\u7ef4\u76f4\u65b9\u56fe\uff09");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("hist1").set("axisunit", "MPa");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("hist1").set("axisunit", "MPa");
    model.result("pg5").run();
    model.result("pg4").run();

    model.title("\u75b2\u52b3\u5206\u6790\u4e2d\u7684\u5faa\u73af\u8ba1\u6570 - \u57fa\u51c6\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u201c\u96e8\u6d41\u8ba1\u6570\u201d\u7b97\u6cd5\u7684\u57fa\u51c6\u95ee\u9898\uff0c\u4f7f\u7528\u5e73\u9762\u62c9\u4f38\u8bd5\u6837\u6765\u6bd4\u8f83 ASTM \u4e0e COMSOL\u201c\u75b2\u52b3\u6a21\u5757\u201d\u4e4b\u95f4\u7684\u7ed3\u679c\u3002\u5176\u4e2d\u9075\u5faa Palmgren-Miner \u6a21\u578b\u5bf9\u7d2f\u79ef\u635f\u4f24\u8ba1\u7b97\u8fdb\u884c\u6269\u5c55\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u89e3\u6790\u5f0f\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("cycle_counting_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
