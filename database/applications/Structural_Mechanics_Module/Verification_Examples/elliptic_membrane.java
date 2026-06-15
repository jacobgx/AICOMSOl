/*
 * elliptic_membrane.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:23 by COMSOL 6.3.0.290. */
public class elliptic_membrane {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("div", "1");
    model.param().descr("div", "\u7f51\u683c\u7ec6\u5316\u56e0\u5b50");
    model.param().set("sy_ref", "92.65817[MPa]");
    model.param().descr("sy_ref", "\u76ee\u6807\u5e94\u529b");

    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new double[]{3.25, 2.75});
    model.component("comp1").geom("geom1").run("e1");
    model.component("comp1").geom("geom1").create("e2", "Ellipse");
    model.component("comp1").geom("geom1").feature("e2").set("semiaxes", new double[]{2.417, 1.583});
    model.component("comp1").geom("geom1").feature("e2").set("angle", 90);
    model.component("comp1").geom("geom1").run("e2");
    model.component("comp1").geom("geom1").create("e3", "Ellipse");
    model.component("comp1").geom("geom1").feature("e3").set("semiaxes", new int[]{2, 1});
    model.component("comp1").geom("geom1").run("e3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("e1", "e2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("e3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{1.783, 2.3});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{1.165, 0.812});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new double[]{2.833, 1.348});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new double[]{1.783, 0.453});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210E3[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", 0.1);
    model.component("comp1").physics("solid").feature().duplicate("lemm2", "lemm1");
    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1")
         .label("\u7ebf\u5f39\u6027\u6750\u6599 1\uff0c\u964d\u9636\u79ef\u5206");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2, 9, 11);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(15, 18, 21);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "-10[MPa]");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "div");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 2, 13, 16, 19);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("solid").create("disc1", "Discretization", -1);
    model.component("comp1").physics("solid").feature("disc1").set("order_displacement", 1);
    model.component("comp1").physics("solid").feature("disc1").label("\u7ebf\u6027\u79bb\u6563");
    model.component("comp1").physics("solid").feature().duplicate("disc2", "disc1");
    model.component("comp1").physics("solid").feature("disc2").set("order_displacement", 3);
    model.component("comp1").physics("solid").feature("disc2").label("\u4e09\u9636\u79bb\u6563");

    model.study("std1").label("\u56db\u8fb9\u5f62\u7ebf\u6027\u5355\u5143\u7814\u7a76");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "div", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "div", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2 3 4 8 12 16 24 32 48 64", 0);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u56db\u8fb9\u5f62\u4e8c\u6b21\u5355\u5143\u7814\u7a76");
    model.study("std2").feature().copy("param", "std1/param");
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u56db\u8fb9\u5f62\u4e09\u6b21\u5355\u5143\u7814\u7a76");
    model.study("std3").feature().copy("param", "std1/param");
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").setEntry("discretization", "solid", "disc2");

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").label("\u4e09\u89d2\u5f62\u7f51\u683c");
    model.component("comp1").mesh("mesh2").create("conv1", "Convert");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").label("\u4e09\u89d2\u5f62\u7ebf\u6027\u5355\u5143\u7814\u7a76");
    model.study("std4").feature().copy("param", "std1/param");
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study("std4").feature("stat").set("useadvanceddisable", false);
    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std5").label("\u4e09\u89d2\u5f62\u4e8c\u6b21\u5355\u5143\u7814\u7a76");
    model.study("std5").feature().copy("param", "std1/param");
    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std6").label("\u4e09\u89d2\u5f62\u4e09\u6b21\u5355\u5143\u7814\u7a76");
    model.study("std6").feature().copy("param", "std1/param");
    model.study("std6").feature("stat").set("useadvanceddisable", true);
    model.study("std6").feature("stat").setEntry("discretization", "solid", "disc2");
    model.study().create("std7");
    model.study("std7").create("stat", "Stationary");
    model.study("std7").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std7").label("\u56db\u8fb9\u5f62\u7ebf\u6027\u5355\u5143\u964d\u9636\u7814\u7a76");
    model.study("std7").feature().copy("param", "std1/param");
    model.study("std7").feature("stat").set("useadvanceddisable", true);
    model.study("std7").feature("stat").set("disabledphysics", new String[]{"solid/lemm2"});
    model.study("std7").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study("std7").feature("stat").setEntry("mesh", "geom1", "mesh1");
    model.study().create("std8");
    model.study("std8").create("stat", "Stationary");
    model.study("std8").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std8").label("\u56db\u8fb9\u5f62\u56db\u9636\u5355\u5143\u964d\u9636\u7814\u7a76");
    model.study("std8").feature().copy("param", "std1/param");
    model.study("std8").feature("stat").set("useadvanceddisable", true);
    model.study("std8").feature("stat").set("disabledphysics", new String[]{"solid/lemm2"});
    model.study("std8").feature("stat").setEntry("mesh", "geom1", "mesh1");
    model.study().create("std9");
    model.study("std9").create("stat", "Stationary");
    model.study("std9").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std9").label("\u56db\u8fb9\u5f62\u4e09\u9636\u5355\u5143\u964d\u9636\u7814\u7a76");
    model.study("std9").feature().copy("param", "std1/param");
    model.study("std9").feature("stat").set("useadvanceddisable", true);
    model.study("std9").feature("stat").set("disabledphysics", new String[]{"solid/lemm2"});
    model.study("std9").feature("stat").setEntry("discretization", "solid", "disc2");
    model.study("std9").feature("stat").setEntry("mesh", "geom1", "mesh1");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 11, 0);
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

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol15");
    model.sol("sol15").study("std2");
    model.sol("sol15").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol15");
    model.batch("p2").run("compute");

    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol28");
    model.sol("sol28").study("std3");
    model.sol("sol28").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol28");
    model.batch("p3").run("compute");

    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol41");
    model.sol("sol41").study("std4");
    model.sol("sol41").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("p4").feature("so1").set("psol", "sol41");
    model.batch("p4").run("compute");

    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol54");
    model.sol("sol54").study("std5");
    model.sol("sol54").label("\u53c2\u6570\u5316\u89e3 5");

    model.batch("p5").feature("so1").set("psol", "sol54");
    model.batch("p5").run("compute");

    model.study("std6").setGenPlots(false);
    model.study("std6").createAutoSequences("all");

    model.sol().create("sol67");
    model.sol("sol67").study("std6");
    model.sol("sol67").label("\u53c2\u6570\u5316\u89e3 6");

    model.batch("p6").feature("so1").set("psol", "sol67");
    model.batch("p6").run("compute");

    model.study("std7").setGenPlots(false);
    model.study("std7").createAutoSequences("all");

    model.sol().create("sol80");
    model.sol("sol80").study("std7");
    model.sol("sol80").label("\u53c2\u6570\u5316\u89e3 7");

    model.batch("p7").feature("so1").set("psol", "sol80");
    model.batch("p7").run("compute");

    model.study("std8").setGenPlots(false);
    model.study("std8").createAutoSequences("all");

    model.sol().create("sol93");
    model.sol("sol93").study("std8");
    model.sol("sol93").label("\u53c2\u6570\u5316\u89e3 8");

    model.batch("p8").feature("so1").set("psol", "sol93");
    model.batch("p8").run("compute");

    model.study("std9").setGenPlots(false);
    model.study("std9").createAutoSequences("all");

    model.sol().create("sol106");
    model.sol("sol106").study("std9");
    model.sol("sol106").label("\u53c2\u6570\u5316\u89e3 9");

    model.batch("p9").feature("so1").set("psol", "sol106");
    model.batch("p9").run("compute");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("D \u5904\u7684\u7f51\u683c\u6536\u655b sy");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(11);
    model.result("pg2").feature("ptgr1").set("data", "dset2");
    model.result("pg2").feature("ptgr1").set("expr", "abs(solid.sGpy/sy_ref-1)");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "div/0.417");
    model.result("pg2").feature("ptgr1").set("linemarker", "asterisk");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u4e00\u9636\u56db\u8fb9\u5f62", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("data", "dset4");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u4e8c\u9636\u56db\u8fb9\u5f62", 0);
    model.result("pg2").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("data", "dset6");
    model.result("pg2").feature("ptgr3").setIndex("legends", "\u4e09\u9636\u56db\u8fb9\u5f62", 0);
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("data", "dset8");
    model.result("pg2").feature("ptgr4").set("linestyle", "dashdot");
    model.result("pg2").feature("ptgr4").set("linecolor", "cyclereset");
    model.result("pg2").feature("ptgr4").set("linemarker", "circle");
    model.result("pg2").feature("ptgr4").setIndex("legends", "\u4e00\u9636\u4e09\u89d2\u5f62", 0);
    model.result("pg2").feature().duplicate("ptgr5", "ptgr4");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr5").set("data", "dset10");
    model.result("pg2").feature("ptgr5").set("linecolor", "cycle");
    model.result("pg2").feature("ptgr5").setIndex("legends", "\u4e8c\u9636\u4e09\u89d2\u5f62", 0);
    model.result("pg2").feature().duplicate("ptgr6", "ptgr5");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr6").set("data", "dset12");
    model.result("pg2").feature("ptgr6").setIndex("legends", "\u4e09\u9636\u4e09\u89d2\u5f62", 0);
    model.result("pg2").feature().duplicate("ptgr7", "ptgr6");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr7").set("data", "dset14");
    model.result("pg2").feature("ptgr7").set("linestyle", "dotted");
    model.result("pg2").feature("ptgr7").set("linecolor", "cyclereset");
    model.result("pg2").feature("ptgr7").set("linemarker", "plus");
    model.result("pg2").feature("ptgr7").setIndex("legends", "\u4e00\u9636\u56db\u8fb9\u5f62\uff0c\u7ea2\u8272", 0);
    model.result("pg2").feature().duplicate("ptgr8", "ptgr7");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr8").set("data", "dset16");
    model.result("pg2").feature("ptgr8").set("linecolor", "cycle");
    model.result("pg2").feature("ptgr8").setIndex("legends", "\u4e8c\u9636\u56db\u8fb9\u5f62\uff0c\u7ea2\u8272", 0);
    model.result("pg2").feature().duplicate("ptgr9", "ptgr8");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr9").set("data", "dset18");
    model.result("pg2").feature("ptgr9").setIndex("legends", "\u4e09\u9636\u56db\u8fb9\u5f62\uff0c\u7ea2\u8272", 0);
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "1/h (1/m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("D \u5904\u7684\u7f51\u683c\u6536\u655b sx");
    model.result("pg3").feature("ptgr1").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr1").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr2").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr2").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr3").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr3").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr4").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr4").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr5").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr5").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr6").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr6").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr7").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr7").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr8").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr8").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr9").set("expr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").feature("ptgr9").set("descr", "abs(solid.sGpx/sy_ref)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("D \u5904\u7684\u7f51\u683c\u6536\u655b sxy");
    model.result("pg4").feature("ptgr1").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr1").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr2").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr2").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr3").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr3").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr4").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr4").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr5").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr5").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr6").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr6").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr7").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr7").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr8").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr8").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr9").set("expr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").feature("ptgr9").set("descr", "abs(solid.sGpxy/sy_ref)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("D \u5904\u7684\u7f51\u683c\u6536\u655b sy\uff08\u901a\u8fc7\u81ea\u7531\u5ea6\uff09");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("xlabel", "\u81ea\u7531\u5ea6\u6570");
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(11);
    model.result().evaluationGroup("eg1").feature("pev1").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("looplevel", new int[]{1, 2}, 0);
    model.result().evaluationGroup("eg1").feature("pev1").set("expr", new String[]{"solid.sGpyy"});
    model.result().evaluationGroup("eg1").feature("pev1")
         .set("descr", new String[]{"\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf"});
    model.result().evaluationGroup("eg1").feature("pev1").set("unit", new String[]{"MPa"});
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u7ebf\u6027\u5355\u5143", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "(solid.sGpyy-sy_ref)/sy_ref", 1);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("unit", "%", 1);
    model.result().evaluationGroup("eg1").feature().duplicate("pev2", "pev1");
    model.result().evaluationGroup("eg1").feature("pev2").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("pev2")
         .setIndex("descr", "\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u4e8c\u6b21\u5355\u5143", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev3", "pev2");
    model.result().evaluationGroup("eg1").feature("pev3").set("data", "dset6");
    model.result().evaluationGroup("eg1").feature("pev3")
         .setIndex("descr", "\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u4e09\u6b21\u5355\u5143", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev4", "pev3");
    model.result().evaluationGroup("eg1").feature("pev4").set("data", "dset8");
    model.result().evaluationGroup("eg1").feature("pev4")
         .setIndex("descr", "\u5e94\u529b\uff0c\u4e09\u89d2\u5f62\u7ebf\u6027\u5355\u5143", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev5", "pev4");
    model.result().evaluationGroup("eg1").feature("pev5").set("data", "dset10");
    model.result().evaluationGroup("eg1").feature("pev5")
         .setIndex("descr", "\u5e94\u529b\uff0c\u4e09\u89d2\u5f62\u4e8c\u6b21\u5355\u5143", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev6", "pev5");
    model.result().evaluationGroup("eg1").feature("pev6").set("data", "dset12");
    model.result().evaluationGroup("eg1").feature("pev6")
         .setIndex("descr", "\u5e94\u529b\uff0c\u4e09\u89d2\u5f62\u4e09\u6b21\u5355\u5143", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev7", "pev6");
    model.result().evaluationGroup("eg1").feature("pev7").set("data", "dset14");
    model.result().evaluationGroup("eg1").feature("pev7")
         .setIndex("descr", "\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u7ebf\u6027\u5355\u5143\uff0c\u7ea2\u8272", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev8", "pev7");
    model.result().evaluationGroup("eg1").feature("pev8").set("data", "dset16");
    model.result().evaluationGroup("eg1").feature("pev8")
         .setIndex("descr", "\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u56db\u6b21\u5355\u5143\uff0c\u7ea2\u8272", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev9", "pev8");
    model.result().evaluationGroup("eg1").feature("pev9").set("data", "dset18");
    model.result().evaluationGroup("eg1").feature("pev9")
         .setIndex("descr", "\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u4e09\u6b21\u5355\u5143\uff0c\u7ea2\u8272", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.sGpyy");
    model.result("pg1").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("showlegendsmaxmin", true);

    model.title("\u692d\u5706\u8584\u819c\u7684\u5e94\u529b\u5206\u6790");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u5e73\u9762\u5e94\u529b\u95ee\u9898\u7684\u57fa\u51c6\u6a21\u578b\uff0c\u7528\u4e8e\u8bc4\u4f30\u8ba1\u7b97\u51fa\u7684\u5e94\u529b\u96c6\u4e2d\u7684\u51c6\u786e\u6027\uff0c\u5e76\u9488\u5bf9\u4e0d\u540c\u7684\u5355\u5143\u7c7b\u578b\u6267\u884c\u7f51\u683c\u6536\u655b\u6027\u7814\u7a76\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();
    model.sol("sol70").clearSolutionData();
    model.sol("sol71").clearSolutionData();
    model.sol("sol72").clearSolutionData();
    model.sol("sol73").clearSolutionData();
    model.sol("sol74").clearSolutionData();
    model.sol("sol75").clearSolutionData();
    model.sol("sol76").clearSolutionData();
    model.sol("sol77").clearSolutionData();
    model.sol("sol78").clearSolutionData();
    model.sol("sol79").clearSolutionData();
    model.sol("sol80").clearSolutionData();
    model.sol("sol81").clearSolutionData();
    model.sol("sol82").clearSolutionData();
    model.sol("sol83").clearSolutionData();
    model.sol("sol84").clearSolutionData();
    model.sol("sol85").clearSolutionData();
    model.sol("sol86").clearSolutionData();
    model.sol("sol87").clearSolutionData();
    model.sol("sol88").clearSolutionData();
    model.sol("sol89").clearSolutionData();
    model.sol("sol90").clearSolutionData();
    model.sol("sol91").clearSolutionData();
    model.sol("sol92").clearSolutionData();
    model.sol("sol93").clearSolutionData();

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol94").clearSolutionData();
    model.sol("sol95").clearSolutionData();
    model.sol("sol96").clearSolutionData();
    model.sol("sol97").clearSolutionData();
    model.sol("sol98").clearSolutionData();
    model.sol("sol99").clearSolutionData();
    model.sol("sol100").clearSolutionData();
    model.sol("sol101").clearSolutionData();
    model.sol("sol102").clearSolutionData();
    model.sol("sol103").clearSolutionData();
    model.sol("sol104").clearSolutionData();
    model.sol("sol105").clearSolutionData();
    model.sol("sol106").clearSolutionData();
    model.sol("sol107").clearSolutionData();
    model.sol("sol108").clearSolutionData();
    model.sol("sol109").clearSolutionData();
    model.sol("sol110").clearSolutionData();
    model.sol("sol111").clearSolutionData();
    model.sol("sol112").clearSolutionData();
    model.sol("sol113").clearSolutionData();
    model.sol("sol114").clearSolutionData();
    model.sol("sol115").clearSolutionData();
    model.sol("sol116").clearSolutionData();
    model.sol("sol117").clearSolutionData();

    model.label("elliptic_membrane.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
